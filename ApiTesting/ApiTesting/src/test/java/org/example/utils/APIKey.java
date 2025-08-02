package org.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class APIKey {
    public static String getAPIKey() {
        String envKey = System.getenv("REQRES_API_KEY");
        if (envKey != null && !envKey.isEmpty()) {
            return envKey;
        }

        try (InputStream input = APIKey.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty("REQRES_API_KEY");
        } catch (Exception e) {
            throw new RuntimeException("API key not found in environment or config file", e);
        }
    }
}
