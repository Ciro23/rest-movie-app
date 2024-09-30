package it.tino.restmovieapp.person;


import it.tino.restmovieapp.CollectionsUtility;
import it.tino.restmovieapp.MovieApp;
import it.tino.restmovieapp.error.ErrorResponse;
import it.tino.restmovieapp.export.XlsxGenerator;
import it.tino.restmovieapp.movie.Movie;
import it.tino.restmovieapp.movie.MovieManager;
import it.tino.restmovieapp.mybatis.mapper.PersonDbDynamicSqlSupport;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

@Path("people")
public class PersonController {

    private final PersonManager personManager;
    private final MovieManager movieManager;

    @Inject
    public PersonController(PersonManager personManager, MovieManager movieManager) {
        this.personManager = personManager;
        this.movieManager = movieManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Person> getAll(
        @QueryParam("name") String name,
        @QueryParam("gender") String gender,
        @QueryParam("birthStart") String birthStart,
        @QueryParam("birthEnd") String birthEnd
    ) throws ParseException {
        return new TreeSet<>(filterPeople(
                name,
                gender,
                birthStart,
                birthEnd
        ));
    }

    @GET
    @Path("xlsx")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportPeople(
        @QueryParam("name") String name,
        @QueryParam("gender") String gender,
        @QueryParam("birthStart") String birthStart,
        @QueryParam("birthEnd") String birthEnd
    ) throws ParseException {
        Set<Person> people = new TreeSet<>(filterPeople(
                name,
                gender,
                birthStart,
                birthEnd
        ));
        Set<PersonXlsx> peopleXlsx = new TreeSet<>();

        for (Person person : people) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String birthFormatted = dateTimeFormatter.format(person.getBirth());

            PersonXlsx personXlsx = new PersonXlsx();
            personXlsx.setId(person.getId());
            personXlsx.setName(person.getName());
            personXlsx.setLastName(person.getLastName());
            personXlsx.setBirth(birthFormatted);
            personXlsx.setGender(person.getGender());
            peopleXlsx.add(personXlsx);
        }

        byte[] excelContent = XlsxGenerator.generateXlsx(peopleXlsx, "People");
        return Response.ok(excelContent)
                .header("Content-Disposition", "attachment; filename=people.xlsx")
                .build();
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

    @GET
    @Path("{personId}/directed-movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getDirectedMoviesByPersonId(@PathParam("personId") int personId) {
        return movieManager.selectByDirectorId(personId);
    }

    @GET
    @Path("{personId}/starred-movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getStarredMoviesByPersonId(@PathParam("personId") int personId) {
        return movieManager.selectByActorId(personId);
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

    private List<Person> filterPeople(
        String name,
        String gender,
        String birthStart,
        String birthEnd
    ) throws ParseException {
        if (
            name == null
            && gender == null
            && birthStart == null
            && birthEnd == null
        ) {
            return personManager.selectAll();
        }

        List<Person> filteredPeople = new ArrayList<>();

        if (name != null) {
            List<Person> people = personManager.selectByCriteria(c -> c
                    .where(
                        PersonDbDynamicSqlSupport.name,
                        SqlBuilder.isLikeCaseInsensitive("%" + name + "%")
                    ).or(
                        PersonDbDynamicSqlSupport.lastName,
                        SqlBuilder.isLikeCaseInsensitive("%" + name + "%")
                    ).or(
                        SqlBuilder.concat(PersonDbDynamicSqlSupport.name, SqlBuilder.constant("' '"), PersonDbDynamicSqlSupport.lastName),
                        SqlBuilder.isLikeCaseInsensitive("%" + name + "%")
                    ).or(
                        SqlBuilder.concat(PersonDbDynamicSqlSupport.lastName, SqlBuilder.constant("' '"), PersonDbDynamicSqlSupport.name),
                        SqlBuilder.isLikeCaseInsensitive("%" + name + "%")
                    )
            );
            CollectionsUtility.addOrRetain(filteredPeople, people);
        }

        if (gender != null) {
            // TODO: codice orribile. fare refactoring per gestione gender nelle api.
            Person.Gender genderEnum;
            if (gender.equalsIgnoreCase("male")) {
                genderEnum = Person.Gender.MALE;
            } else {
                genderEnum = Person.Gender.FEMALE;
            }

            List<Person> people = personManager.selectByCriteria(c -> c.where(
                    PersonDbDynamicSqlSupport.gender,
                    SqlBuilder.isEqualTo(genderEnum.getId())
            ));
            CollectionsUtility.addOrRetain(filteredPeople, people);
        }

        Function<SelectDSLCompleter, List<Person>> onSelect = personManager::selectByCriteria;

        Pair<String, String> birthRange = new Pair<>(birthStart, birthEnd);
        CollectionsUtility.filterByStringDateRange(PersonDbDynamicSqlSupport.birth, birthRange, onSelect)
                .ifPresent(values -> CollectionsUtility.addOrRetain(filteredPeople, values));

        return filteredPeople;
    }
}
