package com.unister.semweb.geoknow.coevolution.rdf;

import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.ValueFactoryImpl;

/**
 * Utils for org.openrdf.model datatype creation.
 * 
 * @author b.eickmann
 * 
 */
public class DatatypesUtils {

   
    private static final ValueFactory VALUE_FACTORY = new ValueFactoryImpl();

    public static Statement statement(Resource subject, URI predicate, Value object) {
        return VALUE_FACTORY.createStatement(subject, predicate, object);
    }

    public static Statement statement(Resource subject, URI predicate, Value object, Resource context) {
        return VALUE_FACTORY.createStatement(subject, predicate, object, context);
    }

    public static URI uri(String uri) {
        return VALUE_FACTORY.createURI(uri);
    }

    public static Literal englishLabel(String label) {
        return VALUE_FACTORY.createLiteral(label, "en");
    }

    public static Model model(Statement... statements) {
        Model model = new LinkedHashModel();
        for (Statement statement : statements) {
            model.add(statement);
        }
        return model;
    }

}
