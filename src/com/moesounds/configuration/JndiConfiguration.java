package com.moesounds.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moesounds.annotation.MoeSoundsDataSource;
import com.moesounds.annotation.ProductionProfile;

/**
 * This class lists all the data sources the application uses
 * 
 * @author NYPD
 */
@Configuration
@ProductionProfile
public class JndiConfiguration {

    @Bean
    @MoeSoundsDataSource
    public DataSource getMoeSoundsDataSource() throws NamingException {

        Context context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/ds_moe_sounds");
    }

}
