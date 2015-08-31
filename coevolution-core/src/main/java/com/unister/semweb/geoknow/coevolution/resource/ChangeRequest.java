package com.unister.semweb.geoknow.coevolution.resource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.RdfUtils;
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
    @ApiModelProperty(value = "the creation date of the change request", dataType = "date", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RdfUtils.DATE_FORMAT_PATTERN, timezone = "UTC")
    protected Date createdDate;
    @ApiModelProperty(value = "the statements to be removed", required = false)
    protected Change[] removals;
    @ApiModelProperty(value = "the statements to be added", required = false)
    protected Change[] additions;
    @ApiModelProperty(value = "whether the change request has been verified", required = false)
    protected Boolean verified;
    @ApiModelProperty(value = "the context of the change request, i.e., the named graph", required = false)
    protected String context;

    public ChangeRequest() {

    }

    /**
     * Constructor. Either removals or additions must be non-empty.
     * 
     * @param subject the subject (resource URI) to be changed
     * @param removals an array of {@link Change} statements to be removed
     * @param additions an array of {@link Change} statements to be added
     */
    public ChangeRequest(String subject, Change[] removals, Change[] additions, String context) {
        super();
        this.subject = subject;
        this.removals = removals;
        this.additions = additions;
        this.context = context;
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

    /**
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("ChangeRequest [subject=%s, author=%s, reason=%s, createdDate=%s, removals=%s, additions=%s, verified=%s, context=%s]",
                        subject, author, reason, createdDate, Arrays.toString(removals), Arrays.toString(additions),
                        verified, context);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(additions);
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((context == null) ? 0 : context.hashCode());
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
        result = prime * result + Arrays.hashCode(removals);
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((verified == null) ? 0 : verified.hashCode());
        return result;
    }

    /* (non-Javadoc)
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
        ChangeRequest other = (ChangeRequest) obj;
        if (!Arrays.equals(additions, other.additions))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (context == null) {
            if (other.context != null)
                return false;
        } else if (!context.equals(other.context))
            return false;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (reason == null) {
            if (other.reason != null)
                return false;
        } else if (!reason.equals(other.reason))
            return false;
        if (!Arrays.equals(removals, other.removals))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (verified == null) {
            if (other.verified != null)
                return false;
        } else if (!verified.equals(other.verified))
            return false;
        return true;
    }

}
