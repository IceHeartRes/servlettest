package com.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.utils.json.IntegerDeserializer;
import com.test.utils.json.LocalDateDeserializer;
import com.test.utils.json.PartSerializer;
import com.test.utils.Columns;
import com.test.utils.json.StringDeserializer;
import lombok.Data;

import java.time.LocalDate;

/**
 * модель данных в БД для таблицы PARTS
 */

@Data
@JsonSerialize(using = PartSerializer.class)
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartDto {
    @JsonProperty(Columns.PART_NAME)
    @JsonDeserialize(using = StringDeserializer.class)
    private String partName;

    @JsonProperty(Columns.PART_NUMBER)
    @JsonDeserialize(using = StringDeserializer.class)
    private String partNumber;

    @JsonProperty(Columns.VENDOR)
    @JsonDeserialize(using = StringDeserializer.class)
    private String vendor;

    @JsonProperty(Columns.QTY)
    @JsonDeserialize(using = IntegerDeserializer.class)
    private Integer qty;

    @JsonProperty(Columns.SHIPPED)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shipped;

    @JsonProperty(Columns.RECEIVE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate receive;
}
