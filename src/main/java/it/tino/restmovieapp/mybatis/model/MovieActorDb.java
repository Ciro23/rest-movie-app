package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class MovieActorDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886856Z", comments="Source field: movies_actors.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886941Z", comments="Source field: movies_actors.actor_id")
    private Integer actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887011Z", comments="Source field: movies_actors.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887082Z", comments="Source field: movies_actors.cast_order")
    private Integer castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886889Z", comments="Source field: movies_actors.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886917Z", comments="Source field: movies_actors.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886965Z", comments="Source field: movies_actors.actor_id")
    public Integer getActorId() {
        return actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.886991Z", comments="Source field: movies_actors.actor_id")
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887035Z", comments="Source field: movies_actors.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887061Z", comments="Source field: movies_actors.role")
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887106Z", comments="Source field: movies_actors.cast_order")
    public Integer getCastOrder() {
        return castOrder;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887132Z", comments="Source field: movies_actors.cast_order")
    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}