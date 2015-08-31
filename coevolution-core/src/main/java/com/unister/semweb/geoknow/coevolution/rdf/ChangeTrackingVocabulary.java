package com.unister.semweb.geoknow.coevolution.rdf;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

import com.unister.semweb.geoknow.coevolution.ChangeTrackingDao;

/**
 * Vocabulary definitions and utility methods for change tracking.
 * 
 * @author m.wauer
 *
 */
public class ChangeTrackingVocabulary {

    /* TYPES */
    
    public static final URI CHANGE_APPLICATION = new URIImpl(Namespace.CHANGE.concat("ChangeApplication"));
    
    /* PROPERTIES */
    
    public static final URI HAS_APPLICATION_RESULT = new URIImpl(Namespace.CHANGE.concat("hasApplicationResult"));
    
    public static final URI HAS_CORRECTION_CONTEXT = new URIImpl(Namespace.CHANGE.concat("hasCorrectionContext"));

    public static final URI HAS_TARGET_GRAPH = new URIImpl(Namespace.CHANGE.concat("hasTargetGraph"));

    public static URI getChangeRequestTrackingProperty(ChangeTrackingDao.ChangeHandling handling) {
        return new URIImpl(Namespace.CHANGE.concat(handling.name()).concat("ChangeRequest"));
    }
    
    public static URI getAdditionTrackingProperty(ChangeTrackingDao.ChangeHandling handling) {
        return new URIImpl(Namespace.CHANGE.concat(handling.name()).concat("Addition"));        
    }
    
    public static URI getRemovalTrackingProperty(ChangeTrackingDao.ChangeHandling handling) {
        return new URIImpl(Namespace.CHANGE.concat(handling.name()).concat("Removal"));                
    }
    
    public static URI getChangeApplicationResult(ChangeTrackingDao.ChangeApplicationResult result) {
        return new URIImpl(Namespace.CHANGE.concat(result.name()));                
    }

}
