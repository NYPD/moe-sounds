package com.moesounds.configuration.api;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;

@Configuration
public class GoogleConfiguration {

    @Value("classpath:resource/api/google/client_secret.json")
    private Resource clientSecretsResource;

    @Bean(name = "googleJacksonFactory")
    public JacksonFactory googleJacksonFactory() {

        return new JacksonFactory();
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets(JacksonFactory googleJacksonFactory) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(clientSecretsResource.getInputStream());
        return GoogleClientSecrets.load(googleJacksonFactory, inputStreamReader);
    }
}
