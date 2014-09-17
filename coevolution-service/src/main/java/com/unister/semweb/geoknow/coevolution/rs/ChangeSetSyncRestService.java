package com.unister.semweb.geoknow.coevolution.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.unister.semweb.geoknow.coevolution.config.AppConfig;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/sync")
@Api(value = "/sync", description = "ChangeSet Sync Service", position = 3)
public class ChangeSetSyncRestService {

    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/version")
    @ApiOperation(value = "Get service API version", notes = "Returns the version of this service API, according to semantic versioning (semver.org)", response = String.class)
    public String getVersion() {
        return AppConfig.VERSION;
    }
    
    /**
     * Returns all statements to be deleted and added when transitioning from source graph to target graph version.
     */
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE })
    @GET
    @Path("/diff")
    @ApiOperation(value = "Creates a diff between versions", notes = "Create a list of modifications to be applied when transitioning from source graph to target graph version", response = ChangeRequest.class)
    public ChangeRequest createDiff(
            @ApiParam(value = "Graph URI where changesets are stored", required = true) @QueryParam("graph") final String graph,
            @ApiParam(required = true, name = "source", value = "The source context (graph)") @QueryParam("context") String sourceContext,
            @ApiParam(required = true, name = "target", value = "The target context (graph)") @QueryParam("context") String targetContext) {
//        return changeSetManagementService.getChangeRequests(graph, subject, page, pagesize);
        return null;
    }

    
}
