package com.test.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class LocalDateDeserializerTest {
    @Test
    public void deserialize() throws Exception {
        final LocalDate date = deserializeLocalDate("Feb 01, 2018");

        assertNotNull(date);
        final LocalDate localDate = LocalDate.of(2018, 2, 1);
        assertEquals(date,localDate);
    }

    @Test
    public void deserializeWrong() throws Exception {
        final LocalDate date = deserializeLocalDate("2018-2-1");

        assertNull(date);
    }

    @Test
    public void deserializeNull() throws Exception {
        final LocalDate date = deserializeLocalDate(null);

        assertNull(date);
    }

    @Test
    public void deserializeEmpty() throws Exception {
        final LocalDate date = deserializeLocalDate("");

        assertNull(date);
    }

    private LocalDate deserializeLocalDate(String text) throws IOException {
        JsonParser parser= Mockito.mock(JsonParser.class);
        Mockito.when(parser.getText()).thenReturn(text);
        return new LocalDateDeserializer().deserialize(parser, null);
    }

}