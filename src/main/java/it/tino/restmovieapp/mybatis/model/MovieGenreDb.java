package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieGenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.642383633+02:00", comments="Source field: movies_genres.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.642445127+02:00", comments="Source field: movies_genres.genre_id")
    private Integer genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.642411254+02:00", comments="Source field: movies_genres.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.642431392+02:00", comments="Source field: movies_genres.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.64246273+02:00", comments="Source field: movies_genres.genre_id")
    public Integer getGenreId() {
        return genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.642481404+02:00", comments="Source field: movies_genres.genre_id")
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}