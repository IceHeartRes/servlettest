package com.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * утилиты по работе с json
 */

@Slf4j
public class JSONUtil {
    /**
     * преобразовать объект в строку json
     * @param object преобразуемый объект
     * @return результат преобразования
     */
    public static String toJSON(Object object) {
        if (object == null)
            return null;

        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("Cannot serialize object [" + object + "] to json object. Reason[" + e.getClass() + "]: " + e.getMessage());
            return null;
        }
    }

    /**
     * преобразовать строку json в объект
     * @param json входная строка
     * @param clazz класс результирующего объекта
     * @param <T>
     * @return возвращаемый объект
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        if (json == null)
            return null;

        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Cannot deserialize string [" + json + "] to object. Reason[" + e.getClass() + "]: " + e.getMessage());
            return null;
        }
    }


}
