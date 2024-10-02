package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieGenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888904Z", comments="Source field: movies_genres.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888984Z", comments="Source field: movies_genres.genre_id")
    private Integer genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888934Z", comments="Source field: movies_genres.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888961Z", comments="Source field: movies_genres.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889008Z", comments="Source field: movies_genres.genre_id")
    public Integer getGenreId() {
        return genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889034Z", comments="Source field: movies_genres.genre_id")
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}