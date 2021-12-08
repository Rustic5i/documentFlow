package com.example.document_flow.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ReadFileProperties {

    private final Properties PROPERTIES = new Properties();

    private final String RESOURCE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""),
            "Папка ресурсов не найдено").getPath();

    public synchronized void load(String propertiesPath) {
        try {
            PROPERTIES.load(new FileInputStream(RESOURCE_PATH + propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выполняет поиск свойства с указанным ключом в списке свойств
     *
     * @param key - ключ свойства
     * @return значение из списка свойств
     */
    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
