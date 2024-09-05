package it.tino.postgres;

import it.tino.postgres.error.MovieAppException;
import it.tino.postgres.genre.GenreManager;
import it.tino.postgres.genre.GenreMapper;
import it.tino.postgres.movie.MovieManager;
import it.tino.postgres.movie.MovieMapper;
import it.tino.postgres.mybatis.mapper.*;
import it.tino.postgres.mybatis.model.*;
import it.tino.postgres.person.PersonManager;
import it.tino.postgres.person.PersonMapper;
import it.tino.postgres.review.ReviewManager;
import it.tino.postgres.review.ReviewMapper;
import it.tino.postgres.user.UserManager;
import it.tino.postgres.user.UserMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CustomBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(SqlSessionFactoryFactory.class).to(SqlSessionFactory.class).in(Singleton.class);

        bindFactory(UserMapperFactory.class).to(UserMapper.class);
        bindFactory(UserManagerFactory.class).to(UserManager.class);

        bindFactory(ReviewMapperFactory.class).to(ReviewMapper.class);
        bindFactory(ReviewManagerFactory.class).to(ReviewManager.class);

        bindFactory(GenreMapperFactory.class).to(GenreMapper.class);
        bindFactory(GenreManagerFactory.class).to(GenreManager.class);

        bindFactory(PersonMapperFactory.class).to(PersonMapper.class);
        bindFactory(PersonManagerFactory.class).to(PersonManager.class);

        bindFactory(MovieMapperFactory.class).to(MovieMapper.class);
        bindFactory(MovieManagerFactory.class).to(MovieManager.class);
    }

    static class SqlSessionFactoryFactory implements Factory<SqlSessionFactory> {
        @Override
        public SqlSessionFactory provide() {
            DataSource dataSource = produceDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();

            Environment environment = new Environment("development", transactionFactory, dataSource);

            Configuration configuration = new Configuration(environment);
            configuration.setLazyLoadingEnabled(true);
            configuration.setUseGeneratedKeys(true);

            configuration.getTypeAliasRegistry().registerAlias(UserDb.class);
            configuration.addMapper(UserDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(ReviewDb.class);
            configuration.addMapper(ReviewDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(GenreDb.class);
            configuration.addMapper(GenreDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(PersonDb.class);
            configuration.addMapper(PersonDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(MovieDb.class);
            configuration.addMapper(MovieDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(MovieDirectorDb.class);
            configuration.addMapper(MovieDirectorDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(MovieActorDb.class);
            configuration.addMapper(MovieActorDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(MovieGenreDb.class);
            configuration.addMapper(MovieGenreDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(VMovieDirectorDb.class);
            configuration.addMapper(VMovieDirectorDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(VMovieActorDb.class);
            configuration.addMapper(VMovieActorDbMapper.class);

            configuration.getTypeAliasRegistry().registerAlias(VMovieGenreDb.class);
            configuration.addMapper(VMovieGenreDbMapper.class);

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            return builder.build(configuration);
        }

        @Override
        public void dispose(SqlSessionFactory sessionFactory) {}

        private DataSource produceDataSource() {
            InitialContext ctx;
            try {
                ctx = new InitialContext();
                return (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
            } catch (NamingException e) {
                throw new MovieAppException(e);
            }
        }
    }

    static class UserMapperFactory implements Factory<UserMapper> {

        @Override
        public UserMapper provide() {
            return new UserMapper();
        }

        @Override
        public void dispose(UserMapper instance) {}
    }

    static class UserManagerFactory implements Factory<UserManager> {

        private final SqlSessionFactory sqlSessionFactory;
        private final UserMapper userMapper;

        @Inject
        UserManagerFactory(SqlSessionFactory sqlSessionFactory, UserMapper userMapper) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.userMapper = userMapper;
        }

        @Override
        public UserManager provide() {
            return new UserManager(sqlSessionFactory, userMapper);
        }

        @Override
        public void dispose(UserManager instance) {}
    }

    static class ReviewMapperFactory implements Factory<ReviewMapper> {

        @Override
        public ReviewMapper provide() {
            return new ReviewMapper();
        }

        @Override
        public void dispose(ReviewMapper instance) {}
    }

    static class ReviewManagerFactory implements Factory<ReviewManager> {

        private final SqlSessionFactory sqlSessionFactory;
        private final ReviewMapper reviewMapper;

        @Inject
        ReviewManagerFactory(SqlSessionFactory sqlSessionFactory, ReviewMapper reviewMapper) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.reviewMapper = reviewMapper;
        }

        @Override
        public ReviewManager provide() {
            return new ReviewManager(sqlSessionFactory, reviewMapper);
        }

        @Override
        public void dispose(ReviewManager instance) {}
    }

    static class GenreMapperFactory implements Factory<GenreMapper> {

        @Override
        public GenreMapper provide() {
            return new GenreMapper();
        }

        @Override
        public void dispose(GenreMapper instance) {}
    }

    static class GenreManagerFactory implements Factory<GenreManager> {

        private final SqlSessionFactory sqlSessionFactory;
        private final GenreMapper genreMapper;

        @Inject
        GenreManagerFactory(SqlSessionFactory sqlSessionFactory, GenreMapper genreMapper) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.genreMapper = genreMapper;
        }

        @Override
        public GenreManager provide() {
            return new GenreManager(sqlSessionFactory, genreMapper);
        }

        @Override
        public void dispose(GenreManager instance) {}
    }

    static class PersonMapperFactory implements Factory<PersonMapper> {

        private final SqlSessionFactory sqlSessionFactory;

        @Inject
        PersonMapperFactory(SqlSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
        }

        @Override
        public PersonMapper provide() {
            return new PersonMapper(sqlSessionFactory);
        }

        @Override
        public void dispose(PersonMapper instance) {}
    }

    static class PersonManagerFactory implements Factory<PersonManager> {

        private final SqlSessionFactory sqlSessionFactory;
        private final PersonMapper personMapper;

        @Inject
        PersonManagerFactory(SqlSessionFactory sqlSessionFactory, PersonMapper personMapper) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.personMapper = personMapper;
        }

        @Override
        public PersonManager provide() {
            return new PersonManager(sqlSessionFactory, personMapper);
        }

        @Override
        public void dispose(PersonManager instance) {}
    }

    static class MovieMapperFactory implements Factory<MovieMapper> {

        private final SqlSessionFactory sqlSessionFactory;

        @Inject
        MovieMapperFactory(SqlSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
        }

        @Override
        public MovieMapper provide() {
            return new MovieMapper(sqlSessionFactory);
        }

        @Override
        public void dispose(MovieMapper instance) {}
    }

    static class MovieManagerFactory implements Factory<MovieManager> {

        private final SqlSessionFactory sqlSessionFactory;
        private final MovieMapper movieMapper;

        @Inject
        MovieManagerFactory(SqlSessionFactory sqlSessionFactory, MovieMapper movieMapper) {
            this.sqlSessionFactory = sqlSessionFactory;
            this.movieMapper = movieMapper;
        }

        @Override
        public MovieManager provide() {
            return new MovieManager(sqlSessionFactory, movieMapper);
        }

        @Override
        public void dispose(MovieManager instance) {}
    }
}
