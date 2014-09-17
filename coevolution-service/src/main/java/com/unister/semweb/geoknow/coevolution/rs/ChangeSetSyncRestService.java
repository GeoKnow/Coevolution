package com.unister.semweb.geoknow.coevolution.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.unister.semweb.geoknow.coevolution.config.AppConfig;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

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

    
}
