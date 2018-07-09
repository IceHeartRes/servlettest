package com.test.utils;

import com.test.dto.FilterRequestDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * утилиты по работе с SQL
 */

public class SqlUtils {

    /**
     * сконструировать запрос в БД от параметров фильтрации
     * @param filterDto параметры фильтрации
     * @return строка SQL запроса
     */
    public static String constructSQL(FilterRequestDto filterDto) {
        return Constants.SELECT_SQL +
                makeFilterParams(filterDto) +
                makeSortParams(filterDto.getSortColumn(), filterDto.getSortDirection());
    }

    private static String makeFilterParams(FilterRequestDto dto) {
        List<String> filters = new ArrayList<>();
        filters.add(makeStringFilterParam(Columns.PART_NAME, dto.getPartName()));
        filters.add(makeStringFilterParam(Columns.PART_NUMBER, dto.getPartNumber()));
        filters.add(makeStringFilterParam(Columns.VENDOR, dto.getVendor()));
        filters.add(makeIntFilterParam(Columns.QTY, dto.getQty()));
        filters.add(makeDateFilterParam(Columns.SHIPPED, dto.getShippedAfter(), dto.getShippedBefore()));
        filters.add(makeDateFilterParam(Columns.RECEIVE, dto.getReceiveAfter(), dto.getReceiveBefore()));

        return " WHERE " + filters.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" and "));
    }

    private static String makeSortParams(String column, String direction) {
        if (column == null) {
            return "";
        }

        String direct = direction == null ? Constants.DEFAULT_DIRECTION : direction;
        return String.format(" ORDER BY %s %s", column, direct);
    }

    private static String makeStringFilterParam(String column, String param) {
        return param != null ? String.format("%s = '%s'", column, param) : null;
    }

    private static String makeIntFilterParam(String column, Integer param) {
        return param != null ? String.format("%s >= %d", column, param) : null;
    }

    private static String makeDateFilterParam(String column, LocalDate afterParam, LocalDate beforeParam) {
        final LocalDate after = afterParam != null ? afterParam : LocalDate.of(1970, 1, 1);
        final LocalDate before = beforeParam != null ? beforeParam : LocalDate.of(2030, 1, 1);
        return String.format("(%s BETWEEN '%s' AND '%s')", column, after, before);
    }
}
