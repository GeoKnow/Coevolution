package com.unister.semweb.geoknow.coevolution.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.UUID;

import org.apache.commons.lang.NotImplementedException;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.unister.semweb.geoknow.coevolution.ChangeRequestDao;
import com.unister.semweb.geoknow.coevolution.resource.ChangeRequest;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;

/**
 * In-Memory implementation of {@link ChangeRequestDao}.
 * 
 * @author m.wauer
 *
 */
public class DefaultChangeRequestDao implements ChangeRequestDao {

    Map<String, SortedMap<Long, IdentifiedChangeRequest>> changeRequests;
    
    Map<String, String> changeRequestIdentifierSubjectLookupTable;
    
    public DefaultChangeRequestDao() {
        changeRequests = Maps.newConcurrentMap();
        changeRequestIdentifierSubjectLookupTable = Maps.newConcurrentMap();
    }

    @Override
    public IdentifiedChangeRequest readFirstForSubject(String subject) throws IOException {
        if (changeRequests.containsKey(subject)) {
            SortedMap<Long, IdentifiedChangeRequest> sortedMap = changeRequests.get(subject);
            return sortedMap.get(sortedMap.firstKey());
        }
        return null;
    }

    @Override
    public Set<IdentifiedChangeRequest> readSuccessor(String changeRequestIdentifier) throws IOException {
        if (changeRequestIdentifierSubjectLookupTable.containsKey(changeRequestIdentifier)) {
            String subject = changeRequestIdentifierSubjectLookupTable.get(changeRequestIdentifier);
            SortedMap<Long, IdentifiedChangeRequest> subjectChangeRequests = changeRequests.get(subject);
            // this is a primitive implementation only addressing a single subject
            for (IdentifiedChangeRequest icr : subjectChangeRequests.values()) {
                if (changeRequestIdentifier.equals(icr.getPrecedingChangeRequestIdentifier())) {
                    return Sets.newHashSet(icr);
                }
            }
        }
        return Sets.newHashSet();
    }

    @Override
    public SortedSet<IdentifiedChangeRequest> readNewest(long limit) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public Set<IdentifiedChangeRequest> readLastForSubject(String subject,
            Optional<String> exceptChangeRequestIdentifier) throws IOException {
        if (changeRequests.containsKey(subject)) {
            SortedMap<Long, IdentifiedChangeRequest> sortedMap = changeRequests.get(subject);
            // simple implementation only addressing single subjects
            return Sets.newHashSet(sortedMap.get(sortedMap.lastKey()));
        }
        return null;
    }

    @Override
    public IdentifiedChangeRequest updateAndSetPrecedingChangeRequest(ChangeRequest changeRequest) throws IOException {
        long timestamp = System.currentTimeMillis();
        String newIdentifier = new UUID(System.currentTimeMillis(), Double.doubleToLongBits(Math.random())).toString();
        String subject = changeRequest.getSubject();
        IdentifiedChangeRequest newChangeRequest = null;
        // try to find preceding changeset
        synchronized(changeRequests) {
            if (changeRequests.containsKey(subject)) {
                // we have a preceding changeset
                SortedMap<Long, IdentifiedChangeRequest> existingChangeRequests = changeRequests.get(subject);
                Long lastKey = existingChangeRequests.lastKey();                
                IdentifiedChangeRequest lastChangeRequest = existingChangeRequests.get(lastKey);
                newChangeRequest = createIdentified(changeRequest, newIdentifier, subject);
                newChangeRequest.setPrecedingChangeRequestIdentifier(lastChangeRequest.getIdentifier());
                existingChangeRequests.put(timestamp, newChangeRequest);
            }
            else {
                // no preceding changeset, add new SortedMap with changeRequest to changeRequests
                newChangeRequest = createIdentified(changeRequest, newIdentifier, subject);
                SortedMap<Long, IdentifiedChangeRequest> subjectChangeRequests = Maps.newTreeMap(new LongComparator());
                subjectChangeRequests.put(timestamp, newChangeRequest);
                changeRequests.put(subject, subjectChangeRequests);
            }
            changeRequestIdentifierSubjectLookupTable.put(newIdentifier, subject);

        }
        return newChangeRequest;
    }
    
    private IdentifiedChangeRequest createIdentified(ChangeRequest changeRequest, String newIdentifier, String subject) {
        IdentifiedChangeRequest newChangeRequest = new IdentifiedChangeRequest(newIdentifier, subject, changeRequest.getRemovals(), changeRequest.getAdditions());
        newChangeRequest.setAuthor(changeRequest.getAuthor());
        newChangeRequest.setReason(changeRequest.getReason());
        newChangeRequest.setCreatedDate(changeRequest.getCreatedDate());
        newChangeRequest.setVerified(changeRequest.getVerified());
        return newChangeRequest;
    }
    
    private static class LongComparator implements Comparator<Long> {

        @Override
        public int compare(Long o1, Long o2) {            
            return o1.longValue()<o2.longValue()?-1:
                   o2.longValue()<o1.longValue()?1:0;
        }
        
    }

}
