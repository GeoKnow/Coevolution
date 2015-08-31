package com.unister.semweb.geoknow.coevolution;

import java.io.IOException;
import java.util.List;
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

    public IdentifiedChangeRequest readFirstForSubject(String graph, String subject) throws IOException;

    public IdentifiedChangeRequest readChangeRequest(String graph, String changeRequestIdentifier) throws IOException;

    public Set<IdentifiedChangeRequest> readSuccessor(String graph, String changeRequestIdentifier) throws IOException;

    public SortedSet<IdentifiedChangeRequest> readNewest(String graph, long limit) throws IOException;

    public Set<IdentifiedChangeRequest> readLastForSubject(String graph, String subject,
            Optional<String> exceptChangeRequestIdentifier) throws IOException;

    public IdentifiedChangeRequest updateAndSetPrecedingChangeRequest(String graph, ChangeRequest changeRequest)
            throws IOException;

    public List<String> getSubjects(String graph, int page, int pagesize) throws IOException;

    public void delete(String graph, String changeRequestIdentifier) throws IOException;

}
