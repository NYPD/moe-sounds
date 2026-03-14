package com.moesounds.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

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

}
