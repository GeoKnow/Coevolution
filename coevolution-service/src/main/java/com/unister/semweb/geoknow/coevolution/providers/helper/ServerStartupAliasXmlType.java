package com.unister.semweb.geoknow.coevolution.providers.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.unister.semweb.geoknow.coevolution.providers.XStreamXmlProvider;

/**
 * This will alias the xml types with the xml marsheller, so we can use annotations on our xml types and do all aliasing
 * and caching up front.
 * 
 * @author wookets
 * 
 */
@Component
public class ServerStartupAliasXmlType implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerStartupAliasXmlType.class);

    private boolean hasBeenCreated = false;

    @Autowired
    private XStreamXmlProvider xStreamXmlProvider;
    
//    @Autowired
//    private Environment environment;
    
//    @Value("${base.package}")
    private String basePackage = "com.unister.semweb.geoknow.coevolution";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.hasBeenCreated)
            return;
        if (event instanceof ContextRefreshedEvent) {
            this.hasBeenCreated = true;
            final ComponentClassScanner scanner = new ComponentClassScanner();
            scanner.addIncludeFilter(new AnnotationTypeFilter(XStreamAlias.class));
            final List<Class<?>> classes = scanner.getComponentClasses(basePackage);
            LOGGER.info("Processing XStream annotations for {} classes", classes.size());
            for (final Class<?> c : classes) {
                LOGGER.debug("Processing XStream annotations for {}", c);
                this.xStreamXmlProvider.getxStream().processAnnotations(c);
            }
            // this.objectXmlMarshaller.setSupportedClasses(classes.toArray(new Class[0]));
        }
    }

    public void setXStreamXmlProvider(XStreamXmlProvider xStreamXmlProvider) {
        this.xStreamXmlProvider = xStreamXmlProvider;
    }

}