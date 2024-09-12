package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class ReviewDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433740733+02:00", comments="Source field: reviews.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433848063+02:00", comments="Source field: reviews.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433935666+02:00", comments="Source field: reviews.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434026404+02:00", comments="Source field: reviews.creation_date")
    private Date creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434130969+02:00", comments="Source field: reviews.vote")
    private Float vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434257073+02:00", comments="Source field: reviews.review")
    private String review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433784534+02:00", comments="Source field: reviews.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433818898+02:00", comments="Source field: reviews.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.43387907+02:00", comments="Source field: reviews.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433910699+02:00", comments="Source field: reviews.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433965822+02:00", comments="Source field: reviews.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.433998633+02:00", comments="Source field: reviews.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434058674+02:00", comments="Source field: reviews.creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434088971+02:00", comments="Source field: reviews.creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434161446+02:00", comments="Source field: reviews.vote")
    public Float getVote() {
        return vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434227258+02:00", comments="Source field: reviews.vote")
    public void setVote(Float vote) {
        this.vote = vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434284114+02:00", comments="Source field: reviews.review")
    public String getReview() {
        return review;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.434315702+02:00", comments="Source field: reviews.review")
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}