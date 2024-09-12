package it.tino.restmovieapp.movie;

import edu.umd.cs.findbugs.annotations.Nullable;
import it.tino.restmovieapp.CollectionsUtility;
import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.export.XlsxGenerator;
import it.tino.restmovieapp.genre.Genre;
import it.tino.restmovieapp.genre.GenreManager;
import it.tino.restmovieapp.mybatis.mapper.MovieDbDynamicSqlSupport;
import it.tino.restmovieapp.person.Person;
import it.tino.restmovieapp.person.PersonManager;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Path("movies")
public class MovieController {

    private final MovieManager movieManager;
    private final GenreManager genreManager;
    private final PersonManager personManager;

    @Inject
    public MovieController(
        MovieManager movieManager,
        GenreManager genreManager,
        PersonManager personManager
    ) {
        this.movieManager = movieManager;
        this.genreManager = genreManager;
        this.personManager = personManager;
    }

    /**
     * The query param for release date must be of format "yyyy-MM-dd".
     * @throws ParseException See {@link SimpleDateFormat#parse(String)}.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(
        @QueryParam("genreId") List<Integer> genreIds,
        @QueryParam("title") String title,
        @QueryParam("releaseDateStart") String releaseDateStart,
        @QueryParam("releaseDateEnd") String releaseDateEnd,
        @QueryParam("budgetStart") Integer budgetStart,
        @QueryParam("budgetEnd") Integer budgetEnd,
        @QueryParam("boxOfficeStart") Integer boxOfficeStart,
        @QueryParam("boxOfficeEnd") Integer boxOfficeEnd,
        @QueryParam("runtimeStart") Integer runtimeStart,
        @QueryParam("runtimeEnd") Integer runtimeEnd
    ) throws ParseException {
        return filterMovies(
                genreIds,
                title,
                releaseDateStart,
                releaseDateEnd,
                budgetStart,
                budgetEnd,
                boxOfficeStart,
                boxOfficeEnd,
                runtimeStart,
                runtimeEnd
        );
    }

    @GET
    @Path("xlsx")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportMoviesXlsx(
        @QueryParam("genreId") List<Integer> genreIds,
        @QueryParam("title") String title,
        @QueryParam("releaseDateStart") String releaseDateStart,
        @QueryParam("releaseDateEnd") String releaseDateEnd,
        @QueryParam("budgetStart") Integer budgetStart,
        @QueryParam("budgetEnd") Integer budgetEnd,
        @QueryParam("boxOfficeStart") Integer boxOfficeStart,
        @QueryParam("boxOfficeEnd") Integer boxOfficeEnd,
        @QueryParam("runtimeStart") Integer runtimeStart,
        @QueryParam("runtimeEnd") Integer runtimeEnd
    ) throws ParseException {
        List<Movie> movies = filterMovies(
                genreIds,
                title,
                releaseDateStart,
                releaseDateEnd,
                budgetStart,
                budgetEnd,
                boxOfficeStart,
                boxOfficeEnd,
                runtimeStart,
                runtimeEnd
        );
        byte[] excelContent = XlsxGenerator.generateXlsx(movies, "Movies");

        return Response.ok(excelContent)
                .header("Content-Disposition", "attachment; filename=movies.xlsx")
                .build();
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

    @GET
    @Path("{movieId}/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getGenresByMovieId(@PathParam("movieId") int movieId) {
        return genreManager.selectByMovieId(movieId);
    }

    @GET
    @Path("{id}/people")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Person> getPeopleByMovieId(@PathParam("id") int id) {
        // Since in a movie the director can also have a role as an actor,
        // the HashSet prevents duplicates.
        return new HashSet<>(personManager.selectByMovieId(id));
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

    private List<Movie> filterMovies(
        List<Integer> genreIds,
        @Nullable String title,
        @Nullable String releaseDateStart,
        @Nullable String releaseDateEnd,
        @Nullable Integer budgetStart,
        @Nullable Integer budgetEnd,
        @Nullable Integer boxOfficeStart,
        @Nullable Integer boxOfficeEnd,
        @Nullable Integer runtimeStart,
        @Nullable Integer runtimeEnd
    ) throws ParseException {
        if (
            genreIds.isEmpty()
            && title == null
            && releaseDateStart == null
            && releaseDateEnd == null
            && budgetStart == null
            && budgetEnd == null
            && boxOfficeStart == null
            && boxOfficeEnd == null
            && runtimeStart == null
            && runtimeEnd == null
        ) {
            return movieManager.selectAll();
        }

        List<Movie> filteredMovies = new ArrayList<>();
        if (!genreIds.isEmpty()) {
            List<Movie> genreMovies = movieManager.selectByGenreIdIn(genreIds);
            CollectionsUtility.addOrRetain(filteredMovies, genreMovies);
        }

        if (title != null) {
            List<Movie> movies = movieManager.selectByCriteria(c -> c.where(
                    MovieDbDynamicSqlSupport.title,
                    SqlBuilder.isLikeCaseInsensitive("%" + title + "%")
            ));
            CollectionsUtility.addOrRetain(filteredMovies, movies);
        }

        Function<SelectDSLCompleter, List<Movie>> onSelect = movieManager::selectByCriteria;

        Pair<String, String> releaseDateRange = new Pair<>(releaseDateStart, releaseDateEnd);
        CollectionsUtility.filterByStringDateRange(MovieDbDynamicSqlSupport.releaseDate, releaseDateRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredMovies, values));

        Pair<Integer, Integer> budgetRange = new Pair<>(budgetStart, budgetEnd);
        CollectionsUtility.filterByRange(MovieDbDynamicSqlSupport.budget, budgetRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredMovies, values));

        Pair<Integer, Integer> boxOfficeRange = new Pair<>(boxOfficeStart, boxOfficeEnd);
        CollectionsUtility.filterByRange(MovieDbDynamicSqlSupport.boxOffice, boxOfficeRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredMovies, values));

        Pair<Integer, Integer> runtimeRange = new Pair<>(runtimeStart, runtimeEnd);
        CollectionsUtility.filterByRange(MovieDbDynamicSqlSupport.runtime, runtimeRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredMovies, values));

        return filteredMovies;
    }
}
