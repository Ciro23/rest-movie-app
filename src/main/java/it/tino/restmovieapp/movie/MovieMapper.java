package it.tino.restmovieapp.movie;

import it.tino.restmovieapp.DateConverter;
import it.tino.restmovieapp.ObjectMapper;
import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.mybatis.mapper.*;
import it.tino.restmovieapp.mybatis.model.MovieDb;
import it.tino.restmovieapp.mybatis.model.VMovieActorDb;
import it.tino.restmovieapp.mybatis.model.VMovieDirectorDb;
import it.tino.restmovieapp.mybatis.model.VMovieGenreDb;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MovieMapper implements ObjectMapper<Movie, MovieDb> {

    protected static final Logger logger = LogManager.getLogger();
    private final SqlSessionFactory sqlSessionFactory;

    public MovieMapper(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Movie> dbToDomain(Collection<MovieDb> dbEntities) {
        List<Integer> movieIds = dbEntities
                .stream()
                .map(MovieDb::getId)
                .toList();

        List<VMovieDirectorDb> vMovieDirectorDbList;
        List<VMovieActorDb> vMovieActorDbList;
        List<VMovieGenreDb> vMovieGenreDbList;

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            VMovieDirectorDbMapper vMovieDirectorDao = sqlSession.getMapper(VMovieDirectorDbMapper.class);
            VMovieActorDbMapper vMovieActorDao = sqlSession.getMapper(VMovieActorDbMapper.class);
            VMovieGenreDbMapper vMovieActorDbMapper = sqlSession.getMapper(VMovieGenreDbMapper.class);

            vMovieDirectorDbList = vMovieDirectorDao.select(c ->
                    c.where(VMovieDirectorDbDynamicSqlSupport.movieId, SqlBuilder.isIn(movieIds))
            );
            vMovieActorDbList = vMovieActorDao.select(c ->
                    c.where(VMovieActorDbDynamicSqlSupport.movieId, SqlBuilder.isIn(movieIds))
            );
            vMovieGenreDbList = vMovieActorDbMapper.select(c ->
                    c.where(VMovieGenreDbDynamicSqlSupport.movieId, SqlBuilder.isIn(movieIds))
            );
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }

        List<Movie> movies = new ArrayList<>();
        for (MovieDb movieDb : dbEntities) {
            List<MovieDirector> directors = vMovieDirectorDbList
                    .stream()
                    .filter(md -> Objects.equals(md.getMovieId(), movieDb.getId()))
                    .map(md -> {
                        MovieDirector directedMovie = new MovieDirector();
                        directedMovie.setMovieId(md.getMovieId());
                        directedMovie.setDirectorId(md.getDirectorId());
                        return directedMovie;
                    })
                    .toList();
            List<MovieActor> actors = vMovieActorDbList
                    .stream()
                    .filter(ma -> Objects.equals(ma.getMovieId(), movieDb.getId()))
                    .map(ma -> {
                        MovieActor starredMovie = new MovieActor();
                        starredMovie.setMovieId(ma.getMovieId());
                        starredMovie.setActorId(ma.getActorId());
                        starredMovie.setRoleName(ma.getRole());
                        starredMovie.setCastOrder(ma.getCastOrder());
                        return starredMovie;
                    })
                    .toList();
            List<MovieGenre> genres = vMovieGenreDbList
                    .stream()
                    .filter(mg -> Objects.equals(mg.getMovieId(), movieDb.getId()))
                    .map(mg -> {
                        MovieGenre movieGenre = new MovieGenre();
                        movieGenre.setMovieId(mg.getMovieId());
                        movieGenre.setGenreId(mg.getGenreId());
                        return movieGenre;
                    })
                    .toList();

            Movie movie = new Movie();
            movie.setId(movieDb.getId());
            movie.setTitle(movieDb.getTitle());
            movie.setReleaseDate(DateConverter.dateToLocalDate(movieDb.getReleaseDate()));
            movie.setBudget(movieDb.getBudget());
            movie.setBoxOffice(movieDb.getBoxOffice());
            movie.setRuntime(movieDb.getRuntime());
            movie.setOverview(movieDb.getOverview());
            movie.setGenres(genres);
            movie.setDirectors(directors);
            movie.setActors(actors);

            movies.add(movie);
        }

        return movies;
    }

    @Override
    public List<MovieDb> domainToDb(Collection<Movie> domainEntities) {
        List<MovieDb> moviesDb = new ArrayList<>();
        for (Movie domainEntity : domainEntities) {
            MovieDb movieDb = new MovieDb();
            movieDb.setId(domainEntity.getId());
            movieDb.setTitle(domainEntity.getTitle());
            movieDb.setReleaseDate(Date.valueOf(domainEntity.getReleaseDate()));
            movieDb.setBudget(domainEntity.getBudget());
            movieDb.setBoxOffice(domainEntity.getBoxOffice());
            movieDb.setRuntime(domainEntity.getRuntime());
            movieDb.setOverview(domainEntity.getOverview());

            moviesDb.add(movieDb);
        }
        return moviesDb;
    }
}
