package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class ReviewDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25398826+02:00", comments="Source field: reviews.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254096862+02:00", comments="Source field: reviews.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254192801+02:00", comments="Source field: reviews.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254279432+02:00", comments="Source field: reviews.creation_date")
    private Date creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25439673+02:00", comments="Source field: reviews.vote")
    private Float vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254500644+02:00", comments="Source field: reviews.review")
    private String review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254032943+02:00", comments="Source field: reviews.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254069321+02:00", comments="Source field: reviews.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25412793+02:00", comments="Source field: reviews.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254166011+02:00", comments="Source field: reviews.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254222566+02:00", comments="Source field: reviews.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254254556+02:00", comments="Source field: reviews.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25433734+02:00", comments="Source field: reviews.creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254370952+02:00", comments="Source field: reviews.creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254426265+02:00", comments="Source field: reviews.vote")
    public Float getVote() {
        return vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254473984+02:00", comments="Source field: reviews.vote")
    public void setVote(Float vote) {
        this.vote = vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254529297+02:00", comments="Source field: reviews.review")
    public String getReview() {
        return review;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.254560675+02:00", comments="Source field: reviews.review")
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}