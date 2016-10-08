package com.moesounds.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

import com.moesounds.util.AppConstants;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile(AppConstants.TEST_PROFILE)
public @interface TestProfile {}