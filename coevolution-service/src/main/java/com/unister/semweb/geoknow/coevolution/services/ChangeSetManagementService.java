package com.unister.semweb.geoknow.coevolution.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;

@Service
public class ChangeSetManagementService {
	
    public String createChangeSet(ChangeRequest changeRequest) {
        // TODO ask DAO
        return null;
    }
    
    /**
     * @param graph changesets graph
     * @param subject optional resource URI for subject of change
     * @param page
     * @param pagesize
     * @return a {@link Collection} of {@link IdentifiedChangeRequest}s
     */
    public Collection<IdentifiedChangeRequest> getChangeRequests(String graph, String subject, int page, int pagesize) {
        // TODO ask DAO
        return new ArrayList<IdentifiedChangeRequest>();
    }
	
}
