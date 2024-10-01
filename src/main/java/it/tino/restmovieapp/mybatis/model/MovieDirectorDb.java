package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieDirectorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639508178+02:00", comments="Source field: movies_directors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639567578+02:00", comments="Source field: movies_directors.director_id")
    private Integer directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639532152+02:00", comments="Source field: movies_directors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639553201+02:00", comments="Source field: movies_directors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639586193+02:00", comments="Source field: movies_directors.director_id")
    public Integer getDirectorId() {
        return directorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639604938+02:00", comments="Source field: movies_directors.director_id")
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
}