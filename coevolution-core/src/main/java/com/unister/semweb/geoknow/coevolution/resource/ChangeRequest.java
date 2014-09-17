package com.unister.semweb.geoknow.coevolution.resource;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * A change request to be posted to the ChangeSet Management Service.
 * 
 * @author m.wauer
 * 
 */
@ApiModel( value = "Change Request", description = "Change request resource representation" )
@XStreamAlias("changeRequest")
public class ChangeRequest implements Serializable {

    /**
     * generated
     */
    private static final long serialVersionUID = 697760352241774780L;

    @ApiModelProperty(value = "the subject to be changed", required = true)
    protected String subject;
    @ApiModelProperty(value = "the author of the change request", required = false)
    protected String author;
    @ApiModelProperty(value = "the reason for the change request", required = false)
    protected String reason;
    @ApiModelProperty(value = "the creation date of the change request", required = false)
    protected Date createdDate;
    @ApiModelProperty(value = "the statements to be removed", required = false)
    protected Change[] removals;
    @ApiModelProperty(value = "the statements to be added", required = false)
    protected Change[] additions;
    @ApiModelProperty(value = "whether the change request has been verified", required = false)
    protected Boolean verified;

    public ChangeRequest() {

    }

    /**
     * Constructor. Either removals or additions must be non-empty.
     * 
     * @param subject the subject (resource URI) to be changed
     * @param removals an array of {@link Change} statements to be removed
     * @param additions an array of {@link Change} statements to be added
     */
    public ChangeRequest(String subject, Change[] removals, Change[] additions) {
        super();
        this.subject = subject;
        this.removals = removals;
        this.additions = additions;
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
     * @return the removals
     */
    public Change[] getRemovals() {
        return removals;
    }

    /**
     * @param removals
     *            the removals to set
     */
    public void setRemovals(Change[] removals) {
        this.removals = removals;
    }

    /**
     * @return the additions
     */
    public Change[] getAdditions() {
        return additions;
    }

    /**
     * @param additions
     *            the additions to set
     */
    public void setAdditions(Change[] additions) {
        this.additions = additions;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
