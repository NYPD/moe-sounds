package com.moesounds.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.configuration.api.GoogleConfiguration;
import com.moesounds.dao.DAO;
import com.moesounds.service.Service;

/**
 * Specific app configuration for Spring MVC.
 * 
 * Check out {@link WebConfiguration} for generic web stuff.
 *
 * @author NYPD
 */
@Configuration
@Import(value = {MyBatisConfiguration.class, GoogleConfiguration.class})
@ComponentScan(basePackageClasses = {DAO.class, Service.class})
public class ApplicationConfiguration {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MoeSoundsSessionBean moeSoundsSessionBean() {
        return new MoeSoundsSessionBean();
    }

}
