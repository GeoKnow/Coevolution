package com.unister.semweb.geoknow.coevolution;

import com.unister.semweb.geoknow.coevolution.resource.ChangeTracking;

/**
 * Contains change tracking instance and change tracking DAO instance for change application processes.
 * 
 * @author m.wauer
 *
 */
public class ChangeTrackingContext {

    private ChangeTracking changeTracking;
    
    private ChangeTrackingDao changeTrackingDao;

    /**
     * Creates a new {@link ChangeTrackingContext} with the given values.
     * 
     * @param changeTracking
     * @param changeTrackingDao
     */
    public ChangeTrackingContext(ChangeTracking changeTracking, ChangeTrackingDao changeTrackingDao) {
        super();
        this.changeTracking = changeTracking;
        this.changeTrackingDao = changeTrackingDao;
    }

//    /**
//     * Creates a new {@link ChangeTrackingContext} with a new {@link ChangeTracking} instance.
//     * 
//     * @param changeTrackingDao
//     */
//    public ChangeTrackingContext(ChangeTrackingDao changeTrackingDao) {
//        this(ChangeTracking.createNewChangeTracking(new ChangeTracki), changeTrackingDao);
//    }

    public ChangeTracking getChangeTracking() {
        return changeTracking;
    }

    public ChangeTrackingDao getChangeTrackingDao() {
        return changeTrackingDao;
    }

}
