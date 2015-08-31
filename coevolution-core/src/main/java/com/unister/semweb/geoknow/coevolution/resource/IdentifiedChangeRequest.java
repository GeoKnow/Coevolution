package com.unister.semweb.geoknow.coevolution.resource;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.StringUtils;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.XMLSchema;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.Namespace;
import com.unister.semweb.geoknow.coevolution.rdf.RdfConvertible;
import com.unister.semweb.geoknow.coevolution.rdf.RdfUtils;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * A change request which has been stored by the ChangeSet Management Service, thus got an identifier (GUID) and,
 * optionally, a preceding change request identifier.
 * 
 * @author m.wauer
 * 
 */
@ApiModel(value = "Identified Change Request", description = "Change request resource representation with identifier")
@XStreamAlias("identifiedChangeRequest")
public class IdentifiedChangeRequest extends ChangeRequest implements RdfConvertible<IdentifiedChangeRequest> {

    /**
     * generated
     */
    private static final long serialVersionUID = -4995264275059362169L;

    @ApiModelProperty(value = "the identifier GUID", required = true)
    protected String identifier;

    @ApiModelProperty(value = "the identifier GUID of a preceding change request, if any", required = false)
    protected String precedingChangeRequestIdentifier;

    /**
     * Default constructor.
     */
    public IdentifiedChangeRequest() {

    }

    /**
     * Super constructor, protected.
     * 
     * @param subject
     * @param removals
     * @param additions
     */
    protected IdentifiedChangeRequest(String subject, Change[] removals, Change[] additions, String context) {
        super(subject, removals, additions, context);
    }

