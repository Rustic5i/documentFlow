package com.example.document_flow.config.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFileProperties {

    private final Properties PROPERTIES = new Properties();

    private String RESOURCE_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private String CONFIG_PATH = RESOURCE_PATH+"config/config.properties";

    private static ReadFileProperties readFileProperties;

    public ReadFileProperties() {
        try {
            PROPERTIES.load(new FileInputStream(CONFIG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return null;
    }
}
