package com.moesounds.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moesounds.annotation.DevelopmentProfile;
import com.moesounds.annotation.ProductionProfile;
import com.moesounds.util.AppConstants;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;

@Configuration
public class LoggingConfiguration {

    private final String pattern = "%d{[yyyy-MM-dd HH:mm:ss.SSS]} [%-5level] \\(%F{0}:%M\\(\\):%L\\) - %msg%n";
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Bean
    @DevelopmentProfile
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
        rollingFileAppender.setEncoder(getDefaultPattern());
        rollingFileAppender.setContext(loggerContext);

        TimeBasedRollingPolicy<ILoggingEvent> timeBasedRollingPolicy = new TimeBasedRollingPolicy<>();
        timeBasedRollingPolicy.setContext(loggerContext);
        timeBasedRollingPolicy.setParent(rollingFileAppender);
        timeBasedRollingPolicy.setFileNamePattern("/logs/webapps/" + AppConstants.PROJECT_NAME + "/moe-logs.%d{yyyy-MM-dd}.log");
        timeBasedRollingPolicy.setMaxHistory(1);
        timeBasedRollingPolicy.setTotalSizeCap(FileSize.valueOf("1 gb"));
        timeBasedRollingPolicy.start();

        rollingFileAppender.setRollingPolicy(timeBasedRollingPolicy);
        rollingFileAppender.start();

        return rollingFileAppender;

    }

    private PatternLayoutEncoder getDefaultPattern() {

        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setPattern(pattern);
        patternLayoutEncoder.start();

        return patternLayoutEncoder;

    }

}
