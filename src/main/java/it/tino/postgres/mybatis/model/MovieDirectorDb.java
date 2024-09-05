package it.tino.postgres.mybatis.model;

import jakarta.annotation.Generated;

public class MovieDirectorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441385864+02:00", comments="Source field: movies_directors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44145905+02:00", comments="Source field: movies_directors.director_id")
    private Integer directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441411922+02:00", comments="Source field: movies_directors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441441517+02:00", comments="Source field: movies_directors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441481241+02:00", comments="Source field: movies_directors.director_id")
    public Integer getDirectorId() {
        return directorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441502711+02:00", comments="Source field: movies_directors.director_id")
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
}