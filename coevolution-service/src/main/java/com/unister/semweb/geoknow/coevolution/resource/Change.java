package com.unister.semweb.geoknow.coevolution.resource;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Some change.
 * 
 * @author m.wauer
 *
 */
@ApiModel( value = "Change", description = "Change resource representation" )
@XStreamAlias("change")
public class Change {

    @ApiModelProperty(value="the subject to be changed, if not given the subject of change is used", required=false)
    private String subject;
    @ApiModelProperty(value="the predicate to be changed", required=true)
    private String predicate;
    @ApiModelProperty(value="the object to be changed", required=true)
    private String object;
    
    public Change() {
    }

    /**
     * Constructor. 
     * 
     * @param subject the subject (resource URI) of the statement, can be null if the subject of the change request should be used
     * @param predicate the predicate URI of the statement
     * @param object the object value of the statement
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
     * @param subject the subject to set
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
     * @param predicate the predicate to set
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
     * @param object the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }
    

}
