package it.tino.restmovieapp.movie;

import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.MovieApp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;
import java.util.List;

@Path("movies")
public class MovieController {

    private final MovieManager movieManager;

    @Inject
    public MovieController(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(
        @QueryParam("directorId") int directorId,
        @QueryParam("actorId") int actorId,
        @QueryParam("genreId") int genreId
    ) {
        if (directorId == 0 && actorId == 0 && genreId == 0) {
            return movieManager.selectAll();
        }

        List<Movie> filteredMovies = new ArrayList<>();
        if (directorId != 0) {
            filteredMovies.addAll(movieManager.selectByDirectorId(directorId));
        }

        if (actorId != 0) {
            List<Movie> actorMovies = movieManager.selectByActorId(actorId);
            if (filteredMovies.isEmpty()) {
                filteredMovies.addAll(actorMovies);
            } else {
                filteredMovies.retainAll(actorMovies);
            }
        }

        if (genreId != 0) {
            List<Movie> genreMovies = movieManager.selectByGenreId(genreId);
            if (filteredMovies.isEmpty()) {
                filteredMovies.addAll(genreMovies);
            } else {
                filteredMovies.retainAll(genreMovies);
            }
        }

        return filteredMovies;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Movie movie = movieManager.selectById(id);
        if (movie == null) {
            return movieNotFound(id, uriInfo);
        }
        return Response.ok(movie).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Movie createNew(Movie movie) {
        return movieManager.insert(movie);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie update(@PathParam("id") int id, Movie movie) {
        movie.setId(id);
        Movie existingMovie = movieManager.selectById(id);
        if (existingMovie == null) {
            return movieManager.insert(movie);
        }
        return movieManager.update(movie);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Movie existingMovie = movieManager.selectById(id);
        if (existingMovie == null) {
            return movieNotFound(id, uriInfo);
        }

        boolean result = movieManager.delete(id);
        if (!result) {
            return MovieApp.serverError(uriInfo);
        }

        return Response
                .noContent()
                .build();
    }

    private Response movieNotFound(int movieId, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("Movie not found")
                .setDetail("Movie with id '" + movieId + "' does not exist in the database")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
