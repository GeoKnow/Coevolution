package com.unister.semweb.geoknow.coevolution.resource;

import org.junit.Before;
import org.junit.Test;
import org.openrdf.model.Model;
import org.openrdf.model.vocabulary.RDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifiedChangeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentifiedChangeTest.class);

    private IdentifiedChange testChange;

    @Before
    public void setUp() throws Exception {
        testChange = new IdentifiedChange("urn:change1", "urn:r1", RDF.TYPE.stringValue(), RDF.STATEMENT.stringValue());
    }

    @Test
    public void testToModel() {
        LOGGER.info("{}", testChange);
        Model model = testChange.toModel();
        LOGGER.info("{}", model);
        IdentifiedChange change = new IdentifiedChange().fromModel(model, null);
        LOGGER.info("{}", change);
    }

}
