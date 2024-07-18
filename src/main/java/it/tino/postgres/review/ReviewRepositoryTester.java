package it.tino.postgres.review;

import java.sql.Timestamp;

import it.tino.postgres.database.RepositoryTester;

public class ReviewRepositoryTester extends RepositoryTester<Review, Integer> {

    public ReviewRepositoryTester(ReviewRepository repository) {
        super(repository);
    }

    @Override
    protected Review onCreateObject() {
    	Review review = new Review();
    	review.setMovieId(1);
    	review.setUserId(1);
    	review.setCreationDate(new Timestamp(100000));
    	review.setVote(9.5);
    	review.setReview("My review");
    	
    	return review;
    }

    @Override
    protected void onUpdateObject(Review objectToUpdate) {
        objectToUpdate.setVote(4);
        objectToUpdate.setReview("My review (updated)");
    }
}
