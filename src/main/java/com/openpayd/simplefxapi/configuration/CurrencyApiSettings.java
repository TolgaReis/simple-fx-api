package com.openpayd.simplefxapi.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Settings for currency API client.
 */
@Configuration
@ConfigurationProperties("currency.api")
@Getter
@Setter
public class CurrencyApiSettings {
    private String url;
    private String key;
}
