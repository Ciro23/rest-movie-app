package it.tino.postgres.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class VMovieActorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447896685+02:00", comments="Source field: v_movies_actors.actor_id")
    private Integer actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447972356+02:00", comments="Source field: v_movies_actors.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448025174+02:00", comments="Source field: v_movies_actors.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44807683+02:00", comments="Source field: v_movies_actors.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448122906+02:00", comments="Source field: v_movies_actors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448166677+02:00", comments="Source field: v_movies_actors.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448212763+02:00", comments="Source field: v_movies_actors.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448263337+02:00", comments="Source field: v_movies_actors.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448307659+02:00", comments="Source field: v_movies_actors.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448350078+02:00", comments="Source field: v_movies_actors.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448398188+02:00", comments="Source field: v_movies_actors.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448455825+02:00", comments="Source field: v_movies_actors.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448505818+02:00", comments="Source field: v_movies_actors.cast_order")
    private Integer castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447934555+02:00", comments="Source field: v_movies_actors.actor_id")
    public Integer getActorId() {
        return actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447957909+02:00", comments="Source field: v_movies_actors.actor_id")
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447990359+02:00", comments="Source field: v_movies_actors.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448011799+02:00", comments="Source field: v_movies_actors.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448043278+02:00", comments="Source field: v_movies_actors.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448061151+02:00", comments="Source field: v_movies_actors.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448091026+02:00", comments="Source field: v_movies_actors.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448108669+02:00", comments="Source field: v_movies_actors.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448136551+02:00", comments="Source field: v_movies_actors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448152932+02:00", comments="Source field: v_movies_actors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448182447+02:00", comments="Source field: v_movies_actors.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448198246+02:00", comments="Source field: v_movies_actors.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448229885+02:00", comments="Source field: v_movies_actors.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448250233+02:00", comments="Source field: v_movies_actors.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448279257+02:00", comments="Source field: v_movies_actors.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448296118+02:00", comments="Source field: v_movies_actors.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448322798+02:00", comments="Source field: v_movies_actors.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448338687+02:00", comments="Source field: v_movies_actors.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448365888+02:00", comments="Source field: v_movies_actors.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44838332+02:00", comments="Source field: v_movies_actors.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448411984+02:00", comments="Source field: v_movies_actors.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448438603+02:00", comments="Source field: v_movies_actors.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448470352+02:00", comments="Source field: v_movies_actors.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.448491531+02:00", comments="Source field: v_movies_actors.role")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44852289+02:00", comments="Source field: v_movies_actors.cast_order")
    public Integer getCastOrder() {
        return castOrder;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44853902+02:00", comments="Source field: v_movies_actors.cast_order")
    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}