package com.unister.semweb.geoknow.coevolution.resource;

import java.util.Iterator;
import java.util.Set;

import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.util.ModelException;
import org.openrdf.model.vocabulary.DCTERMS;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import com.unister.semweb.geoknow.coevolution.rdf.Namespace;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.rdf.RdfConvertible;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
 * DTO representing a dataset.
 * 
 * @author b.eickmann
 * 
 */
@XStreamAlias("dataset")
public class Dataset implements RdfConvertible<Dataset> {

    @ApiModelProperty(value = "the dataset named graph URI", required = true)
    private String subject;
    @ApiModelProperty(value = "the graphset the named graph belongs to")
    private String graphSet;
    @ApiModelProperty(value = "the type of dataset (URI), typically void:Dataset or void:Linkset", required = true)
    private String type;
    @ApiModelProperty(value = "the dataset label", required = true)
    private String label;
    @ApiModelProperty(value = "the created date, as xsd:DateTime", dataType = "dateTime", required = true)
    private String created;
    @ApiModelProperty(value = "the issued date, i.e. when the dataset was considered final, as xsd:DateTime", dataType = "dateTime", required = false)
    private String issued;
    @ApiModelProperty(value = "the modified date, i.e. when the dataset was last modified, as xsd:DateTime", dataType = "dateTime", required = false)
    private String modified;
    @ApiModelProperty(value = "the dataset description", required = false)
    private String description;
    @ApiModelProperty(value = "licenses that apply to the dataset, as URIs", required = false)
    private String[] licenses;
    @ApiModelProperty(value = "graph URIs which were used as sources during generation of this graph", required = false)
    private String[] sourceGraphs;
    @ApiModelProperty(value = "authors who contributed to generating the graph, can also be processes, e.g., LIMES", required = false)
    private String[] authors;

    /**
     * Returns the URI of the dataset.
     * 
     * @return {@link String}
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the URI of the dataset.
     * 
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the URI of the graphset the subject belongs to.
     * 
     * @return
     */
    public String getGraphSet() {
        return this.graphSet;
    }

    /**
     * Sets the URI of the graphset the subject belongs to.
     * 
     * @param graphSet
     */
    public void setGraphSet(String graphSet) {
        this.graphSet = graphSet;
    }

    /**
     * Returns the type of the dataset.
     * (rdf:type)
     * 
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the dataset.
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the label of the dataset.
     * (rdfs:label)
     * 
     * @return {@link String}
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label of the dataset.
     * 
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the creation time as timestamp in format xsd:date.
     * 
     * @return {@link String}
     */
    public String getCreated() {
        return created;
    }

    /**
     * Sets the creation time as timestamp in format xsd:date.
     * 
     * @param created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * Returns the publication time as timestamp in format xsd:date.
     * (dct:issued)
     * 
     * @return {@link String}
     */
    public String getIssued() {
        return issued;
    }

    /**
     * Sets the publication time as timestamp in format xsd:date.
     * 
     * @param issued
     */
    public void setIssued(String issued) {
        this.issued = issued;
    }

    /**
     * Returns the modification timestamp in format xsd:date.
     * 
     * @return {@link String}
     */
    public String getModified() {
        return modified;
    }

    /**
     * Sets the modification timestamp in format xsd:date.
     * (dct:modified)
     * 
     * @param modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * Returns description of dataset.
     * (dcterms:description or rdfs:description)
     * 
     * @return {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description of dataset.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns licences of dataset.
     * (dataid:licenseName or dct:license, which requires the form odrl:Policies)
     * 
     * @return {@link String}
     */
    public String[] getLicenses() {
        return licenses;
    }

    /**
     * Sets licenses of dataset.
     * 
     * @param licence
     */
    public void setLicenses(String... licences) {
        this.licenses = licences;
    }

    /**
     * Returns source graphs.
     * 
     * @return {@link String}
     */
    public String[] getSourceGraphs() {
        return sourceGraphs;
    }

    /**
     * Sets source graph.
     * 
     * @param sourceGraphs
     */
    public void setSourceGraphs(String[] sourceGraphs) {
        this.sourceGraphs = sourceGraphs;
    }

    /**
     * Returns authors of dataset.
     * 
     * @return {@link String}
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * sets author of dataset.
     * 
     * @param author
     */
    public void setAuthors(String... authors) {
        this.authors = authors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model toModel() {
        LinkedHashModel model = new LinkedHashModel();
        Resource subject = new URIImpl(getSubject());
        if (this.graphSet != null) {
            model.add(subject, Namespace.IN_GRAPHSET, uri(this.graphSet));
        }
        if (type != null) {
            model.add(subject, RDF.TYPE, uri(getType()));
        }
        if (label != null) {
            model.add(subject, RDFS.LABEL, lit(label, "en"));
        }
        if (description != null) {
            model.add(subject, DCTERMS.DESCRIPTION, lit(description, "en"));
        }
        if (issued != null) {
            model.add(subject, DCTERMS.ISSUED, lit(issued, XMLSchema.DATETIME));
        }
        if (modified != null) {
            model.add(subject, DCTERMS.MODIFIED, lit(modified, XMLSchema.DATETIME));
        }
        if (created != null) {
            model.add(subject, DCTERMS.CREATED, lit(created, XMLSchema.DATETIME));
        }
        if (authors != null) {
            for (String author : authors) {
                model.add(subject, DCTERMS.CONTRIBUTOR, uri(author));
            }
        }
        if (licenses != null && licenses.length > 0) {
            for (String license : licenses) {
                model.add(subject, DCTERMS.LICENSE, lit(license));
            }
        }
        if (sourceGraphs != null) {
            for (String sourceGraph : sourceGraphs) {
                model.add(subject, DCTERMS.SOURCE, uri(sourceGraph));
            }
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dataset fromModel(Model model, URI subject) {
        Dataset dataset = new Dataset();
        dataset.setSubject(subject.stringValue());
        dataset.setGraphSet(object(model, subject, Namespace.IN_GRAPHSET));
        dataset.setType(object(model, subject, RDF.TYPE));
        dataset.setLabel(object(model, subject, RDFS.LABEL));
        dataset.setDescription(object(model, subject, DCTERMS.DESCRIPTION));
        dataset.setIssued(object(model, subject, DCTERMS.ISSUED));
        dataset.setModified(object(model, subject, DCTERMS.MODIFIED));
        dataset.setCreated(object(model, subject, DCTERMS.CREATED));
        dataset.setAuthors(objects(model, subject, DCTERMS.CONTRIBUTOR));
        dataset.setLicenses(objects(model, subject, DCTERMS.LICENSE));
        dataset.setSourceGraphs(objects(model, subject, DCTERMS.SOURCE));
        return dataset;
    }

    private String object(Model model, URI subject, URI predicate) {
        try {
            return model.filter(subject, predicate, null).objectString();
        } catch (ModelException e) {
            return null;
        }
    }

    private String[] objects(Model model, URI subject, URI predicate) {
        Set<Value> objects = model.filter(subject, predicate, null).objects();
        Iterator<Value> iter = objects.iterator();
        String[] result = new String[objects.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = iter.next().stringValue();
        }
        return result;
    }

    private URI uri(String uri) {
        return new URIImpl(uri);
    }

    private Literal lit(String literal) {
        return new LiteralImpl(literal);
    }

    private Literal lit(String literal, String lang) {
        return new LiteralImpl(literal, lang);
    }

    private Literal lit(String literal, URI datatype) {
        return new LiteralImpl(literal, datatype);
    }

}
