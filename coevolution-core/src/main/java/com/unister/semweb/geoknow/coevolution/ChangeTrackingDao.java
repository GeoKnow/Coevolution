package com.unister.semweb.geoknow.coevolution;

import java.io.IOException;

import org.openrdf.repository.RepositoryConnection;

import com.unister.semweb.geoknow.coevolution.resource.ConflictResolutionStrategy;

/**
 * DAO for tracking change request application.
 * 
 * @author m.wauer
 *
 */
public interface ChangeTrackingDao {

    /**
     * Result of a change application process.
     * 
     * @author m.wauer
     *
     */
    public enum ChangeApplicationResult {
        /**
         * The change application process completed successfully.
         */
        Completed,
        /**
         * The change application process failed / was aborted.
         */
        Failed
    }

    /**
     * Result of handling a certain change within a change application process.
     * 
     * @author m.wauer
     *
     */
    public enum ChangeHandling {
        /**
         * The change was applied entirely. Includes potential forced application.
         */
        applied,
        /**
         * The change was ignored entirely.
         */
        ignored,
        /**
         * The change was merged. Can be applied for additions and change requests with
         * {@link ConflictResolutionStrategy#MergeChange}.
         */
        merged,
        /**
         * The change was applied partially. Only can be applied to change requests incompletely applied with
         * {@link ConflictResolutionStrategy#IgnoreChangeForConflictingPredicate} or
         * {@link ConflictResolutionStrategy#IgnoreConflict}.
         */
        partial,
        /**
         * The change could not be applied due to failure (IOException etc.). Not to be applied for partial or ignored
         * changes.
         */
        failed;

        /**
         * @return <code>true</code> if considered applied (completely, partially or merged), <code>false</code>
         *         otherwise (ignored or failed)
         */
        public boolean isApplied() {
            switch (this) {
            case applied:
            case partial:
            case merged:
                return true;
            default:
                return false;
            }
        }
    }

    /**
     * Stores required change application metadata for the given change application GUID.
     * 
     * @param changeApplicationGuid
     * @param correctionContext
     * @param targetGraph
     * @throws IOException
     */
    public void addChangeTrackingMetadata(String changeApplicationGuid, String correctionContext, String targetGraph,
            RepositoryConnection connection) throws IOException;

    /**
     * Adds a change application result to the given change application process GUID.
     * 
     * @param changeApplicationGuid
     * @param result
     * @throws IOException
     */
    public void addChangeTrackingResult(String changeApplicationGuid, ChangeApplicationResult result,
            RepositoryConnection connection) throws IOException;

    /**
     * Tracks handling of a change request for the given change application GUID.
     * 
     * @param changeApplicationGuid
     * @param changeRequest
     * @param handling
     * @throws IOException
     */
    public void trackChangeRequest(String changeApplicationGuid, String changeRequest, ChangeHandling handling,
            RepositoryConnection connection) throws IOException;

    /**
     * Tracks handling of an addition for the given change application GUID.
     * 
     * @param changeApplicationGuid
     * @param addition
     * @param handling
     * @throws IOException
     */
    public void trackAddition(String changeApplicationGuid, String addition, ChangeHandling handling,
            RepositoryConnection connection) throws IOException;

    /**
     * Tracks handling of a removal for the given change application GUID.
     * 
     * @param changeApplicationGuid
     * @param removal
     * @param handling
     * @throws IOException
     */
    public void trackRemoval(String changeApplicationGuid, String removal, ChangeHandling handling,
            RepositoryConnection connection) throws IOException;

}
