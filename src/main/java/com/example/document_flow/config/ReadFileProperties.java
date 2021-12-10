package com.example.document_flow.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс обертка над классом <code>Properties</code>
 *
 * @author Баратов Руслан
 */
public class ReadFileProperties {

    private final Properties PROPERTIES = new Properties();

    private final String RESOURCE_PATH = "config/config.properties";

    private final InputStream INPUT_STREAM_PROPERTIES_FILE = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH),
            "Папка ресурсов"+RESOURCE_PATH+" не найдено");

    /**
     * Считывает список свойств (пары ключей и элементов) из файла формата properties
     *
     */
    public synchronized void load() {
        try {
            PROPERTIES.load(INPUT_STREAM_PROPERTIES_FILE);
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
