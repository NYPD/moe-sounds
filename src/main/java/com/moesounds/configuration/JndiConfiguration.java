package com.moesounds.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.moesounds.annotation.DevelopmentProfile;
import com.moesounds.annotation.MoeSoundsDataSource;
import com.moesounds.annotation.ProductionProfile;

/**
 * This class lists all the data sources the application uses
 * 
 * @author NYPD
 */
@Configuration
@ProductionProfile
@DevelopmentProfile
public class JndiConfiguration {

    @Bean
    @MoeSoundsDataSource
    public DataSource getMoeSoundsDataSource() throws NamingException {

        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        
        // This corresponds to <res-ref-name>
        bean.setJndiName("java:comp/env/jdbc/ds_moe_sounds");
        
        // This corresponds to <res-type>
        bean.setExpectedType(DataSource.class);
        
        // This simulates <res-auth>Container</res-auth> by ensuring 
        // the bean is managed and looked up via the container context.
        bean.setProxyInterface(DataSource.class);
        
        // Forces the lookup on startup
        bean.setLookupOnStartup(true);
        
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addContextCustomizers(context -> {
            // 1. Create the Resource
            ContextResource resource = new ContextResource();
            resource.setName("jdbc/ds_moe_sounds"); // The name you want
            resource.setType("javax.sql.DataSource");
            resource.setAuth("Container");
            
            // 2. Set MySQL Specific Properties
            resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
            resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            resource.setProperty("url", "jdbc:mysql://localhost:3306/moe_sounds_db");
            resource.setProperty("username", "root");
            resource.setProperty("password", "your_password");

            // 3. Add to Tomcat Context
            context.getNamingResources().addResource(resource);
        });
    }

}
