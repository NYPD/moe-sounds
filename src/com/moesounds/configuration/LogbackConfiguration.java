package com.moesounds.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.moesounds.annotation.DevelopmentProfile;
import com.moesounds.annotation.ProductionProfile;
import com.moesounds.annotation.TestProfile;
import com.moesounds.util.AppConstants;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.net.SMTPAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.net.SMTPAppenderBase;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

@Configuration
public class LogbackConfiguration {

    private final String encoderPattern = "%d{[yyyy-MM-dd HH:mm:ss.SSS]} [%-5level] \\(%F{0}:%M\\(\\):%L\\) - %msg%n";
    private final String filePattern = "/tomcat/logs/" + AppConstants.PROJECT_NAME + "/moe-logs.%d{yyyy-MM-dd}.log";
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Autowired
    private Environment springEnvironment;

    @Bean
    @DevelopmentProfile
    @TestProfile
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

    @Bean
    @ProductionProfile
    public SMTPAppenderBase<ILoggingEvent> smptAppender() {

        SMTPAppender smtpAppender = new SMTPAppender();
        smtpAppender.setContext(loggerContext);
        smtpAppender.setName("e-mail");
        smtpAppender.setFrom(springEnvironment.getProperty("application.logback.smtp.from"));
        smtpAppender.addTo(springEnvironment.getProperty("application.logback.smtp.to"));
        smtpAppender.setSubject(AppConstants.APPLICATION_NAME + " Exception");
        smtpAppender.setSMTPHost(springEnvironment.getProperty("application.logback.smtp.host"));
        smtpAppender.setSMTPPort(Integer.parseInt(springEnvironment.getProperty("application.logback.smtp.port")));
        smtpAppender.setUsername(springEnvironment.getProperty("application.logback.smtp.username"));
        smtpAppender.setPassword(springEnvironment.getProperty("application.logback.smtp.password"));
        smtpAppender.setSSL(Boolean.parseBoolean(springEnvironment.getProperty("application.logback.smtp.ssl")));
        smtpAppender.setSTARTTLS(Boolean.parseBoolean(springEnvironment.getProperty("application.logback.smtp.tls")));

        smtpAppender.setAsynchronousSending(false);
        smtpAppender.setLayout(getDefaultPattern().getLayout());

        smtpAppender.start();

        return smtpAppender;

    }

    private PatternLayoutEncoder getDefaultPattern() {

        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setPattern(encoderPattern);
        patternLayoutEncoder.start();

        return patternLayoutEncoder;

    }

}
