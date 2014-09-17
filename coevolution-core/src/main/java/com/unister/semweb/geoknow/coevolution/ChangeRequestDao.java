package com.unister.semweb.geoknow.coevolution;

import java.io.IOException;
import java.util.Set;
import java.util.SortedSet;

import com.google.common.base.Optional;
import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;

/**
 * Interface for storing and retrieving change requests.
 * 
 * @author m.wauer
 *
 */
public interface ChangeRequestDao {
    
    public IdentifiedChangeRequest readFirstForSubject(String subject) throws IOException;
    
    public Set<IdentifiedChangeRequest> readSuccessor(String changeRequestIdentifier) throws IOException;
    
    public SortedSet<IdentifiedChangeRequest> readNewest(long limit) throws IOException;
    
    public Set<IdentifiedChangeRequest> readLastForSubject(String subject, Optional<String> exceptChangeRequestIdentifier) throws IOException;
    
    public IdentifiedChangeRequest updateAndSetPrecedingChangeRequest(ChangeRequest changeRequest) throws IOException;

}
