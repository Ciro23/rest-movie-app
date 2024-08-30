package it.tino.postgres.review;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;

public class ReviewManagerTester extends DataManagerTester<Review, Integer> {

	private final ReviewManager reviewManager;
	
    public ReviewManagerTester(ReviewManager movieManager) {
        this.reviewManager = movieManager;
    }
    
    @Override
	protected Supplier<List<Review>> onSelectAll() {
		return reviewManager::selectAll;
	}

	@Override
	protected Function<Review, Review> onInsert() {
		return reviewManager::insert;
	}

	@Override
	protected Function<Review, Review> onUpdate() {
		return reviewManager::update;
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return reviewManager::delete;
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
