package com.openpayd.simplefxapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Contains the bean definitions to be processed by the Spring container to generate bean definitions and service
 * requests for those beans at runtime.
 */
@Configuration
public class SimpleFxApiConfiguration {
    /**
     * RestTemplate bean to use as rest client for third part APIs.
     * @return rest template to use client functions.
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
