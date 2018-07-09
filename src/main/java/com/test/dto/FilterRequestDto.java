package com.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.utils.json.IntegerDeserializer;
import com.test.utils.json.LocalDateDeserializer;
import com.test.utils.json.StringDeserializer;
import com.test.utils.Columns;
import lombok.Data;

import java.time.LocalDate;

/**
 * модель данных реквеста для метода /filter
 * содержит параметры фильтрации запроса в БД
 */

@Data
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRequestDto {
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

    @JsonProperty(Columns.SHIPPED_AFTER)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shippedAfter;

    @JsonProperty(Columns.SHIPPED_BEFORE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate shippedBefore;

    @JsonProperty(Columns.RECEIVE_AFTER)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate receiveAfter;

    @JsonProperty(Columns.RECEIVE_BEFORE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate receiveBefore;

    @JsonProperty(Columns.SORT_COLUMN)
    @JsonDeserialize(using = StringDeserializer.class)
    private String sortColumn;

    @JsonProperty(Columns.SORT_DIRECTION)
    @JsonDeserialize(using = StringDeserializer.class)
    private String sortDirection;
}