    /**
     * Constructor. Either removals or additions must be non-empty.
     * 
     * @param identifier
     *            a GUID identifier of the change request
     * @param subject
     *            the subject (resource URI) to be changed
     * @param removals
     *            an array of {@link Change} statements to be removed
     * @param additions
     *            an array of {@link Change} statements to be added
     */
    public IdentifiedChangeRequest(String identifier, String subject, IdentifiedChange[] removals,
            IdentifiedChange[] additions, String context) {
        this(subject, removals, additions, context);
        this.identifier = identifier;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier
     *            the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the precedingChangeRequestIdentifier
     */
    public String getPrecedingChangeRequestIdentifier() {
        return precedingChangeRequestIdentifier;
    }

    /**
     * @param precedingChangeRequestIdentifier
     *            the precedingChangeRequestIdentifier to set
     */
    public void setPrecedingChangeRequestIdentifier(String precedingChangeRequestIdentifier) {
        this.precedingChangeRequestIdentifier = precedingChangeRequestIdentifier;
    }

    @Override
    public Model toModel() {
        LinkedHashModel model = new LinkedHashModel();
        URIImpl identifierURI = new URIImpl(Namespace.CHANGES + identifier);
        // URIImpl contextURI = new URIImpl(getContext());
        model.add(identifierURI, RDF.TYPE, ChangeRequestProperty.ChangeSet.getURI());
        model.add(identifierURI, ChangeRequestProperty.SubjectOfChange.getURI(), new URIImpl(getSubject()));
        model.add(identifierURI, ChangeRequestProperty.Context.getURI(), new URIImpl(getContext()));
        if (getReason() != null) {
            model.add(identifierURI, ChangeRequestProperty.ChangeReason.getURI(), new LiteralImpl(getReason()));
        }
        if (getCreatedDate() != null) {
            model.add(identifierURI, ChangeRequestProperty.CreatedDate.getURI(), new LiteralImpl(
                    RdfUtils.formatIso8601Date(getCreatedDate()), XMLSchema.DATE));
        }
        if (getAuthor() != null) {
            model.add(identifierURI, ChangeRequestProperty.CreatorName.getURI(), new LiteralImpl(getAuthor()));
        }
        if (getVerified() != null) {
            model.add(identifierURI, ChangeRequestProperty.Verified.getURI(),
                    new LiteralImpl(Boolean.toString(getVerified()), XMLSchema.BOOLEAN));
        }
        if (getPrecedingChangeRequestIdentifier() != null) {
            model.add(identifierURI, ChangeRequestProperty.PrecedingChangeSet.getURI(), new URIImpl(
                    Namespace.CHANGES.concat(getPrecedingChangeRequestIdentifier())));
        }

        // removals
        if (getRemovals() != null) {
            for (Change removal : getRemovals()) {
                createChangeStatements(ChangeRequestProperty.Removal, identifierURI, removal, model);
            }
        }

        // additions
        if (getAdditions() != null) {
            for (Change addition : getAdditions()) {
                createChangeStatements(ChangeRequestProperty.Addition, identifierURI, addition, model);
            }
        }

        return model;
    }

    /**
     * Adds statements for the given change to the given model.
     * 
     * @param type
     *            either {@link ChangeRequestProperty#Removal} or {@link ChangeRequestProperty#Addition}
     * @param changeRequestIdentifier
     *            the URI of the changeset
     * @param change
     *            has to be an {@link IdentifiedChange}
     * @param model
     *            the target model
     * @throws SerializationException
     *             if change is not an {@link IdentifiedChange} or contains invalid values
     */
    private void createChangeStatements(ChangeRequestProperty type, URI changeRequestIdentifier, Change change,
            Model model) throws SerializationException {
        if (change instanceof IdentifiedChange) {
            URIImpl changeIdentifier = new URIImpl(Namespace.CHANGES + ((IdentifiedChange) change).getIdentifier());
            URIImpl changeSubject = new URIImpl(change.getSubject());
            URIImpl changePredicate = new URIImpl(change.getPredicate());
            Value changeObject = change.fetchObjectValue();
            model.add(changeRequestIdentifier, type.getURI(), changeIdentifier);
            model.add(changeIdentifier, RDF.SUBJECT, changeSubject);
            model.add(changeIdentifier, RDF.PREDICATE, changePredicate);
            model.add(changeIdentifier, RDF.OBJECT, changeObject);
        }
        else {
            throw new SerializationException("IdentifiedChangeRequest contains non-identified change");
        }
    }

    @Override
    public IdentifiedChangeRequest fromModel(Model model, URI subject) {
        if (subject == null) {
            // try to identify subject
            Set<Resource> subjects = model.filter(null, RDF.TYPE, ChangeRequestProperty.ChangeSet.getURI()).subjects();
            if (subjects.size() == 1) {
                subject = (URI) subjects.iterator().next();
            }
            else {
                throw new UnknownSubjectException("No subject given, but subject can't be derived from model: "
                        + subjects.size() + " changesets found in model");
            }
        }

        // extract required properties
        URI subjectOfChange = model.filter(subject, ChangeRequestProperty.SubjectOfChange.getURI(), null).objectURI();
        URI context = model.filter(subject, ChangeRequestProperty.Context.getURI(), null).objectURI();
        Set<Value> additionURIs = model.filter(subject, ChangeRequestProperty.Addition.getURI(), null).objects();
        Set<Value> removalURIs = model.filter(subject, ChangeRequestProperty.Removal.getURI(), null).objects();
        IdentifiedChange[] additions = extractChanges(model, additionURIs, context);
        IdentifiedChange[] removals = extractChanges(model, removalURIs, context);

        IdentifiedChangeRequest identifiedChangeRequest = new IdentifiedChangeRequest(
                StringUtils.substringAfter(subject.stringValue(), Namespace.CHANGES),
                subjectOfChange.stringValue(), removals, additions, context.stringValue());
        // add optional properties
        Value author = model.filter(subject, ChangeRequestProperty.CreatorName.getURI(), null).objectValue();
        identifiedChangeRequest.setAuthor(author == null ? null : author.stringValue());
        Value created = model.filter(subject, ChangeRequestProperty.CreatedDate.getURI(), null).objectValue();
        try {
            if (null != created) {
                identifiedChangeRequest.setCreatedDate(RdfUtils.parseIso8601Date(created.stringValue()));
            }
        } catch (ParseException e) {
            throw new SerializationException("Invalid date: " + created, e);
        }
        URI preceding = model.filter(subject, ChangeRequestProperty.PrecedingChangeSet.getURI(), null).objectURI();
        if (preceding != null) {
            identifiedChangeRequest.setPrecedingChangeRequestIdentifier(StringUtils.substringAfter(
                    preceding.stringValue(), Namespace.CHANGES));
        }
        Value reason = model.filter(subject, ChangeRequestProperty.ChangeReason.getURI(), null).objectValue();
        if (reason != null) {
            identifiedChangeRequest.setReason(reason.stringValue());
        }
        Value verified = model.filter(subject, ChangeRequestProperty.Verified.getURI(), null).objectValue();
        if (verified != null) {
            identifiedChangeRequest.setVerified(verified == null ? null : new Boolean(verified.stringValue()));
        }
        return identifiedChangeRequest;
    }

    private IdentifiedChange[] extractChanges(Model model, Set<Value> changeURIs, URI context) {
        List<IdentifiedChange> changes = Lists.newArrayList();
        for (Value changeURI : changeURIs) {
            if (changeURI instanceof Resource) {
                Resource subjectResource = model.filter((Resource) changeURI, RDF.SUBJECT, null).objectResource();
                URI predicateURI = model.filter((Resource) changeURI, RDF.PREDICATE, null).objectURI();
                Value objectValue = model.filter((Resource) changeURI, RDF.OBJECT, null).objectValue();
                changes.add(new IdentifiedChange(
                        StringUtils.substringAfter(changeURI.stringValue(), Namespace.CHANGES),
                        subjectResource.stringValue(), predicateURI
                                .stringValue(), Change.parseObjectValue(objectValue)));
            }
        }
        return changes.toArray(new IdentifiedChange[] {});
    }

    public enum ChangeRequestProperty {

        // official changeset vocabulary types and properties
        ChangeSet(Namespace.CHANGESET + "ChangeSet"),
        SubjectOfChange(Namespace.CHANGESET + "subjectOfChange"),
        ChangeReason(Namespace.CHANGESET + "changeReason"),
        CreatedDate(Namespace.CHANGESET + "createdDate"),
        Addition(Namespace.CHANGESET + "addition"),
        Removal(Namespace.CHANGESET + "removal"),
        CreatorName(Namespace.CHANGESET + "creatorName"),
        Statement(Namespace.CHANGESET + "statement"),
        PrecedingChangeSet(Namespace.CHANGESET + "precedingChangeSet"),
        // extensions for GeoKnow
        Context(Namespace.CHANGE + "context"),
        Verified(Namespace.CHANGE + "verified");
        ;

        private URI uri;

        ChangeRequestProperty(String uri) {
            this.uri = new URIImpl(uri);
        }

        public URI getURI() {
            return uri;
        }

        public String toString() {
            return uri.stringValue();
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result
                + ((precedingChangeRequestIdentifier == null) ? 0 : precedingChangeRequestIdentifier.hashCode());
        result = prime * result + super.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        IdentifiedChangeRequest other = (IdentifiedChangeRequest) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (precedingChangeRequestIdentifier == null) {
            if (other.precedingChangeRequestIdentifier != null)
                return false;
        } else if (!precedingChangeRequestIdentifier.equals(other.precedingChangeRequestIdentifier))
            return false;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String
                .format("IdentifiedChangeRequest [identifier=%s, precedingChangeRequestIdentifier=%s, subject=%s, author=%s, reason=%s, createdDate=%s, removals=%s, additions=%s, verified=%s, context=%s]",
                        identifier, precedingChangeRequestIdentifier, subject, author, reason, createdDate,
                        Arrays.toString(removals), Arrays.toString(additions), verified, context);
    }

}
