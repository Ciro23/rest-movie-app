package it.tino.restmovieapp.error;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnexpectedExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {
        System.out.println(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500")
                .setTitle("Unexpected internal server error")
                .setDetail(exception.getMessage())
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

