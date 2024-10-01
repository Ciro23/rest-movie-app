package it.tino.restmovieapp.genre;


import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.PaginatedResponse;
import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.export.PdfGenerator;
import it.tino.restmovieapp.export.XlsxGenerator;
import it.tino.restmovieapp.mybatis.mapper.GenreDbDynamicSqlSupport;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import net.sf.jasperreports.engine.JRException;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Path("genres")
public class GenreController {

    private final GenreManager genreManager;

    @Inject
    public GenreController(GenreManager genreManager) {
        this.genreManager = genreManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedResponse<Genre> getAll(
        @QueryParam("name") String name,
        @QueryParam("page") @DefaultValue("1") Integer page,
        @QueryParam("size") @DefaultValue("10") Integer size,
        @QueryParam("sortField") @DefaultValue("name") String sortField,
        @QueryParam("sortDirection") @DefaultValue("asc") String sortDirection
    ) {
        return filterGenres(name, page, size, sortField, sortDirection);
    }

    @GET
    @Path("xlsx")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportGenres(@QueryParam("name") String name) {
        PaginatedResponse<Genre> genres = filterGenres(name, null, null, null, null);
        Set<GenreXlsx> genresXlsx = new TreeSet<>();

        for (Genre genre : genres.data()) {
            GenreXlsx genreXlsx = new GenreXlsx();
            genreXlsx.setId(genre.getId());
            genreXlsx.setName(genre.getName());
            genresXlsx.add(genreXlsx);
        }

        byte[] excelContent = XlsxGenerator.generateXlsx(genresXlsx, "Genres");
        return Response.ok(excelContent)
                .header("Content-Disposition", "attachment; filename=genres.xlsx")
                .build();
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

    @GET
    @Path("{id}/pdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportGenrePdf(@PathParam("id") int id, @Context UriInfo uriInfo) throws JRException {
        Genre genre = genreManager.selectById(id);
        if (genre == null) {
            return genreNotFound(id, uriInfo);
        }

        byte[] pdfContent = PdfGenerator.generateGenrePdf(genre);
        return Response.ok(pdfContent)
                .header("Content-Disposition", "attachment; filename=genre-" + genre.getId() + ".pdf")
                .build();
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

    private PaginatedResponse<Genre> filterGenres(
        String name,
        Integer page,
        Integer size,
        String sortField,
        String sortDirection
    ) {
        if (page == null || size == null) {
            if (name == null) {
                List<Genre> allGenres = genreManager.selectAll();
                return new PaginatedResponse<>(allGenres, allGenres.size());
            }

            List<Genre> filteredGenres = genreManager.selectByCriteria(c -> c.where(
                    GenreDbDynamicSqlSupport.name,
                    SqlBuilder.isLike("%" + name + "%")
            ));
            return new PaginatedResponse<>(filteredGenres, filteredGenres.size());
        }

        int offset = (page - 1) * size;
        if (name == null) {
            return genreManager.selectPaginated(offset, size, sortField, sortDirection);
        }

        SelectDSLCompleter selectDSLCompleter = c -> c.where(
                GenreDbDynamicSqlSupport.name,
                SqlBuilder.isLike("%" + name + "%")
        );
        CountDSLCompleter countDSLCompleter = c -> c.where(
                GenreDbDynamicSqlSupport.name,
                SqlBuilder.isLike("%" + name + "%")
        );
        return genreManager.selectPaginatedByCriteria(
                selectDSLCompleter,
                countDSLCompleter,
                offset,
                size,
                sortField,
                sortDirection
        );
    }
}
