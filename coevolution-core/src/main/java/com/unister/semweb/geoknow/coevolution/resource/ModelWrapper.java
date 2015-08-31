package com.unister.semweb.geoknow.coevolution.resource;

import org.openrdf.model.Model;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LinkedHashModel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.RdfConvertible;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Model", description = "RDF Model representation")
@XStreamAlias("model")
public class ModelWrapper implements RdfConvertible<ModelWrapper> {

    @ApiModelProperty("Sesame model")
    private Model model;
    
    /**
     * default constructor.
     */
    public ModelWrapper() {
        this.model = new LinkedHashModel();
    }
    
    public ModelWrapper(Model model) {
        this.model = model;
    }

    @Override
    public Model toModel() {
        return this.model;
    }

    @Override
    public ModelWrapper fromModel(Model model, URI subject) {
        this.model = model;
        return this;
    }

}
