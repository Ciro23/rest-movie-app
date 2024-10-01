package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class ReviewDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.631916045+02:00", comments="Source field: reviews.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632024957+02:00", comments="Source field: reviews.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632136675+02:00", comments="Source field: reviews.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63222023+02:00", comments="Source field: reviews.creation_date")
    private Date creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632312061+02:00", comments="Source field: reviews.vote")
    private Float vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632404222+02:00", comments="Source field: reviews.review")
    private String review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.631958484+02:00", comments="Source field: reviews.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.6319942+02:00", comments="Source field: reviews.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632061335+02:00", comments="Source field: reviews.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632107891+02:00", comments="Source field: reviews.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632164436+02:00", comments="Source field: reviews.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632194482+02:00", comments="Source field: reviews.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632251719+02:00", comments="Source field: reviews.creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632286824+02:00", comments="Source field: reviews.creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632337909+02:00", comments="Source field: reviews.vote")
    public Float getVote() {
        return vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632366602+02:00", comments="Source field: reviews.vote")
    public void setVote(Float vote) {
        this.vote = vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632430902+02:00", comments="Source field: reviews.review")
    public String getReview() {
        return review;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.632464094+02:00", comments="Source field: reviews.review")
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}