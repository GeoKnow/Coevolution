package com.unister.semweb.geoknow.coevolution.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.openrdf.model.Model;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.DCTERMS;
import org.openrdf.model.vocabulary.RDF;

import com.unister.semweb.geoknow.coevolution.rdf.Namespace;

public class GraphSetTest {

    @Test
    public void testToModel() {
        GraphSet graphSet = new GraphSet("test");
        graphSet.setCreated(new Date());
        graphSet.setDescription("testdescription");
        Model model = graphSet.toModel();
        assertNotNull(model);
        assertEquals(Namespace.GRAPHSETS.concat("test"), model.subjects().iterator().next().stringValue());
        assertEquals("testdescription", model.filter(null, DCTERMS.DESCRIPTION, null).objectString());
        assertNotNull(model.filter(null, DCTERMS.CREATED, null).objectValue());
    }

    @Test
    public void testFromModel() {
        LinkedHashModel model = new LinkedHashModel();
        model.add(new StatementImpl(new URIImpl(Namespace.GRAPHSETS.concat("test")), RDF.TYPE, GraphSet.GRAPHSET_TYPE));
        GraphSet graphSet = new GraphSet().fromModel(model, null);
        assertNotNull(graphSet);
        assertEquals("test", graphSet.getIdentifier());
    }

}
