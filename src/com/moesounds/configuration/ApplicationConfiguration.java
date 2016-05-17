package com.moesounds.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.moesounds.dao.DAO;
import com.moesounds.service.Service;

@Configuration
@Import(value={LoggingConfiguration.class, MyBatisConfiguration.class})
@ComponentScan(basePackageClasses={DAO.class, Service.class})
public class ApplicationConfiguration {
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	
//	@Bean
//	public ObjectMapper objectMapper() {
//		PropertyNamingStrategy propertyNamingStrategy = PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    objectMapper.setPropertyNamingStrategy(propertyNamingStrategy);
//	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		return objectMapper;
//	}

}
