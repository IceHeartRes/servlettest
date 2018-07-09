package com.test.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.test.dto.PartDto;
import com.test.utils.Columns;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * серилизатор для {@link PartDto}
 */

public class PartSerializer extends JsonSerializer<PartDto> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);

    @Override
    public void serialize(PartDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField(Columns.PART_NAME, value.getPartName());
        gen.writeStringField(Columns.PART_NUMBER, value.getPartNumber());
        gen.writeStringField(Columns.VENDOR, value.getVendor());
        gen.writeNumberField(Columns.QTY, value.getQty());
        final LocalDate shippedDate = value.getShipped();
        String shipped = shippedDate == null ? null : shippedDate.format(formatter);
        gen.writeStringField(Columns.SHIPPED, shipped);
        final LocalDate receiveDate = value.getReceive();
        String receive = receiveDate == null ? null : receiveDate.format(formatter);
        gen.writeStringField(Columns.RECEIVE, receive);
        gen.writeEndObject();
    }
}
