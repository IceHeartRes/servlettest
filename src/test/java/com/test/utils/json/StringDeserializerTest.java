package com.test.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class StringDeserializerTest {
    @Test
    public void deserialize() throws Exception {
        final String value = deserializeString("str");

        assertNotNull(value);
        assertEquals(value, "str");
    }

    @Test
    public void deserializeNull() throws Exception {
        final String value = deserializeString(null);

        assertNull(value);
    }

    @Test
    public void deserializeEmpty() throws Exception {
        final String value = deserializeString("");

        assertNull(value);
    }

    private String deserializeString(String text) throws IOException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        Mockito.when(parser.getText()).thenReturn(text);
        return new StringDeserializer().deserialize(parser, null);
    }

}