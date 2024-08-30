package it.tino.postgres.user;


import it.tino.postgres.ErrorResponse;
import it.tino.postgres.MovieApp;
import it.tino.postgres.database.ConnectionManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("users")
public class UserController {

    private final UserManager userManager;

    public UserController() {
        userManager = new UserManager(ConnectionManager.getInstance());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userManager.selectAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        User user = userManager.selectById(id);
        if (user == null) {
            return userNotFound(id, uriInfo);
        }
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createNew(User user) {
        return userManager.insert(user);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") int id, User user) {
        user.setId(id);
        User existingUser = userManager.selectById(id);
        if (existingUser == null) {
            return userManager.insert(user);
        }
        return userManager.update(user);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {
        User existingUser = userManager.selectById(id);
        if (existingUser == null) {
            return userNotFound(id, uriInfo);
        }

        boolean result = userManager.delete(id);
        if (!result) {
            return MovieApp.serverError(uriInfo);
        }

        return Response
                .noContent()
                .build();
    }

    private Response userNotFound(int userId, @Context UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("about:blank")
                .setTitle("User not found")
                .setDetail("User with id '" + userId + "' does not exist in the database")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
