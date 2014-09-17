package com.unister.semweb.geoknow.coevolution.resource;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

/**
 * Constants for wildcards.
 * 
 * @author m.wauer
 *
 */
public final class ChangeConstants {

    public static final String NAMESPACE = "http://ontology.unister.de/geoknow/coevolution/change/constants/";
   
    /**
     * A wildcard for removing all statements with any predicate which matches the other constraints in the {@link Change}.
     */
    public static final URI ANY_PREDICATE = new URIImpl(NAMESPACE.concat("anyPredicate"));
     
    /**
     * A wildcard for removing all statements with any object which matches the other constraints in the {@link Change}.
     */
    public static final URI ANY_OBJECT = new URIImpl(NAMESPACE.concat("anyObject")); 
    

}
