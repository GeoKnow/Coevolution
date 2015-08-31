package com.unister.semweb.geoknow.coevolution;

import java.util.List;

import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;

/**
 * 
 * DAO for the {@link GraphVersionService}.
 * @author b.eickmann
 * 
 */

public interface GraphVersioningDao {

    
    /**
     * Adds a {@link Statement} to the given context.
     * @param statement
     * @param context
     */
    public void addStatement(Statement statement, Resource context);

    /**
     * Adds {@link Statement}s to the given context.
     * @param model
     * @param context
     */
    public void addStatements(Model model, Resource context);
    
    /**
     * Returns all subjects of triples in the given context with the given predicate and object.
     * @param predicate
     * @param object
     * @param context
     * @return {@link List<Resource>}
     */
    public List<Resource> getAllSubjects(URI predicate, Resource object, Resource context);

    /**
     * Returns all subjects of triples in the given context.
     * @param context
     * @return {@link List<Resource>}
     */
    public List<Resource> getAllSubjects(Resource context);

    /**
     * Returns the last created named graph URI for the given group.
     * @param group
     * @param context
     * @return {@link Resource}
     */
    public Resource getLatestGraphVersion(URI group, Resource context);
    

    /**
     * Returns a {@link Model} with all {@link Statement}s with the given subject.
     * @param subject
     * @param context
     * @return {@link Model}
     */
    public Model getGraphForSubject(Resource subject, Resource context);

}
