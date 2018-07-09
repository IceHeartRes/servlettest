package com.test.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * чтение параметров приложения
 */
@Slf4j
public class PropertiesReader {
    /**
     * прочитать свойства из файла config.properties
     * @return объект {@link Properties}
     */
    public static Properties getProperties() {
        InputStream inputStream = PropertiesReader.class.getClassLoader()
                .getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("missing_load_properties", e);
        }
        return properties;
    }
}
