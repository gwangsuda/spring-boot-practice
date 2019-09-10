package com.example.boot.autoconfigure.shadowauthentication;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.codec.digest.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShadowAuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(ShadowAuthenticationProvider.class);

    private boolean skip = false;
    
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean authenticate(String id, String password) {
        
        if (isSkip()) {
            return true;
        }

        if (Objects.isNull(id) || Objects.isNull(password)) {
            logger.debug("id or password is null");
            return false;
        }
        
        try {
            String command = String.format("sudo cat /etc/shadow | grep %s", id);
            String result = SimpleCommandExecutor.execute("/bin/bash", "-c", command);

            String shadowPassword = result.split(":")[1];
            String cryptedPassword = Crypt.crypt(password, shadowPassword);

            logger.debug("\n shadowPassword  = {} \n cryptedPassword = {}", shadowPassword, cryptedPassword);
            
            return shadowPassword.contentEquals(cryptedPassword);
            
        } catch (IOException | InterruptedException ex) {
            logger.debug("command execution failed", ex);
        }
        
        return false;
    }
}