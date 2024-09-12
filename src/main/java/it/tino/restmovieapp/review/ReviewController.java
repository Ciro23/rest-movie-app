package it.tino.restmovieapp.review;


import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.mybatis.mapper.ReviewDbDynamicSqlSupport;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.util.List;

@Path("reviews")
public class ReviewController {

    private final ReviewManager reviewManager;

    @Inject
    public ReviewController(ReviewManager reviewManager) {
        this.reviewManager = reviewManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAll(@QueryParam("userId") int userId) {
        if (userId == 0) {
            return reviewManager.selectAll();
        }

        return reviewManager.selectByCriteria(c -> c.where(ReviewDbDynamicSqlSupport.id, SqlBuilder.isEqualTo(userId)));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Review review = reviewManager.selectById(id);
        if (review == null) {
            return reviewNotFound(id, uriInfo);
        }
        return Response.ok(review).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew(Review review, @Context UriInfo uriInfo) {
        if (isReviewVoteInvalid(review)) {
            return reviewInvalidVoteResponse(review, uriInfo);
        }

        if (hasUserAlreadyReviewed(review)) {
            return userAlreadyReviewedMovieResponse(review, uriInfo);
        }

        Review newReview = reviewManager.insert(review);
        return Response
                .ok(newReview)
                .build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Review review, @Context UriInfo uriInfo) {
        review.setId(id);

        if (isReviewVoteInvalid(review)) {
            return reviewInvalidVoteResponse(review, uriInfo);
        }

        Review updatedReview;
        Review existingReview = reviewManager.selectById(id);

        if (existingReview == null) {
            if (hasUserAlreadyReviewed(review)) {
                return userAlreadyReviewedMovieResponse(review, uriInfo);
            }

            updatedReview = reviewManager.insert(review);
        } else {
            updatedReview = reviewManager.update(review);
        }

        return Response
                .ok(updatedReview)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Review existingReview = reviewManager.selectById(id);
        if (existingReview == null) {
            return reviewNotFound(id, uriInfo);
        }

        boolean result = reviewManager.delete(id);
        if (!result) {
            return MovieApp.serverError(uriInfo);
        }

        return Response
                .noContent()
                .build();
    }

    /**
     * Each user can only add one review for each movie.
     * @param review The one which the user is trying to add.
     * @return true if the user already reviewed the movie, false otherwise.
     */
    private boolean hasUserAlreadyReviewed(Review review) {
        return !reviewManager.selectByCriteria(c -> c.where(ReviewDbDynamicSqlSupport.userId, SqlBuilder.isEqualTo(review.getUserId()))
                .and(ReviewDbDynamicSqlSupport.movieId, SqlBuilder.isEqualTo(review.getMovieId()))).isEmpty();
    }

    private Response userAlreadyReviewedMovieResponse(Review review, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("User already reviewed the movie")
                .setDetail("A review already exists for the user with id '" + review.getUserId() + "'" +
                        " and the movie with id '" + review.getMovieId() + "'")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

    private boolean isReviewVoteInvalid(Review review) {
        return review.getVote() < 0 || review.getVote() > 10;
    }

    private Response reviewInvalidVoteResponse(Review review, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("Invalid movie vote value")
                .setDetail("Vote '" + review.getVote() + "' assigned to the movie" +
                        " is not within legal values (0-10")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

    private Response reviewNotFound(int reviewId, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("Review not found")
                .setDetail("Review with id '" + reviewId + "' does not exist in the database")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
