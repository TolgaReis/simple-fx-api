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
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
