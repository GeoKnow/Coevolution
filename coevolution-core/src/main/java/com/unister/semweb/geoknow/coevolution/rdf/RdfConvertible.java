package com.unister.semweb.geoknow.coevolution.rdf;

import org.openrdf.model.Model;
import org.openrdf.model.URI;

/**
 * Enables conversion of objects into RDF models.
 * 
 * @author m.wauer
 *
 * @param <T>
 *            the type of object
 */
public interface RdfConvertible<T> {

    /**
     * Convert object to model.
     * 
     * @return a {@link Model}
     */
    public Model toModel();

    /**
     * Assign properties from model to instance.
     * 
     * @param model
     * @param subject
     *            the subject in the model to be converted. Can be ommitted (<code>null</code>) if there is only the
     *            target resource to be converted in the model.
     * @return the same instance of T which is modified. In other words, <code>return this;</code> in your implementation.
     */
    public T fromModel(Model model, URI subject);

}
