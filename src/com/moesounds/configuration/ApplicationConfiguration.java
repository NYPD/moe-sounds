package com.moesounds.configuration;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.moesounds.dao.DAO;
import com.moesounds.service.Service;

@Configuration
@Import(value={LoggingConfiguration.class, MyBatisConfiguration.class})
@ComponentScan(basePackageClasses={DAO.class, Service.class})
public class ApplicationConfiguration {
	
	@Value("classpath:resource/api/client_secret.json")
	Resource clientSecretsResource;
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	@Bean
	public JacksonFactory jacksonFactory() {
		return new JacksonFactory();
	}
	
	@Bean
	public GoogleClientSecrets googleClientSecrets(JacksonFactory jacksonFactory) {
		
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(clientSecretsResource.getInputStream());
			return GoogleClientSecrets.load(jacksonFactory, inputStreamReader);
	    } catch (IOException e) {
	    	throw new Error("No client_secrets.json found", e);
	    }
		
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
