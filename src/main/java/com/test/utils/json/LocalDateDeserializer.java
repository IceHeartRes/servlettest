package com.test.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.test.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;

/**
 * десерилизатор для {@link LocalDate}
 */

@Slf4j
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return LocalDate.parse(p.getText(), Constants.FORMATER);
        } catch (Exception e) {
            return null;
        }

    }
}
