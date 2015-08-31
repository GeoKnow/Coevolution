package com.unister.semweb.geoknow.coevolution.resource;

import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.StringUtils;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Some change.
 * 
 * @author m.wauer
 *
 */
@ApiModel(value = "Change", description = "Change resource representation")
@XStreamAlias("change")
public class Change {

    @ApiModelProperty(value = "the subject to be changed. Only URIs allowed", required = true)
    private String subject;
    @ApiModelProperty(value = "the predicate to be changed. Only URIs allowed", required = true)
    private String predicate;
    @ApiModelProperty(value = "the object to be changed. Only in case of a literal, it has to be enclosed in double quotes. If a typed literal, has to conform to the N-triples syntax of typed literals with URIs as datatype, e.g., '\"My label\"^^<http://www.w3.org/2001/XMLSchema#>'.", required = true)
    private String object;
    @ApiModelProperty(value = "the identifier GUID", required = true)
    protected String identifier;

    public Change() {
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
    public Change(String subject, String predicate, String object) {
        super();
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the predicate
     */
    public String getPredicate() {
        return predicate;
    }

    /**
     * @param predicate
     *            the predicate to set
     */
    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object
     *            the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @return the identifier
     */
    protected String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier
     *            the identifier to set
     */
    protected void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return String.format("Change [subject=%s, predicate=%s, object=%s]", subject, predicate, object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((object == null) ? 0 : object.hashCode());
        result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Change other = (Change) obj;
        if (object == null) {
            if (other.object != null)
                return false;
        } else if (!object.equals(other.object))
            return false;
        if (predicate == null) {
            if (other.predicate != null)
                return false;
        } else if (!predicate.equals(other.predicate))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        return true;
    }

    /**
     * @return a Sesame {@link Value} for the given object
     * @throws SerializationException
     *             if invalid
     */
    public Value fetchObjectValue() {
        if (null == object) {
            return null;
        }
        if (object.startsWith("\"")) {
            // literal
            int lastIndexOfTypedLiteralDelimiter = object.lastIndexOf("\"^^");
            if (lastIndexOfTypedLiteralDelimiter == -1) {
                int lastIndexOfLanguageTag = object.lastIndexOf("\"@");
                if (lastIndexOfLanguageTag == -1) {
                    // simple literal
                    return new LiteralImpl(StringUtils.substring(object, 1, object.length() - 1));
                }
                else {
                    // literal with language tag
                    String languageTag = StringUtils.substringAfterLast(object, "@");
                    return new LiteralImpl(StringUtils.substring(object, 1, lastIndexOfLanguageTag), languageTag);
                }
            }
            else {
                URIImpl datatype = new URIImpl(StringUtils.substring(object, lastIndexOfTypedLiteralDelimiter + 4,
                        object.length() - 1));
                return new LiteralImpl(StringUtils.substring(object, 1, lastIndexOfTypedLiteralDelimiter), datatype);
            }
        }
        else {
            return new URIImpl(object);
        }
    }

    /**
     * Sets Sesame {@link Value} of object.
     * 
     * @param object
     */
    public void insertObjectValue(Value object) {
        this.object = parseObjectValue(object);
    }

    /**
     * Parse the object value to properly represent URIs and literals.
     * 
     * @param object
     *            a Sesame {@link Value}
     * @return a string properly formatting the value
     */
    public static String parseObjectValue(Value object) {
        if (null == object) {
            return null;
        }
        if (object instanceof Literal) {
            URI datatype = ((Literal) object).getDatatype();
            String language = ((Literal) object).getLanguage();
            return "\"".concat(((Literal) object).getLabel()).concat(
                    datatype == null ?
                            language == null ? "\"" : "\"@".concat(language)
                            : "\"^^<".concat(datatype.stringValue()).concat(">")
                    );
        }
        return object.stringValue();
    }

}
