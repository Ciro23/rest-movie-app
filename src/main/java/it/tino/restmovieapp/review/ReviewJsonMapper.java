package it.tino.restmovieapp.review;

import it.tino.restmovieapp.ObjectMapper;
import it.tino.restmovieapp.movie.Movie;
import it.tino.restmovieapp.movie.MovieManager;
import it.tino.restmovieapp.mybatis.mapper.MovieDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.UserDbDynamicSqlSupport;
import it.tino.restmovieapp.user.User;
import it.tino.restmovieapp.user.UserManager;
import jakarta.inject.Inject;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class ReviewJsonMapper implements ObjectMapper<Review, ReviewJson> {

    private final MovieManager movieManager;
    private final UserManager userManager;

    @Inject
    public ReviewJsonMapper(MovieManager movieManager, UserManager userManager) {
        this.movieManager = movieManager;
        this.userManager = userManager;
    }

    @Override
    public List<Review> sourceToDomain(Collection<ReviewJson> source) {
        List<Review> reviews = new ArrayList<>();
        for (ReviewJson reviewJson : source) {
            Review review = new Review();
            review.setId(reviewJson.getId());
            review.setMovieId(reviewJson.getMovie().getId());
            review.setUserId(reviewJson.getUser().getId());
            review.setCreationDate(reviewJson.getCreationDate());
            review.setVote(reviewJson.getVote());
            review.setContent(reviewJson.getContent());
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<ReviewJson> domainToTarget(Collection<Review> domainEntities) {
        Set<Integer> movieIds = domainEntities
                .stream()
                .map(Review::getMovieId)
                .collect(Collectors.toSet());

        Set<Integer> userIds = domainEntities
                .stream()
                .map(Review::getUserId)
                .collect(Collectors.toSet());

        List<Movie> movies = Collections.emptyList();
        if (!movieIds.isEmpty()) {
            movies = movieManager.selectByCriteria(c -> c
                    .where(MovieDbDynamicSqlSupport.id, SqlBuilder.isIn(movieIds))
            );
        }

        List<User> users = Collections.emptyList();
        if (!userIds.isEmpty()) {
            users = userManager.selectByCriteria(c -> c
                    .where(UserDbDynamicSqlSupport.id, SqlBuilder.isIn(userIds))
            );
        }

        List<ReviewJson> reviewJsons = new ArrayList<>();
        for (Review review : domainEntities) {
            Movie movie = movies
                    .stream()
                    .filter(m -> m.getId() == review.getMovieId())
                    .findAny()
                    .orElseThrow();

            User user = users
                    .stream()
                    .filter(u -> u.getId() == review.getUserId())
                    .findAny()
                    .orElseThrow();

            ReviewJson reviewJson = new ReviewJson();
            reviewJson.setId(review.getId());
            reviewJson.setMovie(movie);
            reviewJson.setUser(user);
            reviewJson.setCreationDate(review.getCreationDate());
            reviewJson.setVote(review.getVote());
            reviewJson.setContent(review.getContent());
            reviewJsons.add(reviewJson);
        }

        return reviewJsons;
    }
}
