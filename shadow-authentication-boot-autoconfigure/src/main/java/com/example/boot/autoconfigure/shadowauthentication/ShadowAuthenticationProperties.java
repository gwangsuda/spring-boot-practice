package com.example.boot.autoconfigure.shadowauthentication;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("auth.shadow")
public class ShadowAuthenticationProperties {

    private boolean skip = false;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}