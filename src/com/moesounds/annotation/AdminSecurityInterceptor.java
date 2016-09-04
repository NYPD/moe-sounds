package com.moesounds.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

import com.moesounds.domain.enums.UserRole;
import com.moesounds.service.ApiLoginService;
import com.moesounds.service.GoogleLoginService;

/**
 * @see GoogleLoginService
 * @see ApiLoginService
 * 
 * @author NYPD
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface AdminSecurityInterceptor {

    UserRole allowedUserRole = UserRole.ADMIN;
}
