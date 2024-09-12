package it.tino.restmovieapp.genre;


import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.MovieApp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("genres")
public class GenreController {

    private final GenreManager genreManager;

    @Inject
    public GenreController(GenreManager genreManager) {
        this.genreManager = genreManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getAll() {
        return genreManager.selectAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Genre genre = genreManager.selectById(id);
        if (genre == null) {
            return genreNotFound(id, uriInfo);
        }
        return Response.ok(genre).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Genre createNew(Genre genre) {
        return genreManager.insert(genre);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Genre update(@PathParam("id") int id, Genre genre) {
        genre.setId(id);
        Genre existingGenre = genreManager.selectById(id);
        if (existingGenre == null) {
            return genreManager.insert(genre);
        }
        return genreManager.update(genre);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Genre existingGenre = genreManager.selectById(id);
        if (existingGenre == null) {
            return genreNotFound(id, uriInfo);
        }

        boolean result = genreManager.delete(id);
        if (!result) {
            return MovieApp.serverError(uriInfo);
        }

        return Response
                .noContent()
                .build();
    }

    private Response genreNotFound(int genreId, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("Genre not found")
                .setDetail("Genre with id '" + genreId + "' does not exist in the database")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
