package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieActorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645325551+02:00", comments="Source field: v_movies_actors.actor_id")
    private Integer actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645383249+02:00", comments="Source field: v_movies_actors.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645430446+02:00", comments="Source field: v_movies_actors.last_name")
    private String lastName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645477865+02:00", comments="Source field: v_movies_actors.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645523018+02:00", comments="Source field: v_movies_actors.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645574414+02:00", comments="Source field: v_movies_actors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645618987+02:00", comments="Source field: v_movies_actors.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645683837+02:00", comments="Source field: v_movies_actors.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645729813+02:00", comments="Source field: v_movies_actors.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.64577679+02:00", comments="Source field: v_movies_actors.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645827975+02:00", comments="Source field: v_movies_actors.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645870935+02:00", comments="Source field: v_movies_actors.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645920858+02:00", comments="Source field: v_movies_actors.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645965331+02:00", comments="Source field: v_movies_actors.cast_order")
    private Integer castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645350277+02:00", comments="Source field: v_movies_actors.actor_id")
    public Integer getActorId() {
        return actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645368932+02:00", comments="Source field: v_movies_actors.actor_id")
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645398978+02:00", comments="Source field: v_movies_actors.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645414587+02:00", comments="Source field: v_movies_actors.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645446015+02:00", comments="Source field: v_movies_actors.last_name")
    public String getLastName() {
        return lastName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645462626+02:00", comments="Source field: v_movies_actors.last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645495227+02:00", comments="Source field: v_movies_actors.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645511437+02:00", comments="Source field: v_movies_actors.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645537926+02:00", comments="Source field: v_movies_actors.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645563123+02:00", comments="Source field: v_movies_actors.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645589332+02:00", comments="Source field: v_movies_actors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645607125+02:00", comments="Source field: v_movies_actors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645634917+02:00", comments="Source field: v_movies_actors.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645656176+02:00", comments="Source field: v_movies_actors.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645700589+02:00", comments="Source field: v_movies_actors.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645715987+02:00", comments="Source field: v_movies_actors.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645746684+02:00", comments="Source field: v_movies_actors.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645761291+02:00", comments="Source field: v_movies_actors.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645797819+02:00", comments="Source field: v_movies_actors.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645815011+02:00", comments="Source field: v_movies_actors.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645843554+02:00", comments="Source field: v_movies_actors.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645859464+02:00", comments="Source field: v_movies_actors.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645885322+02:00", comments="Source field: v_movies_actors.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645908295+02:00", comments="Source field: v_movies_actors.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645936026+02:00", comments="Source field: v_movies_actors.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645953278+02:00", comments="Source field: v_movies_actors.role")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.645993353+02:00", comments="Source field: v_movies_actors.cast_order")
    public Integer getCastOrder() {
        return castOrder;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.646009493+02:00", comments="Source field: v_movies_actors.cast_order")
    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}