package com.test.dao;

import com.test.dto.FilterRequestDto;
import com.test.dto.PartDto;
import com.test.utils.Columns;
import com.test.utils.JdbcConnector;
import com.test.utils.PropertiesReader;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PartDaoTest extends JdbcConnector {


    public static final String NUMBER = "number2";
    public static final String NUMBER_ = "number1";
    public static final String NAME = "name2";
    public static final String VENDOR = "vendor2";
    public static final Integer QTY = 1;
    public static final LocalDate SHIPPED_AFTER = LocalDate.of(2018, 1, 1);
    public static final LocalDate SHIPPED_BEFORE = LocalDate.of(2018, 1, 4);
    public static final LocalDate RECEIVE_AFTER = LocalDate.of(2018, 1, 1);
    public static final LocalDate RECEIVE_BEFORE = LocalDate.of(2018, 1, 4);

    @Before
    public void setUp() throws Exception {
        InputStream resourceAsStream = PropertiesReader.class.getClassLoader()
                .getResourceAsStream("create.sql");
        String createSql = IOUtils.toString(resourceAsStream);

        final Statement statement = getStatement();
        statement.execute(createSql);

    }


    @Test
    public void readPartsAll() throws Exception {
        final List<PartDto> partDtos = PartDao.readParts(new FilterRequestDto());

        assertNotNull(partDtos);
        assertEquals(partDtos.size(), 2);
    }

    @Test
    public void readPartsFiltered() throws Exception {
        final FilterRequestDto filterRequestDto = new FilterRequestDto();
        filterRequestDto.setPartNumber(NUMBER);
        filterRequestDto.setPartName(NAME);
        filterRequestDto.setVendor(VENDOR);
        filterRequestDto.setQty(QTY);
        filterRequestDto.setShippedAfter(SHIPPED_AFTER);
        filterRequestDto.setShippedBefore(SHIPPED_BEFORE);
        filterRequestDto.setReceiveAfter(RECEIVE_AFTER);
        filterRequestDto.setReceiveBefore(RECEIVE_BEFORE);

        final List<PartDto> partDtos = PartDao.readParts(filterRequestDto);

        assertNotNull(partDtos);
        assertEquals(partDtos.size(), 1);
        final PartDto partDto = partDtos.get(0);
        assertEquals(partDto.getPartNumber(), NUMBER);
        assertEquals(partDto.getPartName(), NAME);
        assertEquals(partDto.getVendor(), VENDOR);
    }

    @Test
    public void readPartsSorted() throws Exception {
        final FilterRequestDto filterRequestDto = new FilterRequestDto();
        filterRequestDto.setSortColumn(Columns.PART_NAME);
        filterRequestDto.setSortDirection("desc");

        final List<PartDto> partDtos = PartDao.readParts(filterRequestDto);

        assertNotNull(partDtos);
        assertEquals(partDtos.size(), 2);
         PartDto partDto = partDtos.get(0);
        assertEquals(partDto.getPartNumber(), NUMBER);
       partDto = partDtos.get(1);
        assertEquals(partDto.getPartNumber(), NUMBER_);
    }


}