package com.unister.semweb.geoknow.coevolution;

import java.io.IOException;

import org.openrdf.model.Model;

import com.unister.semweb.geoknow.coevolution.resource.ConflictResolutionStrategy;

/**
 * Interface for service implementations of applying change requests.
 * 
 * @author m.wauer
 *
 */
public interface ChangeRequestApplier {

    /**
     * Applies changes to a given model.
     * 
     * @param model
     *            a sesame {@link Model} instance which will be modified.
     * @param correctionContext
     *            the graph containing the changes to be applied
     * @param resultContext
     *            the graph to which the changes will be applied, if <code>null</code> the statements to be created will
     *            have no specified context.
     * @param conflictResolution
     *            a {@link ConflictResolutionStrategy}
     * @param filter
     *            the filters for specifying a subset of all changes in the given graph TODO specify type
     * @throws IOException
     */
    public void applyChangeRequest(Model model, String correctionContext, String resultContext,
            ConflictResolutionStrategy conflictResolution, String filters) throws IOException;

    /**
     * Applies changes to a given graph in the repository.
     * 
     * @param correctionContext
     *            the graph containing the changes to be applied
     * @param resultContext
     *            the graph to which the changes will be applied (in-place modification).
     * @param conflictResolution
     *            a {@link ConflictResolutionStrategy}
     * @param filter
     *            the filters for specifying a subset of all changes in the given graph TODO specify type
     * @param changeTrackingContext
     *            for statistics on application process and identifier. A change tracking DAO has to be set, usually
     *            <code>new ChangeTrackingContext(changeTrackingDao)</code> should suffice. This context will be updated with application metadata.
     * @throws IOException
     */
    public void applyChangeRequest(String correctionContext, String resultContext,
            ConflictResolutionStrategy conflictResolution, String filters, ChangeTrackingContext changeTrackingContext)
            throws IOException;

}
