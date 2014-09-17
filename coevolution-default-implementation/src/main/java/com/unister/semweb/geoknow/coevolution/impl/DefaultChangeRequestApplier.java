package com.unister.semweb.geoknow.coevolution.impl;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openrdf.model.Model;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.model.impl.ContextStatementImpl;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;

import com.google.common.collect.Sets;
import com.unister.semweb.geoknow.coevolution.ChangeRequestApplier;
import com.unister.semweb.geoknow.coevolution.ChangeRequestDao;
import com.unister.semweb.geoknow.coevolution.resource.Change;
import com.unister.semweb.geoknow.coevolution.resource.IdentifiedChangeRequest;

/**
 * Default implementation of {@link ChangeRequestApplier}.
 * 
 * @author m.wauer
 * 
 */
public class DefaultChangeRequestApplier implements ChangeRequestApplier {

    private ChangeRequestDao changeRequestDao;

    public DefaultChangeRequestApplier(ChangeRequestDao changeRequestDao) {
        this.changeRequestDao = changeRequestDao;
    }

    @Override
    public Model applyChangeRequest(Model model, String resultContext) throws IOException {
        // create copy
        LinkedHashModel copy = new LinkedHashModel(model);

        Set<Resource> subjects = model.subjects();

        // load change sets to apply (first change set for each resource)
        Set<IdentifiedChangeRequest> startChanges = Sets.newHashSet();
        for (Resource subject : subjects) {
            IdentifiedChangeRequest changeRequest = changeRequestDao.readFirstForSubject(subject.stringValue());
            startChanges.add(changeRequest);
        }

        for (IdentifiedChangeRequest changeRequest : startChanges) {
            applyChangeRequestChain(copy, changeRequest, resultContext);
        }

        return copy;
    }

    protected void applyChangeRequestChain(Model model, IdentifiedChangeRequest changeRequest,
            String resultContext) throws IOException {
        applyChangeRequest(model, changeRequest, resultContext);
        Set<IdentifiedChangeRequest> successors = changeRequestDao.readSuccessor(changeRequest.getIdentifier());
        for (IdentifiedChangeRequest successor : successors) {
            applyChangeRequestChain(model, successor, resultContext);
        }
    }

    protected void applyChangeRequest(Model model, IdentifiedChangeRequest changeRequest, String resultContext) {
        for (Change removal : changeRequest.getRemovals()) {
            Statement removalStatement = createStatement(removal, resultContext);
            model.remove(removalStatement);
        }
        for (Change addition : changeRequest.getAdditions()) {
            Statement additionStatement = createStatement(addition, resultContext);
            model.add(additionStatement);
        }
    }

    private static Statement createStatement(Change change, String resultContext) {
        Value object = parseValue(change.getObject());
        ContextStatementImpl changeStatement = new ContextStatementImpl(new URIImpl(change.getSubject()),
                new URIImpl(change.getPredicate()), object, new URIImpl(resultContext));
        return changeStatement;
    }

    private static Value parseValue(String object) {
        if (object.startsWith("\"")) {
            // literal
            int lastQuote = StringUtils.lastIndexOf(object, "\"");
            String objectStringValue = StringUtils.substring(object, 1, lastQuote);
            String objectDataType = null;
            String objectLanguage = null;
            if (object.length() > lastQuote) {
                int dataTypeLt = StringUtils.indexOf(object, "<", lastQuote);
                if (-1 != dataTypeLt) {
                    objectDataType = StringUtils.substring(object, dataTypeLt + 1, object.length() - 1);
                    return new LiteralImpl(objectStringValue, new URIImpl(objectDataType));
                }
                int languageAt = StringUtils.indexOf(object, "@", lastQuote);
                if (-1 != languageAt) {
                    objectLanguage = StringUtils.substring(object, languageAt + 1, object.length() - 1);
                    return new LiteralImpl(objectStringValue, objectLanguage);
                }
            }
            return new LiteralImpl(objectStringValue);
        }
        else
            return new URIImpl(object);
    }

}
