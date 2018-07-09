package com.test.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class IntegerDeserializerTest {
    @Test
    public void deserialize() throws Exception {
        final Integer value = deserializeInteger("1");

        assertNotNull(value);
        assertEquals(value.intValue(), 1);
    }

    @Test
    public void deserializeNull() throws Exception {
        final Integer value = deserializeInteger(null);

        assertNull(value);
    }

    @Test
    public void deserializeEmpty() throws Exception {
        final Integer value = deserializeInteger("");

        assertNull(value);
    }

    private Integer deserializeInteger(String text) throws IOException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        Mockito.when(parser.getText()).thenReturn(text);
        return new IntegerDeserializer().deserialize(parser, null);
    }
}