package com.moesounds.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

import com.moesounds.configuration.JndiConfiguration;
import com.moesounds.configuration.MyBatisConfiguration;

/**
 * @see JndiConfiguration
 * @see MyBatisConfiguration
 * 
 * @author NYPD
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface MoeSoundsDataSource
{}

