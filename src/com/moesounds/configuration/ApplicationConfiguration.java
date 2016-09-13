package com.moesounds.configuration;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * Specific app configuration for Spring MVC.
 * 
 * Check out {@link WebConfiguration} for generic web stuff.
 *
 * @author NYPD
 */
@Configuration
@Import(value = {LoggingConfiguration.class, MyBatisConfiguration.class, GoogleConfiguration.class})
@ComponentScan(basePackageClasses = {DAO.class, Service.class})
public class ApplicationConfiguration {

    @Autowired
    private List<Appender<ILoggingEvent>> appenders;

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

    @PostConstruct
    public void initLog4j() {

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.detachAndStopAllAppenders();

        for (Appender<ILoggingEvent> appender : appenders) {
            root.addAppender(appender);

        }

        root.setLevel(Level.DEBUG);
    }

}
