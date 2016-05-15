package com.moesounds.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.moesounds.controller.Controller;
import com.moesounds.interceptor.Interceptor;

/**
* This class is used to configure Spring MVC.
*
* @author NYPD
*/
@Configuration
@Import(value={ApplicationConfiguration.class})
@ComponentScan(basePackageClasses={Controller.class, Interceptor.class})
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {	
	
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

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//    	SecurityInterceptor systemAdminSecurityInterceptor = getSystemAdminSecurityInterceptor();
//    	SecurityInterceptor clanAdminSecurityInterceptor = getClanAdminSecurityInterceptor();
//    	
//    	registry.addInterceptor(systemAdminSecurityInterceptor).addPathPatterns("/admin/system/**");
//    }

//	private SecurityInterceptor getSystemAdminSecurityInterceptor(){
//		SecurityInterceptor securityInterceptor = new SecurityInterceptor();
//		securityInterceptor.addAllowedRole(UserRole.SYSTEM_ADMIN.getId());
//		return securityInterceptor;
//	}
}