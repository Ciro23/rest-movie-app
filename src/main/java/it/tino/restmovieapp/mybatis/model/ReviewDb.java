package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class ReviewDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.867942Z", comments="Source field: reviews.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868183Z", comments="Source field: reviews.movie_id")
    private Integer movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868352Z", comments="Source field: reviews.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868535Z", comments="Source field: reviews.creation_date")
    private Date creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.86871Z", comments="Source field: reviews.vote")
    private Float vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868874Z", comments="Source field: reviews.review")
    private String review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868063Z", comments="Source field: reviews.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868129Z", comments="Source field: reviews.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868238Z", comments="Source field: reviews.movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868301Z", comments="Source field: reviews.movie_id")
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868406Z", comments="Source field: reviews.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868481Z", comments="Source field: reviews.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868601Z", comments="Source field: reviews.creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868659Z", comments="Source field: reviews.creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868768Z", comments="Source field: reviews.vote")
    public Float getVote() {
        return vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868824Z", comments="Source field: reviews.vote")
    public void setVote(Float vote) {
        this.vote = vote;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868927Z", comments="Source field: reviews.review")
    public String getReview() {
        return review;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.868989Z", comments="Source field: reviews.review")
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}