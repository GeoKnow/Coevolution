package com.unister.semweb.geoknow.coevolution.rdf;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

public class Namespace {

    /**
     * ChangeSet vocabulary namespace
     */
    public static final String CHANGESET = "http://purl.org/vocab/changeset/schema#"; 
    
    /**
     * GeoKnow coevolution base vocabulary namespace
     */
    public static final String COEVOLUTION = "http://geoknow.eu/coevolution/";
        
    /**
     * GeoKnow change vocabulary namespace
     */
    public static final String CHANGE = COEVOLUTION + "change#";
    
    /**
     * GeoKnow Generator resources graph
     */
    public static final String GENERATOR_RESOURCES = "http://generator.geoknow.eu/resource/";
    
    /**
     * GeoKnow coevolution graph for changes
     */
    public static final String CHANGES_GRAPH = GENERATOR_RESOURCES + "changes";
    
    /**
     * GeoKnow coevolution namespace prefix for change requests
     */
    public static final String CHANGES = GENERATOR_RESOURCES + "changes/";
    
    /**
     * GeoKnow coevolution graph for storing graph versioning metadata
     */
    public static final String GRAPHVERSIONING_GRAPH = GENERATOR_RESOURCES + "graphversioning";

    /**
     * GeoKnow coevolution namespace prefix for graph versioning metadata
     */
    public static final String GRAPHVERSIONING = GENERATOR_RESOURCES + "graphversioning/";
    
    /**
     * GeoKnow coevolution graph for storing Graph Sets 
     */
    public static final String GRAPHSETS = GENERATOR_RESOURCES + "graphset/";

    /**
     * GeoKnow coevolution property that holds between a named graph and the graphset it belongs to.
     */
    public static final URI IN_GRAPHSET = new URIImpl(GRAPHVERSIONING + "property/graphset");
    
    /**
     * GeoKnow coevolution graph for change tracking
     */
    public static final String CHANGE_TRACKING = GENERATOR_RESOURCES + "changetracking/";

    /**
     * GeoKnow coevolution default graph for change tracking
     */
    public static final URI CHANGE_TRACKING_GRAPH = new URIImpl(GENERATOR_RESOURCES + "changetracking");
    
}
