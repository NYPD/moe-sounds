package com.moesounds.configuration;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.configuration.api.GoogleConfiguration;
import com.moesounds.dao.DAO;
import com.moesounds.service.Service;
import com.moesounds.util.AppConstants;

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
@PropertySource(value = "classpath:resource/project.properties")
public class ApplicationConfiguration {

    @Autowired(required = false)
    private ServletContext servletContext;
    @Autowired
    private Environment springEnvironment;
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
    public void initLogbackAppenders() {

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.detachAndStopAllAppenders();

        for (Appender<ILoggingEvent> appender : appenders)
            root.addAppender(appender);

        String[] activeProfiles = springEnvironment.getActiveProfiles();

        boolean isDevelopment = Arrays.stream(activeProfiles).filter(x -> AppConstants.DEVELOPMENT_PROFILE.equals(x)).findAny().orElse(null) != null;

        Level loggingLevel = isDevelopment ? Level.DEBUG : Level.INFO;

        root.setLevel(loggingLevel);
    }

    @PostConstruct
    public void addServletContextProperties() {

        if (servletContext != null)
            servletContext.setAttribute("projectVersion", springEnvironment.getProperty("application.version"));
    }

}
