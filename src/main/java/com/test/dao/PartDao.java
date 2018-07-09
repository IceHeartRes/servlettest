package com.test.dao;

import com.test.dao.mappers.PartMapper;
import com.test.dto.FilterRequestDto;
import com.test.dto.PartDto;
import com.test.utils.JdbcConnector;
import com.test.utils.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * класс транспорта до базы данных
 */

public class PartDao extends JdbcConnector {
    /**
     * прочитать данные из БД
     * @param filterDto патаметры фильтра
     * @return список объектов {@link PartDto}
     */
    public static List<PartDto> readParts(FilterRequestDto filterDto) {
        List<PartDto> parts = new ArrayList<>();

        try {
            Statement statement = getStatement();

            final String sql = SqlUtils.constructSQL(filterDto);
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                parts.add(PartMapper.mapRow(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("exception_while_db_reading", e);
        }
        return parts;
    }
}
