package it.tino.restmovieapp.user;


import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.export.PdfGenerator;
import it.tino.restmovieapp.export.XlsxGenerator;
import it.tino.restmovieapp.mybatis.mapper.ReviewDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.UserDbDynamicSqlSupport;
import it.tino.restmovieapp.review.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import net.sf.jasperreports.engine.JRException;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.util.*;

@Path("users")
public class UserController {

    private final UserManager userManager;
    private final ReviewManager reviewManager;
    private final ReviewJsonMapper reviewJsonMapper;

    @Inject
    public UserController(UserManager userManager, ReviewManager reviewManager, ReviewJsonMapper reviewJsonMapper) {
        this.userManager = userManager;
        this.reviewManager = reviewManager;
        this.reviewJsonMapper = reviewJsonMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll(
        @QueryParam("username") String username,
        @QueryParam("email") String email
    ) {
       return filterUsers(username, email);
    }

    @GET
    @Path("xlsx")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportUsersXlsx(
        @QueryParam("username") String username,
        @QueryParam("email") String email
    ) {
        List<User> users = filterUsers(username, email);
        Set<UserXlsx> usersXlsx = new TreeSet<>();

        for (User user : users) {
            UserXlsx userXlsx = new UserXlsx();
            userXlsx.setId(user.getId());
            userXlsx.setUsername(user.getUsername());
            userXlsx.setEmail(user.getEmail());
            usersXlsx.add(userXlsx);
        }

        byte[] excelContent = XlsxGenerator.generateXlsx(usersXlsx, "Users");
        return Response.ok(excelContent)
                .header("Content-Disposition", "attachment; filename=users.xlsx")
                .build();
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

    @GET
    @Path("{userId}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReviewJson> getReviewsByUserId(@PathParam("userId") int userId) {
        List<Review> reviews = reviewManager.selectByCriteria(c -> c
                .where(ReviewDbDynamicSqlSupport.userId, SqlBuilder.isEqualTo(userId))
        );
        return reviewJsonMapper.domainToTarget(reviews);
    }

    @GET
    @Path("{id}/pdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportUserPdf(@PathParam("id") int id, @Context UriInfo uriInfo) throws JRException {
        User user = userManager.selectById(id);
        if (user == null) {
            return userNotFound(id, uriInfo);
        }

        byte[] pdfContent = PdfGenerator.generateUserPdf(user);
        return Response.ok(pdfContent)
                .header("Content-Disposition", "attachment; filename=" + user.getUsername() + ".pdf")
                .build();
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

    private List<User> filterUsers(String username, String email) {
        if (username == null && email == null) {
            return userManager.selectAll();
        }

        List<User> filteredUsers = new ArrayList<>();
        if (username != null) {
            List<User> users = userManager.selectByCriteria(c -> c.where(
                    UserDbDynamicSqlSupport.username,
                    SqlBuilder.isLikeCaseInsensitive("%" + username + "%")
            ));
            filteredUsers.addAll(users);
        }

        if (email != null) {
            List<User> users = userManager.selectByCriteria(c -> c.where(
                    UserDbDynamicSqlSupport.email,
                    SqlBuilder.isLikeCaseInsensitive("%" + email + "%")
            ));

            if (filteredUsers.isEmpty()) {
                filteredUsers.addAll(users);
            } else {
                filteredUsers.retainAll(users);
            }
        }

        return filteredUsers;
    }
}
