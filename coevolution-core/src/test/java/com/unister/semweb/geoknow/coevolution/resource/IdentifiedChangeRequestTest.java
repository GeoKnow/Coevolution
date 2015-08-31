package com.unister.semweb.geoknow.coevolution.resource;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.openrdf.model.Model;
import org.openrdf.model.vocabulary.RDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifiedChangeRequestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentifiedChangeRequestTest.class);
    
    private IdentifiedChangeRequest changeRequest;
    
    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception {
        IdentifiedChange testChangeUri = new IdentifiedChange("urn:change1", "urn:r1", RDF.TYPE.stringValue(), RDF.STATEMENT.stringValue());
        IdentifiedChange testChangeTypedLiteral = new IdentifiedChange("urn:change2", "urn:r1", RDF.TYPE.stringValue(), "\"testvalue\"^^<urn:t1>");
        changeRequest = new IdentifiedChangeRequest("urn:c1", "urn:r1", new IdentifiedChange[] { testChangeUri, testChangeTypedLiteral }, new IdentifiedChange[] {}, "urn:g1");
        changeRequest.setAuthor("Testauthor");
        changeRequest.setCreatedDate(new Date(2015, 4, 30, 15, 5));
    }

    @Test
    public void testToModel() {
        Model model = changeRequest.toModel();  
        LOGGER.info("{}", model);
    }

    @Test
    public void testFromModel() {
        Model model = changeRequest.toModel();
        IdentifiedChangeRequest restored = new IdentifiedChangeRequest().fromModel(model, null);
        assertEquals(changeRequest.getIdentifier(), restored.getIdentifier());
        assertEquals(changeRequest.getSubject(), restored.getSubject());
        assertEquals(changeRequest.getAuthor(), restored.getAuthor());
        assertEquals(changeRequest.getContext(), restored.getContext());
//        assertEquals(changeRequest.getPrecedingChangeRequestIdentifier(), restored.getPrecedingChangeRequestIdentifier());
//        assertEquals(changeRequest.getReason(), restored.getReason());
//        assertEquals(changeRequest.getRemovals()[0], restored.getRemovals()[0]);
        assertEquals(changeRequest, restored);
    }

}
