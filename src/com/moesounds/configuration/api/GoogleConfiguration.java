package com.moesounds.configuration.api;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.moesounds.beans.GoogleSessionBean;

@Configuration
public class GoogleConfiguration {

    @Value("classpath:resource/api/google/client_secret.json")
    private Resource clientSecretsResource;

    @Bean
    public NetHttpTransport netHttpTransport() {
        return new NetHttpTransport();
    }

    @Bean
    public JacksonFactory googleJacksonFactory() {
        return new JacksonFactory();
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets(JacksonFactory googleJacksonFactory) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(clientSecretsResource.getInputStream());
        return GoogleClientSecrets.load(googleJacksonFactory, inputStreamReader);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public GoogleSessionBean googleSessionBean() {
        return new GoogleSessionBean();
    }

}
