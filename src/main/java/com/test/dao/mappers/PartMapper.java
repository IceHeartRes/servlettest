package com.test.dao.mappers;

import com.test.dto.PartDto;
import com.test.utils.Columns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * класс маппер для преобразования из {@link ResultSet} в {@link PartDto}
 */

public class PartMapper {
    /**
     * преобразовать строку из {@link ResultSet} в {@link PartDto}
     * @param resultSet данные из БД
     * @return объект {@link PartDto}
     * @throws SQLException
     */
    public static PartDto mapRow(ResultSet resultSet) throws SQLException {
        final PartDto partDto = new PartDto();
        partDto.setPartNumber(resultSet.getString(Columns.PART_NUMBER));
        partDto.setPartName(resultSet.getString(Columns.PART_NAME));
        partDto.setVendor(resultSet.getString(Columns.VENDOR));
        partDto.setQty(resultSet.getInt(Columns.QTY));
        final LocalDate shipped = resultSet.getTimestamp(Columns.SHIPPED).toLocalDateTime().toLocalDate();
        partDto.setShipped(shipped);
        final LocalDate receive = resultSet.getTimestamp(Columns.RECEIVE).toLocalDateTime().toLocalDate();
        partDto.setReceive(receive);
        return partDto;
    }
}
