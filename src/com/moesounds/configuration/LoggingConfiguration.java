package com.moesounds.configuration;

import java.nio.charset.Charset;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public ConsoleAppender consoleAppender() {

        PatternLayout patternLayout = PatternLayout.newBuilder()
                .withPattern("%-5p (%F:%M():%L) - %m%n")
                .withCharset(Charset.defaultCharset())
                .build();

        ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(patternLayout);
        return appender;
    }
}