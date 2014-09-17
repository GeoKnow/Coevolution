package com.unister.semweb.geoknow.coevolution.providers.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.util.ClassUtils;

/**
 * This will scan the classpath for certain classes that you may want to register based on annotations
 * or something similar... This uses ASM and spring stuff, so we know it is good.
 * 
 * usage
 * 
 * final ComponentClassScanner scanner = new ComponentClassScanner();
 * scanner.addIncludeFilter(new AnnotationTypeFilter(DbRevision.class));
 * final List<Class<? extends Object>> classes = scanner.getComponentClasses(this.CLASS_PATH);
 * 
 * @author wookets
 * 
 */
public class ComponentClassScanner extends ClassPathScanningCandidateComponentProvider {
    public ComponentClassScanner() {
        super(false);
    }

    public final List<Class<? extends Object>> getComponentClasses(String basePackage) {
        basePackage = basePackage == null ? "" : basePackage;
        final List<Class<? extends Object>> classes = new ArrayList<Class<? extends Object>>();
        for (final BeanDefinition candidate : this.findCandidateComponents(basePackage)) {
            try {
                final Class<? extends Object> cls = ClassUtils.resolveClassName(candidate.getBeanClassName(),
                        ClassUtils.getDefaultClassLoader());
                classes.add(cls);
            } catch (final Throwable ex) {
                ex.printStackTrace();
            }
        }
        return classes;
    }
}