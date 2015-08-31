package com.unister.semweb.geoknow.coevolution.resource;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.RDF;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.Namespace;
import com.unister.semweb.geoknow.coevolution.rdf.RdfConvertible;
import com.wordnik.swagger.annotations.ApiModel;

/**
 * Some change with an identifier.
 * 
 * @author m.wauer
 *
 */
@ApiModel(value = "Identified Change", description = "Change resource representation with identifier")
@XStreamAlias("change")
public class IdentifiedChange extends Change implements RdfConvertible<IdentifiedChange> {

    public IdentifiedChange() {
        super();
    }

    /**
     * Super Constructor, protected.
     * 
     * @param subject
     *            the subject (resource URI) of the statement, can be null if the subject of the change request should
     *            be used
     * @param predicate
     *            the predicate URI of the statement
     * @param object
     *            the object value of the statement
     */
    protected IdentifiedChange(String subject, String predicate, String object) {
        super(subject, predicate, object);
    }

    /**
     * Constructor.
     * 
     * @param subject
     *            the subject (resource URI) of the statement, can be null if the subject of the change request should
     *            be used
     * @param predicate
     *            the predicate URI of the statement
     * @param object
     *            the object value of the statement
     */
    public IdentifiedChange(String identifier, String subject, String predicate, String object) {
        super(subject, predicate, object);
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return String.format("IdentifiedChange [identifier=%s, getSubject()=%s, getPredicate()=%s, getObject()=%s]",
                identifier, getSubject(), getPredicate(), getObject());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + super.hashCode();
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        IdentifiedChange other = (IdentifiedChange) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        return super.equals(obj);
    }

    @Override
    public Model toModel() {
        LinkedHashModel model = new LinkedHashModel();
        URIImpl identifierURI = new URIImpl(Namespace.CHANGES + identifier);
        model.add(identifierURI, RDF.SUBJECT, new URIImpl(getSubject()));
        model.add(identifierURI, RDF.PREDICATE, new URIImpl(getPredicate()));
        model.add(identifierURI, RDF.OBJECT, new URIImpl(getObject()));
        return model;
    }

    @Override
    public IdentifiedChange fromModel(Model model, URI subject) {
        Model subjects = model.filter(subject, RDF.SUBJECT, null);
        Model predicates = model.filter(subject, RDF.PREDICATE, null);
        Model objects = model.filter(subject, RDF.OBJECT, null);
        Value subjectValue = subjects.objectValue();
        Value predicateValue = predicates.objectValue();
        Value objectValue = objects.objectValue();
        if (subject == null) {
            Set<Resource> identifiers = model.subjects();
            if (identifiers.size() != 1) {
                throw new IllegalArgumentException("Given model contains multiple resources, but no subject is defined");
            }
            this.setIdentifier(StringUtils.substringAfter(identifiers.iterator().next().stringValue(),
                    Namespace.CHANGES));
        }
        this.setSubject(subjectValue.stringValue());
        this.setPredicate(predicateValue.stringValue());
        this.setObject(objectValue.stringValue());
        return this;
    }

}
