package it.tino.postgres;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class MovieApp extends ResourceConfig {
    public MovieApp() {
        packages("it.tino.postgres");
        register(NotFoundExceptionMapper.class);
    }

    public static Response serverError(UriInfo uriInfo) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setType("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500")
                .setTitle("Unexpected server error")
                .setDetail("Something went wrong on our side")
                .setInstance(uriInfo.getAbsolutePath().toString());

        return Response
                .serverError()
                .entity(errorResponse).build();
    }
}
