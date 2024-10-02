package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieDirectorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884729Z", comments="Source field: movies_directors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884807Z", comments="Source field: movies_directors.director_id")
    private Integer directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884759Z", comments="Source field: movies_directors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884785Z", comments="Source field: movies_directors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884831Z", comments="Source field: movies_directors.director_id")
    public Integer getDirectorId() {
        return directorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884856Z", comments="Source field: movies_directors.director_id")
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
}