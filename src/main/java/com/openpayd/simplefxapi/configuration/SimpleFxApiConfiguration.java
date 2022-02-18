package com.openpayd.simplefxapi.configuration;

import com.openpayd.simplefxapi.service.FxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleFxApiConfiguration {
    @Bean
    public FxService getFxService() {
        return new FxService();
    }
}
