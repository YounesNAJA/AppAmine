package com.younesnaja.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesManager {
    private static Properties properties;

    public static void loadPropertiesFile() throws IOException {
        properties = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = Constants.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public static String getProperty(String propertyKey){
        return properties.getProperty(propertyKey).replaceAll(";", "").replaceAll("\"", "");
    }
}
