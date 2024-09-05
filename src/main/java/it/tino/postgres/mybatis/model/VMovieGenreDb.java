package it.tino.postgres.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieGenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450219165+02:00", comments="Source field: v_movies_genres.genre_id")
    private Integer genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450278245+02:00", comments="Source field: v_movies_genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450321876+02:00", comments="Source field: v_movies_genres.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450366509+02:00", comments="Source field: v_movies_genres.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450414288+02:00", comments="Source field: v_movies_genres.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450535924+02:00", comments="Source field: v_movies_genres.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450582401+02:00", comments="Source field: v_movies_genres.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450660055+02:00", comments="Source field: v_movies_genres.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450706081+02:00", comments="Source field: v_movies_genres.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.45024347+02:00", comments="Source field: v_movies_genres.genre_id")
    public Integer getGenreId() {
        return genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450265892+02:00", comments="Source field: v_movies_genres.genre_id")
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450294435+02:00", comments="Source field: v_movies_genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450310204+02:00", comments="Source field: v_movies_genres.name")
    public void setName(Object name) {
        this.name = name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450338006+02:00", comments="Source field: v_movies_genres.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450353906+02:00", comments="Source field: v_movies_genres.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450381667+02:00", comments="Source field: v_movies_genres.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450400042+02:00", comments="Source field: v_movies_genres.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450436509+02:00", comments="Source field: v_movies_genres.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450519093+02:00", comments="Source field: v_movies_genres.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450550521+02:00", comments="Source field: v_movies_genres.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450567303+02:00", comments="Source field: v_movies_genres.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450607017+02:00", comments="Source field: v_movies_genres.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450643875+02:00", comments="Source field: v_movies_genres.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450675955+02:00", comments="Source field: v_movies_genres.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450692185+02:00", comments="Source field: v_movies_genres.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450722171+02:00", comments="Source field: v_movies_genres.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450741016+02:00", comments="Source field: v_movies_genres.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}