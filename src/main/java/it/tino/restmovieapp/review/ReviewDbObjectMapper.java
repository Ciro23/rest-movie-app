package it.tino.restmovieapp.review;

import it.tino.restmovieapp.DateConverter;
import it.tino.restmovieapp.ObjectMapper;
import it.tino.restmovieapp.mybatis.model.ReviewDb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReviewDbObjectMapper implements ObjectMapper<Review, ReviewDb> {

    @Override
    public List<Review> sourceToDomain(Collection<ReviewDb> source) {
        List<Review> reviews = new ArrayList<>();
        for (ReviewDb dbEntity : source) {
            LocalDateTime localDateTime = DateConverter.dateToLocalDateTime(dbEntity.getCreationDate());
            Review review = new Review();

            review.setId(dbEntity.getId());
            review.setMovieId(dbEntity.getMovieId());
            review.setUserId(dbEntity.getUserId());
            review.setCreationDate(localDateTime);
            review.setVote(dbEntity.getVote());
            review.setContent(dbEntity.getReview());
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<ReviewDb> domainToTarget(Collection<Review> domainEntities) {
        List<ReviewDb> reviewsDb = new ArrayList<>();
        for (Review domainEntity : domainEntities) {
            Date convertedCreationDate = null;
            if (domainEntity.getCreationDate() != null) {
                convertedCreationDate = DateConverter.localDateTimeToDate(domainEntity.getCreationDate());
            }

            ReviewDb reviewDb = new ReviewDb();
            reviewDb.setId(domainEntity.getId());
            reviewDb.setMovieId(domainEntity.getMovieId());
            reviewDb.setUserId(domainEntity.getUserId());
            reviewDb.setCreationDate(convertedCreationDate);
            reviewDb.setVote(domainEntity.getVote());
            reviewDb.setReview(domainEntity.getContent());
            reviewsDb.add(reviewDb);
        }
        return reviewsDb;
    }
}
