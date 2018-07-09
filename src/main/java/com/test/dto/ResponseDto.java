package com.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * модель ответа для метода /filter
 * содержит список прочитанных из БД данных
 */

@Data
@JsonSerialize
@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {
    List<PartDto> parts;

    public ResponseDto() {
    }

    public ResponseDto(List<PartDto> parts) {
        this.parts = parts;
    }
}
