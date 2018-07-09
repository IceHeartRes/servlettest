package com.test.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * десерилизатор для {@link Integer}
 */

@Slf4j
public class IntegerDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String value = p.getText();
        return value == null || value.isEmpty() ? null : Integer.valueOf(value);
    }
}
