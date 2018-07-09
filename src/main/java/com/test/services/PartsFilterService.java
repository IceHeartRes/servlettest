package com.test.services;

import com.test.dao.PartDao;
import com.test.dto.FilterRequestDto;
import com.test.dto.PartDto;
import com.test.dto.ResponseDto;

import java.util.List;

/**
 * сервис для обработки бизнес логики
 */

public class PartsFilterService {
    /**
     * выполнить фильтрацию по полученным параметра
     * @param filterDto параметры фильтрации
     * @return список прочитанных из БД записай типа {@link PartDto}
     */
    public static ResponseDto filter(FilterRequestDto filterDto) {
        final List<PartDto> parts = PartDao.readParts(filterDto);
        return new ResponseDto(parts);
    }
}
