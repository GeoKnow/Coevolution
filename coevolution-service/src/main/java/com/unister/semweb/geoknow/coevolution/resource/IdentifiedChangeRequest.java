package com.unister.semweb.geoknow.coevolution.resource;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * A change request which has been stored by the ChangeSet Management Service, thus got an identifier (GUID) and,
 * optionally, a preceding change request identifier.
 * 
 * @author m.wauer
 * 
 */
@ApiModel( value = "Identified Change Request", description = "Change request resource representation with identifier" )
@XStreamAlias("identifiedChangeRequest")
public class IdentifiedChangeRequest extends ChangeRequest {

    /**
     * generated
     */
    private static final long serialVersionUID = -4995264275059362169L;

    @ApiModelProperty(value = "the identifier GUID", required = true)
    protected String identifier;

    @ApiModelProperty(value = "the identifier GUID of a preceding change request, if any", required = false)
    protected String precedingChangeRequestIdentifier;

    /**
     * Super constructor, protected.
     * 
     * @param subject
     * @param removals
     * @param additions
     */
    protected IdentifiedChangeRequest(String subject, Change[] removals, Change[] additions) {
        super(subject, removals, additions);
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
    public IdentifiedChangeRequest(String identifier, String subject, Change[] removals, Change[] additions) {
        this(subject, removals, additions);
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

}
