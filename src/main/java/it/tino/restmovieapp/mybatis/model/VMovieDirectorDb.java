package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieDirectorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890259Z", comments="Source field: v_movies_directors.director_id")
    private Integer directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890338Z", comments="Source field: v_movies_directors.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890418Z", comments="Source field: v_movies_directors.last_name")
    private String lastName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890494Z", comments="Source field: v_movies_directors.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890569Z", comments="Source field: v_movies_directors.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89064Z", comments="Source field: v_movies_directors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890709Z", comments="Source field: v_movies_directors.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890778Z", comments="Source field: v_movies_directors.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890854Z", comments="Source field: v_movies_directors.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890928Z", comments="Source field: v_movies_directors.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.891007Z", comments="Source field: v_movies_directors.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.891076Z", comments="Source field: v_movies_directors.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890289Z", comments="Source field: v_movies_directors.director_id")
    public Integer getDirectorId() {
        return directorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890316Z", comments="Source field: v_movies_directors.director_id")
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890363Z", comments="Source field: v_movies_directors.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89039Z", comments="Source field: v_movies_directors.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890444Z", comments="Source field: v_movies_directors.last_name")
    public String getLastName() {
        return lastName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890472Z", comments="Source field: v_movies_directors.last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890522Z", comments="Source field: v_movies_directors.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890547Z", comments="Source field: v_movies_directors.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890593Z", comments="Source field: v_movies_directors.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890619Z", comments="Source field: v_movies_directors.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890663Z", comments="Source field: v_movies_directors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890688Z", comments="Source field: v_movies_directors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890732Z", comments="Source field: v_movies_directors.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890757Z", comments="Source field: v_movies_directors.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890807Z", comments="Source field: v_movies_directors.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890833Z", comments="Source field: v_movies_directors.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890877Z", comments="Source field: v_movies_directors.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890906Z", comments="Source field: v_movies_directors.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890959Z", comments="Source field: v_movies_directors.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.890985Z", comments="Source field: v_movies_directors.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89103Z", comments="Source field: v_movies_directors.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.891055Z", comments="Source field: v_movies_directors.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.891099Z", comments="Source field: v_movies_directors.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.891123Z", comments="Source field: v_movies_directors.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}