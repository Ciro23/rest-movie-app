package it.tino.restmovieapp.person;


import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.MovieApp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("people")
public class PersonController {

    private final PersonManager personManager;

    @Inject
    public PersonController(PersonManager personManager) {
        this.personManager = personManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll(@QueryParam("movieId") int movieId) {
        if (movieId == 0) {
            return personManager.selectAll();
        }

        return personManager.selectByMovieId(movieId);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Person person = personManager.selectById(id);
        if (person == null) {
            return personNotFound(id, uriInfo);
        }
        return Response.ok(person).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person createNew(Person person) {
        return personManager.insert(person);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person update(@PathParam("id") int id, Person person) {
        person.setId(id);
        Person existingPerson = personManager.selectById(id);
        if (existingPerson == null) {
            return personManager.insert(person);
        }
        return personManager.update(person);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Person existingPerson = personManager.selectById(id);
        if (existingPerson == null) {
            return personNotFound(id, uriInfo);
        }

        boolean result = personManager.delete(id);
        if (!result) {
            return MovieApp.serverError(uriInfo);
        }

        return Response
                .noContent()
                .build();
    }

    private Response personNotFound(int personId, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("Person not found")
                .setDetail("Person with id '" + personId + "' does not exist in the database")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
