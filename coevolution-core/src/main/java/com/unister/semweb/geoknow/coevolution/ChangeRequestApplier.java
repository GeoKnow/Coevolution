package com.unister.semweb.geoknow.coevolution;

import java.io.IOException;
import java.util.Set;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;

/**
 * Interface for service implementations of applying change requests.
 * 
 * @author m.wauer
 *
 */
public interface ChangeRequestApplier {
    
    public Model applyChangeRequest(Model model, String resultContext) throws IOException;

}
