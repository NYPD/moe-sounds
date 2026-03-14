package com.moesounds.configuration;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.moesounds.controller.Controller;
import com.moesounds.domain.enums.UserRole;
import com.moesounds.interceptor.SecurityInterceptor;

import jakarta.annotation.PostConstruct;

/**
 * This class is used to configure Spring MVC. Deals more with "server" stuff rather than specific
 * application configuration.
 * 
 * Check out {@link ApplicationConfiguration} for specific app configuration
 *
 * @author NYPD
 */
@Configuration
@Import(value = {ApplicationConfiguration.class})
@ComponentScan(basePackageClasses = {Controller.class})
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private ServletContext servletContext;
    private static final String[] ADMIN_INCLUDED_PATTERNS = {"/admin/**"};
    private static final String[] ADMIN_EXCLUDED_PATTERNS = {"/admin", "/admin/api/*"};


    @Bean
    public SecurityInterceptor adminSecurityInterceptor() {
        return new SecurityInterceptor(UserRole.ADMIN);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminSecurityInterceptor())
        .addPathPatterns(ADMIN_INCLUDED_PATTERNS)
        .excludePathPatterns(ADMIN_EXCLUDED_PATTERNS);
    }

    @PostConstruct
    public void initServletContext() {
        servletContext.setAttribute("context", servletContext.getContextPath());
    }

}