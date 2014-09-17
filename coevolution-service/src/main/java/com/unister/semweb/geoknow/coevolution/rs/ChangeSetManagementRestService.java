package com.unister.semweb.geoknow.coevolution.rs;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unister.semweb.geoknow.coevolution.config.AppConfig;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;
import com.unister.semweb.geoknow.coevolution.services.ChangeSetManagementService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/change")
@Api(value = "/change", description = "ChangeSet Management Service", position = 1)
public class ChangeSetManagementRestService {
    @Inject
    private ChangeSetManagementService changeSetManagementService;

    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE })
    @GET
    @ApiOperation(value = "List all change requests", notes = "List all change requests using paging", response = IdentifiedChangeRequest.class, responseContainer = "List")
    public Collection<IdentifiedChangeRequest> getChangeRequests(
            @ApiParam(value = "Graph URI where changesets are stored", required = true) @QueryParam("graph") final String graph,
            @ApiParam(value = "Subject of change, default any", required = false) @QueryParam("subject") final String subject,
            @ApiParam(value = "Page to fetch", required = true) @QueryParam("page") @DefaultValue("1") final int page,
            @ApiParam(value = "Page size", required = true) @QueryParam("pagesize") @DefaultValue("1000") final int pagesize) {
        return changeSetManagementService.getChangeRequests(graph, subject, page, pagesize);
    }

    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/version")
    @ApiOperation(value = "Get service API version", notes = "Returns the version of this service API, according to semantic versioning (semver.org)", response = String.class)
    public String getVersion() {
        return AppConfig.VERSION;
    }

    /**
     * Returns a Change request
     */
    @ApiOperation(value = "Returns a change request", notes = "Returns the change request identified by the given change set URI.")
    @GET
    @Path("/id/{changeIdentifier}")
    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    public IdentifiedChangeRequest getChangeRequst(
            @ApiParam(value = "Graph URI where changesets are stored", required = true) @QueryParam("graph") final String graph,
            @ApiParam(value = "Change request identifier GUID", required = true) @PathParam("changeIdentifier") final String identifier) {
        return null;
    }

    /**
     * Updates a Change request
     */
    @ApiOperation(value = "Updates a Change request", notes = "Updates the Change request.")
    @PUT
    @Path("/id/{changeIdentifier}")
    public void updateChangeRequest(
            @ApiParam(required = true, name = "change request", value = "The change request to be submitted") IdentifiedChangeRequest changeRequest,
            @ApiParam(required = true, name = "identifier", value = "The URI identifying the change request") @PathParam("changeIdentifier") String changeIdentifier,
            @ApiParam(required = true, name = "graph", value = "The changeset graph to store a changeset") @QueryParam("graph") String graph) {
    }

    /**
     * Updates a Change request
     */
    @ApiOperation(value = "Deletes a Change request", notes = "Deletes the Change request.")
    @DELETE
    @Path("/id/{changeIdentifier}")
    public void deleteChangeRequest(
            @ApiParam(required = true, name = "identifier", value = "The URI identifying the change request") @PathParam("changeIdentifier") String changeIdentifier,
            @ApiParam(required = true, name = "graph", value = "The changeset graph to store a changeset") @QueryParam("graph") String graph) {
    }

    /**
     * Creates a Change request
     */
    @ApiOperation(value = "Creates a change request", notes = "The change request needs to have at least one addition <i>or</i> removal. Returns the identifier (GUID) of the created changeset.")
    @POST
    public String createChangeRequest(
            @ApiParam(required = true, name = "change request", value = "The change request to be submitted") ChangeRequest changeRequest,
            @ApiParam(required = true, name = "graph", value = "The changeset graph to store a changeset") @QueryParam("graph") String graph) {
        return null;
    }
    
    /**
     * Lists all changeset subjects in a given graph.
     */
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE })
    @GET
    @Path("/subjects")
    @ApiOperation(value = "List all change request subjects", notes = "List all change request subjects using paging", response = String.class, responseContainer = "List")
    public Collection<String> getChangeRequestSubjects(
            @ApiParam(value = "Graph URI where changesets are stored", required = true) @QueryParam("graph") final String graph,
            @ApiParam(value = "Page to fetch", required = true) @QueryParam("page") @DefaultValue("1") final int page,
            @ApiParam(value = "Page size", required = true) @QueryParam("pagesize") @DefaultValue("1000") final int pagesize) {
//        return changeSetManagementService.getChangeRequests(graph, subject, page, pagesize);
        return null;
    }

    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    // @Path("/{email}")
    // @GET
    // @ApiOperation(value = "Find person by e-mail", notes = "Find person by e-mail", response = Person.class)
    // @ApiResponses({
    // @ApiResponse(code = 404, message = "Person with such e-mail doesn't exists")
    // })
    // public Person getPeople(
    // @ApiParam(value = "E-Mail address to lookup for", required = true) @PathParam("email") final String email) {
    // return peopleService.getByEmail(email);
    // }
    //
    // @Produces({ MediaType.APPLICATION_JSON })
    // @POST
    // @ApiOperation(value = "Create new person", notes = "Create new person")
    // @ApiResponses({
    // @ApiResponse(code = 201, message = "Person created successfully"),
    // @ApiResponse(code = 409, message = "Person with such e-mail already exists")
    // })
    // public Response addPerson(@Context final UriInfo uriInfo,
    // @ApiParam(value = "E-Mail", required = true) @FormParam("email") final String email,
    // @ApiParam(value = "First Name", required = true) @FormParam("firstName") final String firstName,
    // @ApiParam(value = "Last Name", required = true) @FormParam("lastName") final String lastName) {
    //
    // peopleService.addPerson(email, firstName, lastName);
    // return Response.created(uriInfo.getRequestUriBuilder().path(email).build()).build();
    // }
    //
    // @Produces({ MediaType.APPLICATION_JSON })
    // @Path("/{email}")
    // @PUT
    // @ApiOperation(value = "Update existing person", notes = "Update existing person", response = Person.class)
    // @ApiResponses({
    // @ApiResponse(code = 404, message = "Person with such e-mail doesn't exists")
    // })
    // public Person updatePerson(
    // @ApiParam(value = "E-Mail", required = true) @PathParam("email") final String email,
    // @ApiParam(value = "First Name", required = false) @FormParam("firstName") final String firstName,
    // @ApiParam(value = "First Name", required = false) @FormParam("lastName") final String lastName) {
    //
    // final Person person = peopleService.getByEmail(email);
    //
    // if (firstName != null) {
    // person.setFirstName(firstName);
    // }
    //
    // if (lastName != null) {
    // person.setLastName(lastName);
    // }
    //
    // return person;
    // }
    //
    // @Path("/{email}")
    // @DELETE
    // @ApiOperation(value = "Delete existing person", notes = "Delete existing person", response = Person.class)
    // @ApiResponses({
    // @ApiResponse(code = 404, message = "Person with such e-mail doesn't exists")
    // })
    // public Response deletePerson(@ApiParam(value = "E-Mail", required = true) @PathParam("email") final String email)
    // {
    // peopleService.removePerson(email);
    // return Response.ok().build();
    // }
    //
    // @Path("/info")
    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // @ApiOperation(value = "Gets info on implementation", response = String.class)
    // public Response getInfo() {
    // return Response.ok(new ChangeSetApplier().toString()).build();
    // }

}
