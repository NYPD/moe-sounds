package com.moesounds.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moesounds.annotation.ProductionProfile;

@Configuration
@ProductionProfile
public class JndiDataSourceConfiguration {

//  @Bean
//  public DataSource getPmsDataSource() throws NamingException {	
//	Context context = new InitialContext();
//  	return (DataSource) context.lookup("java:comp/env/jdbc/ds_pms");
//  }
	
}
