package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieActorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.64092441+02:00", comments="Source field: movies_actors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640984812+02:00", comments="Source field: movies_actors.actor_id")
    private Integer actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641037651+02:00", comments="Source field: movies_actors.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641106048+02:00", comments="Source field: movies_actors.cast_order")
    private Integer castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640948375+02:00", comments="Source field: movies_actors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640969624+02:00", comments="Source field: movies_actors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641002956+02:00", comments="Source field: movies_actors.actor_id")
    public Integer getActorId() {
        return actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641022062+02:00", comments="Source field: movies_actors.actor_id")
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641063539+02:00", comments="Source field: movies_actors.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641085259+02:00", comments="Source field: movies_actors.role")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641122228+02:00", comments="Source field: movies_actors.cast_order")
    public Integer getCastOrder() {
        return castOrder;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.641142496+02:00", comments="Source field: movies_actors.cast_order")
    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}