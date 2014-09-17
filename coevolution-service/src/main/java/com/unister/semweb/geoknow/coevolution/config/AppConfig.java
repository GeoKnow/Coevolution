package com.unister.semweb.geoknow.coevolution.config;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.unister.semweb.geoknow.coevolution.providers.TurtleProvider;
import com.unister.semweb.geoknow.coevolution.providers.XStreamXmlProvider;
import com.unister.semweb.geoknow.coevolution.providers.helper.ServerStartupAliasXmlType;
import com.unister.semweb.geoknow.coevolution.resource.Change;
import com.unister.semweb.geoknow.coevolution.rs.ChangeSetApplicationRestService;
import com.unister.semweb.geoknow.coevolution.rs.ChangeSetManagementRestService;
import com.unister.semweb.geoknow.coevolution.rs.ChangeSetSyncRestService;
import com.unister.semweb.geoknow.coevolution.rs.GraphVersioningRestService;
import com.unister.semweb.geoknow.coevolution.rs.JaxRsApiApplication;
import com.unister.semweb.geoknow.coevolution.services.ChangeSetApplicationService;
import com.unister.semweb.geoknow.coevolution.services.ChangeSetManagementService;
import com.unister.semweb.geoknow.coevolution.services.ChangeSetSyncService;
import com.unister.semweb.geoknow.coevolution.services.GraphVersioningService;
import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    public static final String SERVER_PORT = "server.port";
    public static final String SERVER_HOST = "server.host";
    public static final String CONTEXT_PATH = "context.path";
    public static final String BASE_PACKAGE = "base.package";
    public static final String VERSION = "1.0.0";

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(),
                JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object> asList(changeSetManagementRestService(),
                changeSetApplicationRestService(), changeSetSyncRestService(), graphVersioningRestService(), 
                apiListingResourceJson()));
        factory.setAddress(factory.getAddress());
        factory.setProviders(Arrays.<Object> asList(jsonProvider(), xstreamXmlProvider(), turtleProvider(),
                resourceListingProvider(),
                apiDeclarationProvider()));
        return factory.create();
    }

    @Bean
    @Autowired
    public BeanConfig swaggerConfig(Environment environment) {
        final BeanConfig config = new BeanConfig();

        String basePath = String.format("http://%s:%s/%s%s",
                environment.getProperty(SERVER_HOST),
                environment.getProperty(SERVER_PORT),
                environment.getProperty(CONTEXT_PATH),
                jaxRsServer().getEndpoint().getEndpointInfo().getAddress()
                );
        config.setVersion(VERSION);
        config.setDescription("GeoKnow Generator Co-Evolution Services");
        config.setTermsOfServiceUrl("http://generator.geoknow.eu");
        config.setScan(true);
        config.setResourcePackage(Change.class.getPackage().getName());
        config.setBasePath(basePath);

        return config;
    }

    @Bean
    public ApiDeclarationProvider apiDeclarationProvider() {
        return new ApiDeclarationProvider();
    }

    @Bean
    public ApiListingResourceJSON apiListingResourceJson() {
        return new ApiListingResourceJSON();
    }

    @Bean
    public ResourceListingProvider resourceListingProvider() {
        return new ResourceListingProvider();
    }

    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }

    @Bean
    public ChangeSetManagementRestService changeSetManagementRestService() {
        return new ChangeSetManagementRestService();
    }

    @Bean
    public ChangeSetManagementService changeSetManagementService() {
        return new ChangeSetManagementService();
    }


    @Bean
    public ChangeSetApplicationRestService changeSetApplicationRestService() {
        return new ChangeSetApplicationRestService();
    }
    
    @Bean
    public ChangeSetApplicationService changeSetApplicationService() {
        return new ChangeSetApplicationService();
    }
    
    
    @Bean
    public ChangeSetSyncRestService changeSetSyncRestService() {
        return new ChangeSetSyncRestService();
    }

    @Bean
    public ChangeSetSyncService changeSetSyncService() {
        return new ChangeSetSyncService();
    }
    
    
    @Bean
    public GraphVersioningRestService graphVersioningRestService() {
        return new GraphVersioningRestService();
    }

    @Bean
    public GraphVersioningService graphVersioningService() {
        return new GraphVersioningService();
    }
    
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public Object xstreamXmlProvider() {
        return new XStreamXmlProvider();
    }

    @Bean
    public Object turtleProvider() {
        return new TurtleProvider();
    }

    @Bean
    public ApplicationListener<ContextRefreshedEvent> xstreamAnnotationListener() {
        return new ServerStartupAliasXmlType();
    }

}
