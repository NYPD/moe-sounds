package com.moesounds.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.moesounds.controller.Controller;
import com.moesounds.domain.enums.UserRole;
import com.moesounds.interceptor.SecurityInterceptor;

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
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final String[] INCLUDED_PATTERNS = {"/admin/**"};
    private static final String[] EXCLUDED_PATTERNS = {"/admin", "/admin/api/*"};

    @Bean
    public SecurityInterceptor adminSecurityInterceptor() {
        return new SecurityInterceptor(UserRole.ADMIN);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
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
        .addPathPatterns(INCLUDED_PATTERNS)
        .excludePathPatterns(EXCLUDED_PATTERNS);
    }

}