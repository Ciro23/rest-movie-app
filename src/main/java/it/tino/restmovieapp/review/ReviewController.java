package it.tino.restmovieapp.review;


import it.tino.restmovieapp.CollectionsUtility;
import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.export.XlsxGenerator;
import it.tino.restmovieapp.mybatis.mapper.ReviewDbDynamicSqlSupport;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import kotlin.Pair;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

@Path("reviews")
public class ReviewController {

    private final ReviewManager reviewManager;
    private final ReviewJsonMapper reviewJsonMapper;

    @Inject
    public ReviewController(
        ReviewManager reviewManager,
        ReviewJsonMapper reviewJsonMapper
    ) {
        this.reviewManager = reviewManager;
        this.reviewJsonMapper = reviewJsonMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReviewJson> getAll(
        @QueryParam("movieId") List<Integer> movieIds,
        @QueryParam("userId") List<Integer> userIds,
        @QueryParam("creationDateStart") String creationDateStart,
        @QueryParam("creationDateEnd") String creationDateEnd,
        @QueryParam("voteStart") Float voteStart,
        @QueryParam("voteEnd") Float voteEnd
    ) throws ParseException {
        return filterReviews(
                movieIds,
                userIds,
                creationDateStart,
                creationDateEnd,
                voteStart,
                voteEnd
        );
    }

    @GET
    @Path("xlsx")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportGenres(
        @QueryParam("movieId") List<Integer> movieIds,
        @QueryParam("userId") List<Integer> userIds,
        @QueryParam("creationDateStart") String creationDateStart,
        @QueryParam("creationDateEnd") String creationDateEnd,
        @QueryParam("voteStart") Float voteStart,
        @QueryParam("voteEnd") Float voteEnd
    ) throws ParseException {
        List<ReviewJson> reviews = filterReviews(
                movieIds,
                userIds,
                creationDateStart,
                creationDateEnd,
                voteStart,
                voteEnd
        );
        Set<ReviewXlsx> reviewsXlsx = new TreeSet<>();

        for (ReviewJson review : reviews) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String creationFormatted = "";
            if (review.getCreationDate() != null) {
                creationFormatted = dateTimeFormatter.format(review.getCreationDate());
            }

            ReviewXlsx reviewXlsx = new ReviewXlsx();
            reviewXlsx.setId(review.getId());
            reviewXlsx.setMovie(review.getMovie().getTitle());
            reviewXlsx.setUser(review.getUser().getEmail());
            reviewXlsx.setCreationDate(creationFormatted);
            reviewXlsx.setVote(review.getVote());
            reviewXlsx.setContent(review.getContent());
            reviewsXlsx.add(reviewXlsx);
        }

        byte[] excelContent = XlsxGenerator.generateXlsx(reviewsXlsx, "Reviews");
        return Response.ok(excelContent)
                .header("Content-Disposition", "attachment; filename=reviews.xlsx")
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Review review = reviewManager.selectById(id);
        if (review == null) {
            return reviewNotFound(id, uriInfo);
        }

        ReviewJson reviewJson = reviewJsonMapper.domainToTarget(review);
        return Response.ok(reviewJson).build();
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
        ReviewJson reviewJson = reviewJsonMapper.domainToTarget(newReview);
        return Response
                .ok(reviewJson)
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

        ReviewJson reviewJson = reviewJsonMapper.domainToTarget(updatedReview);
        return Response
                .ok(reviewJson)
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
        return !reviewManager.selectByCriteria(c -> c
                .where(ReviewDbDynamicSqlSupport.userId, SqlBuilder.isEqualTo(review.getUserId()))
                .and(ReviewDbDynamicSqlSupport.movieId, SqlBuilder.isEqualTo(review.getMovieId()))
        ).isEmpty();
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

    private List<ReviewJson> filterReviews(
        List<Integer> movieIds,
        List<Integer> userIds,
        String creationDateStart,
        String creationDateEnd,
        Float voteStart,
        Float voteEnd
    ) throws ParseException {
        if (
            movieIds.isEmpty()
            && userIds.isEmpty()
            && creationDateStart == null
            && creationDateEnd == null
            && voteStart == null
            && voteEnd == null
        ) {
            return reviewJsonMapper.domainToTarget(reviewManager.selectAll());
        }

        List<Review> filteredReviews = new ArrayList<>();
        Function<SelectDSLCompleter, List<Review>> onSelect = reviewManager::selectByCriteria;

        if (!movieIds.isEmpty()) {
            List<Review> reviews = reviewManager.selectByCriteria(c -> c
                    .where(ReviewDbDynamicSqlSupport.movieId, SqlBuilder.isIn(movieIds))
            );
            CollectionsUtility.addOrRetain(filteredReviews, reviews);
        }

        if (!userIds.isEmpty()) {
            List<Review> reviews = reviewManager.selectByCriteria(c -> c
                    .where(ReviewDbDynamicSqlSupport.userId, SqlBuilder.isIn(userIds))
            );
            CollectionsUtility.addOrRetain(filteredReviews, reviews);
        }

        Pair<String, String> creationDateRange = new Pair<>(creationDateStart, creationDateEnd);
        CollectionsUtility.filterByStringDateRange(ReviewDbDynamicSqlSupport.creationDate, creationDateRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredReviews, values));

        Pair<Float, Float> voteRange = new Pair<>(voteStart, voteEnd);
        CollectionsUtility.filterByRange(ReviewDbDynamicSqlSupport.vote, voteRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredReviews, values));

        return reviewJsonMapper.domainToTarget(filteredReviews);
    }
}
