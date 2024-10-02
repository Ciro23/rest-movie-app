package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieGenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896626Z", comments="Source field: v_movies_genres.genre_id")
    private Integer genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896701Z", comments="Source field: v_movies_genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896766Z", comments="Source field: v_movies_genres.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896832Z", comments="Source field: v_movies_genres.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896897Z", comments="Source field: v_movies_genres.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896961Z", comments="Source field: v_movies_genres.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897024Z", comments="Source field: v_movies_genres.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897087Z", comments="Source field: v_movies_genres.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89715Z", comments="Source field: v_movies_genres.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896653Z", comments="Source field: v_movies_genres.genre_id")
    public Integer getGenreId() {
        return genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896681Z", comments="Source field: v_movies_genres.genre_id")
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896723Z", comments="Source field: v_movies_genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896746Z", comments="Source field: v_movies_genres.name")
    public void setName(Object name) {
        this.name = name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896789Z", comments="Source field: v_movies_genres.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896812Z", comments="Source field: v_movies_genres.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896854Z", comments="Source field: v_movies_genres.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896877Z", comments="Source field: v_movies_genres.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89692Z", comments="Source field: v_movies_genres.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896942Z", comments="Source field: v_movies_genres.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.896982Z", comments="Source field: v_movies_genres.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897005Z", comments="Source field: v_movies_genres.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897046Z", comments="Source field: v_movies_genres.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897068Z", comments="Source field: v_movies_genres.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897109Z", comments="Source field: v_movies_genres.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897131Z", comments="Source field: v_movies_genres.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897176Z", comments="Source field: v_movies_genres.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897202Z", comments="Source field: v_movies_genres.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}