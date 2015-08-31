package com.unister.semweb.geoknow.coevolution;

import java.util.List;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;

import com.unister.semweb.geoknow.coevolution.resource.Change;
import com.unister.semweb.geoknow.coevolution.resource.ConflictResolutionStrategy;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;

/**
 * Applies {@link Change}s in {@link IdentifiedChangeRequest}s to the configured {@link Repository}.
 * 
 * @since 0.1.0, version 0.2.0 added change tracking
 * @author b.eickmann
 *
 */
public interface ChangeApplierDao {
    
    /**
     * Applies {@link Change}s in {@link IdentifiedChangeRequest}s to the configured {@link Repository}.
     * 
     * @param requests
     * @param resultContext
     * @param conflictResolution
     * @return true if successful
     * @throws RepositoryException
     */
    public boolean applyChanges(List<IdentifiedChangeRequest> requests, String resultContext, ConflictResolutionStrategy conflictResolution, ChangeTrackingContext changeTrackingContext);
    
}
