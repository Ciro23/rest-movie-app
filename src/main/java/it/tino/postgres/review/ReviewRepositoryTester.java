package it.tino.postgres.review;

import it.tino.postgres.database.RepositoryTester;

public class ReviewRepositoryTester extends RepositoryTester<Review, Integer> {

    public ReviewRepositoryTester(ReviewRepository repository) {
        super(repository);
    }

    @Override
    protected Review onCreateObject() {
        return new Review(0, 1, 1, 9.5, "My review");
    }

    @Override
    protected void onUpdateObject(Review objectToUpdate) {
        objectToUpdate.setVote(4);
        objectToUpdate.setReview("My review (updated)");
    }
}
