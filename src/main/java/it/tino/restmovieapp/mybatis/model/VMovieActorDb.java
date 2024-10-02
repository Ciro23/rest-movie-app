package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieActorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893208Z", comments="Source field: v_movies_actors.actor_id")
    private Integer actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893286Z", comments="Source field: v_movies_actors.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893352Z", comments="Source field: v_movies_actors.last_name")
    private String lastName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893443Z", comments="Source field: v_movies_actors.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893527Z", comments="Source field: v_movies_actors.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893604Z", comments="Source field: v_movies_actors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893673Z", comments="Source field: v_movies_actors.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893742Z", comments="Source field: v_movies_actors.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893807Z", comments="Source field: v_movies_actors.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893875Z", comments="Source field: v_movies_actors.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893951Z", comments="Source field: v_movies_actors.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894014Z", comments="Source field: v_movies_actors.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894078Z", comments="Source field: v_movies_actors.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894142Z", comments="Source field: v_movies_actors.cast_order")
    private Integer castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893238Z", comments="Source field: v_movies_actors.actor_id")
    public Integer getActorId() {
        return actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893265Z", comments="Source field: v_movies_actors.actor_id")
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893308Z", comments="Source field: v_movies_actors.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893332Z", comments="Source field: v_movies_actors.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893374Z", comments="Source field: v_movies_actors.last_name")
    public String getLastName() {
        return lastName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893405Z", comments="Source field: v_movies_actors.last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893475Z", comments="Source field: v_movies_actors.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893505Z", comments="Source field: v_movies_actors.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.89355Z", comments="Source field: v_movies_actors.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893575Z", comments="Source field: v_movies_actors.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893629Z", comments="Source field: v_movies_actors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893653Z", comments="Source field: v_movies_actors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893696Z", comments="Source field: v_movies_actors.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893719Z", comments="Source field: v_movies_actors.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893766Z", comments="Source field: v_movies_actors.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893788Z", comments="Source field: v_movies_actors.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893829Z", comments="Source field: v_movies_actors.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893853Z", comments="Source field: v_movies_actors.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893907Z", comments="Source field: v_movies_actors.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893931Z", comments="Source field: v_movies_actors.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893972Z", comments="Source field: v_movies_actors.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.893995Z", comments="Source field: v_movies_actors.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894035Z", comments="Source field: v_movies_actors.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894059Z", comments="Source field: v_movies_actors.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894099Z", comments="Source field: v_movies_actors.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894122Z", comments="Source field: v_movies_actors.role")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894163Z", comments="Source field: v_movies_actors.cast_order")
    public Integer getCastOrder() {
        return castOrder;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.894184Z", comments="Source field: v_movies_actors.cast_order")
    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}