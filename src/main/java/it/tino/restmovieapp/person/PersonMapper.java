package it.tino.restmovieapp.person;

import it.tino.restmovieapp.DateConverter;
import it.tino.restmovieapp.ObjectMapper;
import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.movie.MovieActor;
import it.tino.restmovieapp.movie.MovieDirector;
import it.tino.restmovieapp.mybatis.mapper.VMovieActorDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.VMovieActorDbMapper;
import it.tino.restmovieapp.mybatis.mapper.VMovieDirectorDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.VMovieDirectorDbMapper;
import it.tino.restmovieapp.mybatis.model.PersonDb;
import it.tino.restmovieapp.mybatis.model.VMovieActorDb;
import it.tino.restmovieapp.mybatis.model.VMovieDirectorDb;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class PersonMapper implements ObjectMapper<Person, PersonDb> {

    protected static final Logger logger = LogManager.getLogger();
    private final SqlSessionFactory sqlSessionFactory;

    public PersonMapper(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Person> sourceToDomain(Collection<PersonDb> source) {
        List<Integer> personIds = source
                .stream()
                .map(PersonDb::getId)
                .toList();

        if (personIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<VMovieDirectorDb> vMovieDirectorDbList;
        List<VMovieActorDb> vMovieActorDbList;

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            VMovieDirectorDbMapper vMovieDirectorDao = sqlSession.getMapper(VMovieDirectorDbMapper.class);
            VMovieActorDbMapper vMovieActorDao = sqlSession.getMapper(VMovieActorDbMapper.class);

            vMovieDirectorDbList = vMovieDirectorDao.select(c ->
                    c.where(VMovieDirectorDbDynamicSqlSupport.directorId, SqlBuilder.isIn(personIds))
            );
            vMovieActorDbList = vMovieActorDao.select(c ->
                    c.where(VMovieActorDbDynamicSqlSupport.actorId, SqlBuilder.isIn(personIds))
            );
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }

        List<Person> people = new ArrayList<>();
        for (PersonDb personDb : source) {
            List<MovieDirector> directedMovies = vMovieDirectorDbList
                    .stream()
                    .filter(md -> Objects.equals(md.getDirectorId(), personDb.getId()))
                    .map(md -> {
                        MovieDirector directedMovie = new MovieDirector();
                        directedMovie.setMovieId(md.getMovieId());
                        directedMovie.setDirectorId(md.getDirectorId());
                        return directedMovie;
                    })
                    .toList();
            List<MovieActor> starredMovies = vMovieActorDbList
                    .stream()
                    .filter(ma -> Objects.equals(ma.getActorId(), personDb.getId()))
                    .map(ma -> {
                        MovieActor starredMovie = new MovieActor();
                        starredMovie.setMovieId(ma.getMovieId());
                        starredMovie.setActorId(ma.getActorId());
                        starredMovie.setRoleName(ma.getRole());
                        starredMovie.setCastOrder(ma.getCastOrder());
                        return starredMovie;
                    })
                    .toList();

            LocalDate convertedBirth = DateConverter.dateToLocalDate(personDb.getBirth());
            Person person = new Person();

            person.setId(personDb.getId());
            person.setName(personDb.getName());
            person.setLastName(personDb.getLastName());
            person.setBirth(convertedBirth);
            person.setGender(Person.Gender.fromId(personDb.getGender()));
            person.setDirectedMovies(directedMovies);
            person.setStarredMovies(starredMovies);

            people.add(person);
        }

        return people;
    }

    @Override
    public List<PersonDb> domainToTarget(Collection<Person> domainEntities) {
        List<PersonDb> peopleDb = new ArrayList<>();
        for (Person domainEntity : domainEntities) {
            PersonDb personDb = new PersonDb();
            personDb.setId(domainEntity.getId());
            personDb.setName(domainEntity.getName());
            personDb.setLastName(domainEntity.getLastName());
            personDb.setBirth(Date.valueOf(domainEntity.getBirth()));
            personDb.setGender(domainEntity.getGender().getId());
            peopleDb.add(personDb);
        }

        return peopleDb;
    }
}
