package com.moesounds.configuration;


import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    @Bean
    public FileAppender fileAppender() {

        // LoggerContext context= (LoggerContext) LogManager.getContext();
        // org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();
        //
        // config.
        //
        // AppenderRef ref= AppenderRef.createAppenderRef("CONSOLE_APPENDER",null,null);
        // AppenderRef[] refs = new AppenderRef[] {ref};
        // LoggerConfig loggerConfig= LoggerConfig.createLogger("false",
        // Level.INFO,"CONSOLE_LOGGER","com",refs,null,null,null);
        // loggerConfig.addAppender(appender,null,null);
        //
        // config.addAppender(appender);
        // config.addLogger("com", loggerConfig);
        // context.updateLoggers(config);
        return null;
    }

    @Bean
    public ConsoleAppender consoleAppender() {

        ConsoleAppender consoleAppender = new ConsoleAppender();



        // consoleAppender.setThreshold(Level.ALL);
        // PatternLayout patternLayout = new PatternLayout();
        // patternLayout.setConversionPattern("%d %-5p [%c{1}] %m %n");
        // consoleAppender.setLayout(patternLayout);
        return null;
    }
}