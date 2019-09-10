package com.example.boot.autoconfigure.shadowauthentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ShadowAuthenticationProvider.class)
@EnableConfigurationProperties(ShadowAuthenticationProperties.class)
public class ShadowAuthenticationAutoConfiguration {

    private final ShadowAuthenticationProperties properties;
    
    public ShadowAuthenticationAutoConfiguration(ShadowAuthenticationProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ShadowAuthenticationProvider shadowAuthenticationProvider() {
        
        ShadowAuthenticationProvider provider = new ShadowAuthenticationProvider();
        provider.setSkip(properties.isSkip());
        
        return provider;
    }
}