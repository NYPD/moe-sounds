package com.moesounds.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moesounds.annotation.DevelopmentProfile;
import com.moesounds.annotation.ProductionProfile;
import com.moesounds.annotation.TestProfile;
import com.moesounds.util.AppConstants;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

@Configuration
public class LoggingConfiguration {

    private final String encoderPattern = "%d{[yyyy-MM-dd HH:mm:ss.SSS]} [%-5level] \\(%F{0}:%M\\(\\):%L\\) - %msg%n";
    private final String filePattern = "/tomcat/logs/" + AppConstants.PROJECT_NAME + "/moe-logs.%d{yyyy-MM-dd}.log";
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Bean
    @DevelopmentProfile
    @TestProfile
    @ProductionProfile
    public ConsoleAppender<ILoggingEvent> consoleAppender() {

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setName("console");
        consoleAppender.setEncoder(getDefaultPattern());
        consoleAppender.setContext(loggerContext);
        consoleAppender.start();

        return consoleAppender;

    }

    @Bean
    @DevelopmentProfile
    @ProductionProfile
    public RollingFileAppender<ILoggingEvent> rollingFileAppender() {

        RollingFileAppender<ILoggingEvent> rollingFileAppender= new RollingFileAppender<>();
        rollingFileAppender.setName("rolling");
        rollingFileAppender.setContext(loggerContext);

        TimeBasedRollingPolicy<ILoggingEvent> timeBasedRollingPolicy = new TimeBasedRollingPolicy<>();
        timeBasedRollingPolicy.setFileNamePattern(filePattern);
        timeBasedRollingPolicy.setMaxHistory(60);
        timeBasedRollingPolicy.setParent(rollingFileAppender);
        timeBasedRollingPolicy.setContext(loggerContext);
        timeBasedRollingPolicy.start();

        rollingFileAppender.setEncoder(getDefaultPattern());
        rollingFileAppender.setRollingPolicy(timeBasedRollingPolicy);
        rollingFileAppender.start();

        return rollingFileAppender;

    }

    private PatternLayoutEncoder getDefaultPattern() {

        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setPattern(encoderPattern);
        patternLayoutEncoder.start();

        return patternLayoutEncoder;

    }

}
