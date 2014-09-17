package com.unister.semweb.geoknow.coevolution.rs;

import java.util.Collection;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.openrdf.model.Model;

import com.unister.semweb.geoknow.coevolution.config.AppConfig;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/application")
@Api(value = "/application", description = "ChangeSet Application Service", position = 2)
public class ChangeSetApplicationRestService {

    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/version")
    @ApiOperation(value = "Get service API version", notes = "Returns the version of this service API, according to semantic versioning (semver.org)", response = String.class)
    public String getVersion() {
        return AppConfig.VERSION;
    }
    
    /**
     * Applies changesets
     */
    @ApiOperation(value = "Applies change requests", notes = "Returns the modified model, with changes from the given graph applied. All changes will be applied regarding the given context.")
    @POST
    public Model createChangeRequest(
            @ApiParam(required = true, name = "model", value = "The set of statements to be modified") Model model,
            @ApiParam(required = true, name = "context", value = "The context (graph) of the changes to be applied") @QueryParam("context") String context,
            @ApiParam(required = true, name = "graph", value = "The changeset graph to store a changeset") @QueryParam("graph") String graph) {
        return null;
    }
    
    /**
     * Returns all statements to be deleted and added when transitioning from source graph to target graph version.
     */
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE })
    @POST
    @Path("/graph")
    @ApiOperation(value = "Applies changes to a graph", notes = "Applies changes from a changeset graph to a source graph, optionally writing to a new target graph.")
    public ChangeRequest getDeleteSet(
            @ApiParam(value = "Graph URI where changesets are stored", required = true) @QueryParam("graph") final String graph,
            @ApiParam(required = true, name = "source", value = "The source context (graph)") @QueryParam("context") String sourceContext,
            @ApiParam(required = false, name = "target", value = "The target context (graph)") @QueryParam("context") String targetContext) {
//        return changeSetManagementService.getChangeRequests(graph, subject, page, pagesize);
        return null;
    }


}
