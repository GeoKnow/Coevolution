package com.unister.semweb.geoknow.coevolution.resource;

import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.DCTERMS;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.ChangeTrackingVocabulary;
import com.unister.semweb.geoknow.coevolution.rdf.Namespace;
import com.unister.semweb.geoknow.coevolution.rdf.RdfConvertible;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Response object for change application process.
 * 
 * @author m.wauer
 *
 */
@ApiModel(value = "ChangeTracking", description = "Change tracking resource representation")
@XStreamAlias("changeTracking")
public class ChangeTracking implements RdfConvertible<ChangeTracking> {

    @ApiModelProperty(value = "the change application URI", required = true)
    private String changeApplicationURI;
    @ApiModelProperty(value = "the change application GUID", required = true)
    private String identifier;
    @ApiModelProperty(value = "the change application process result", notes = "values can be Completed and Failed (ChangeApplicationResult)", required = true)
    private String status;
    @ApiModelProperty(value = "details on the change application process result", notes = "only given on errors", required = false)
    private String description;

    @ApiModelProperty(value = "the number of processed change requests", notes = "includes those that have been ignored or partially applied", required = false)
    private long totalChangeRequests;
    // TODO check if notes are correct
    @ApiModelProperty(value = "the number of applied change requests", notes = "includes those that have been applied entirely and those that are partially applied / merged", required = false)
    private long appliedChangeRequests;
    @ApiModelProperty(value = "the number of processed additions", notes = "includes those that have been ignored", required = false)
    private long totalAdditions;
    @ApiModelProperty(value = "the number of applied additions", notes = "only includes those that have been added, not those that were merged", required = false)
    private long appliedAdditions;
    @ApiModelProperty(value = "the number of processed removals", notes = "includes those that have been ignored", required = false)
    private long totalRemovals;
    @ApiModelProperty(value = "the number of applied removals", required = false)
    private long appliedRemovals;
    @ApiModelProperty(value = "the number of failures on any operation", required = false)
    private long totalFailures;

    /**
     * use {@link #createNewChangeTracking()} for an initialized instance
     */
    protected ChangeTracking() {
    }

    /**
     * Generates a new {@link ChangeTracking} instance with given identifier and 0 values.
     * 
     * @param identifier
     *            a GUID for the change application process
     * @return {@link ChangeTracking} instance
     */
    public static ChangeTracking createNewChangeTracking(String identifier) {
        ChangeTracking instance = new ChangeTracking();
        instance.setIdentifier(identifier);
        instance.setChangeApplicationURI(Namespace.CHANGE_TRACKING.concat(identifier));
        return instance;
    }

    public String getChangeApplicationURI() {
        return changeApplicationURI;
    }

    public void setChangeApplicationURI(String changeApplicationURI) {
        this.changeApplicationURI = changeApplicationURI;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTotalChangeRequests() {
        return totalChangeRequests;
    }

    public void setTotalChangeRequests(long totalChangeRequests) {
        this.totalChangeRequests = totalChangeRequests;
    }

    public long getAppliedChangeRequests() {
        return appliedChangeRequests;
    }

    public void setAppliedChangeRequests(long appliedChangeRequests) {
        this.appliedChangeRequests = appliedChangeRequests;
    }

    public long getTotalAdditions() {
        return totalAdditions;
    }

    public void setTotalAdditions(long totalAdditions) {
        this.totalAdditions = totalAdditions;
    }

    public long getAppliedAdditions() {
        return appliedAdditions;
    }

    public void setAppliedAdditions(long appliedAdditions) {
        this.appliedAdditions = appliedAdditions;
    }

    public long getTotalRemovals() {
        return totalRemovals;
    }

    public void setTotalRemovals(long totalRemovals) {
        this.totalRemovals = totalRemovals;
    }

    public long getAppliedRemovals() {
        return appliedRemovals;
    }

    public void setAppliedRemovals(long appliedRemovals) {
        this.appliedRemovals = appliedRemovals;
    }

    public long getTotalFailures() {
        return totalFailures;
    }

    public void setTotalFailures(long totalFailures) {
        this.totalFailures = totalFailures;
    }

    /**
     * Increases total and applied change requests by one.
     */
    public void appliedChangeRequest() {
        this.totalChangeRequests++;
        this.appliedChangeRequests++;
    }

    /**
     * Increases total change requests by one.
     */
    public void ignoredChangeRequest() {
        this.totalChangeRequests++;
    }

    /**
     * Increases total and applied additions by one.
     */
    public void appliedAddition() {
        this.totalAdditions++;
        this.appliedAdditions++;
    }

    /**
     * Increases total additions by one.
     */
    public void ignoredAddition() {
        this.totalAdditions++;
    }

    /**
     * Increases total and applied removals by one.
     */
    public void appliedRemoval() {
        this.totalRemovals++;
        this.appliedRemovals++;
    }

    /**
     * Increases total removals by one.
     */
    public void ignoredRemoval() {
        this.totalRemovals++;
    }

    /**
     * Increases total number of failures.
     */
    public void countFailure() {
        this.totalFailures++;
    }

    @Override
    public String toString() {
        return String
                .format("ChangeTracking [changeApplicationURI=%s, identifier=%s, status=%s, description=%s, totalChangeRequests=%s, appliedChangeRequests=%s, totalAdditions=%s, appliedAdditions=%s, totalRemovals=%s, appliedRemovals=%s, totalFailures=%s]",
                        changeApplicationURI, identifier, status, description, totalChangeRequests,
                        appliedChangeRequests,
                        totalAdditions, appliedAdditions, totalRemovals, appliedRemovals, totalFailures);
    }

    @Override
    public Model toModel() {
        LinkedHashModel model = new LinkedHashModel();
        URI subject = new URIImpl(this.getChangeApplicationURI());
        model.add(subject, RDF.TYPE, ChangeTrackingVocabulary.CHANGE_APPLICATION);
        model.add(subject, ChangeTrackingVocabulary.HAS_APPLICATION_RESULT,
                new URIImpl(Namespace.CHANGE.concat(this.getStatus())));
        model.add(subject, DCTERMS.IDENTIFIER, new LiteralImpl(this.getIdentifier()));
        if (this.getDescription() != null) {
            model.add(subject, RDFS.COMMENT, new LiteralImpl(this.getDescription()));
        }
        // add statistics
        model.add(subject, stat("totalChangeRequests"), longLiteral(this.getTotalChangeRequests()));
        model.add(subject, stat("appliedChangeRequests"), longLiteral(this.getAppliedChangeRequests()));
        model.add(subject, stat("totalAdditions"), longLiteral(this.getTotalAdditions()));
        model.add(subject, stat("appliedAdditions"), longLiteral(this.getAppliedAdditions()));
        model.add(subject, stat("totalRemovals"), longLiteral(this.getTotalRemovals()));
        model.add(subject, stat("appliedRemovals"), longLiteral(this.getAppliedRemovals()));
        model.add(subject, stat("totalFailures"), longLiteral(this.getTotalFailures()));
        
        return model;
    }

    private Literal longLiteral(long value) {
        return new LiteralImpl(new Long(value).toString(), XMLSchema.LONG);
    }

    private URI stat(String type) {
        return new URIImpl(Namespace.CHANGE.concat(type));
    }

    @Override
    public ChangeTracking fromModel(Model model, URI subject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("not implemented yet");
    }

}
