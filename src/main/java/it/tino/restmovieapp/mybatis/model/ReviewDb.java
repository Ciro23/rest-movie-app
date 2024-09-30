package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class ReviewDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352166718+02:00", comments="Source field: reviews.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352274068+02:00", comments="Source field: reviews.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352369596+02:00", comments="Source field: reviews.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352456758+02:00", comments="Source field: reviews.creation_date")
    private Date creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35254374+02:00", comments="Source field: reviews.vote")
    private Float vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35267777+02:00", comments="Source field: reviews.review")
    private String review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352210049+02:00", comments="Source field: reviews.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352245555+02:00", comments="Source field: reviews.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352302571+02:00", comments="Source field: reviews.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352340021+02:00", comments="Source field: reviews.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352397738+02:00", comments="Source field: reviews.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352430239+02:00", comments="Source field: reviews.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35248979+02:00", comments="Source field: reviews.creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352517231+02:00", comments="Source field: reviews.creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352571392+02:00", comments="Source field: reviews.vote")
    public Float getVote() {
        return vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352627095+02:00", comments="Source field: reviews.vote")
    public void setVote(Float vote) {
        this.vote = vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352708026+02:00", comments="Source field: reviews.review")
    public String getReview() {
        return review;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352753501+02:00", comments="Source field: reviews.review")
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}