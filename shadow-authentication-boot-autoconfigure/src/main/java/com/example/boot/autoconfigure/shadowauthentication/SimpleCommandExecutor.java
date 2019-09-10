package com.example.boot.autoconfigure.shadowauthentication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleCommandExecutor {

    private SimpleCommandExecutor() {
    }
    
    public static String execute(String... command) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getInputStream())));
        
        String buffer = null;
        while ((buffer = reader.readLine()) != null) {
            builder.append(buffer);
        }
        
        process.waitFor();
        
        return builder.toString();
    }
}
