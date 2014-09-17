package com.unister.semweb.geoknow.coevolution.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.unister.semweb.geoknow.coevolution.config.AppConfig;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/graphs")
@Api(value = "/graphs", description = "Graph Versioning Service", position = 4)
public class GraphVersioningRestService {
    
    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/version")
    @ApiOperation(value = "Get service API version", notes = "Returns the version of this service API, according to semantic versioning (semver.org)", response = String.class)
    public String getVersion() {
        return AppConfig.VERSION;
    }
    
    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/type/{type}")
    @ApiOperation(value = "Get versions of the given type", notes = "Returns the available versions of the given graph type as URIs", response = String.class, responseContainer = "List")
    public String getGraphVersions(
            @ApiParam(required = true, name = "type", value = "The graph type identifier") @PathParam("type") String graphType
            ) {
        return null;
    }
    
    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/latest/{type}")
    @ApiOperation(value = "Get latest version of the given type", notes = "Returns the latest version of the given graph type as URI", response = String.class)
    public String getLatestGraphVersion(
            @ApiParam(required = true, name = "type", value = "The graph type identifier") @PathParam("type") String graphType
            ) {
        return null;
    }
    
    @Produces({ MediaType.APPLICATION_XML, TurtleProvider.TURTLE_MEDIA_TYPE, MediaType.TEXT_PLAIN })
    @GET
    @Path("/type/{type}/{version}")
    @ApiOperation(value = "Get metadata on a graph version", notes = "Returns details on the given version of the given graph type as URI", response = String.class)
    public String getGraphDetails(
            @ApiParam(required = true, name = "type", value = "The graph type identifier") @PathParam("type") String graphType,
            @ApiParam(required = true, name = "version", value = "The graph version identifier") @PathParam("version") String graphVersion
            ) {
        return null;
    }
    

}
