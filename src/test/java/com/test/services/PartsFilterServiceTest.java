package com.test.services;

import com.test.dao.PartDao;
import com.test.dto.FilterRequestDto;
import com.test.dto.PartDto;
import com.test.dto.ResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PartDao.class})
public class PartsFilterServiceTest {
    @Test
    public void filter() throws Exception {
        final PartDto partDto = new PartDto();
        partDto.setPartNumber("number");
        partDto.setPartName("name");
        partDto.setVendor("vendor");
        partDto.setQty(1);
        partDto.setShipped(LocalDate.now());
        partDto.setReceive(LocalDate.now().plusDays(5));
        List<PartDto> parts = Collections.singletonList(partDto);

        PowerMockito.mockStatic(PartDao.class);
        PowerMockito.when(PartDao.readParts(Matchers.anyObject())).thenReturn(parts);

        final ResponseDto response = PartsFilterService.filter(new FilterRequestDto());


        assertNotNull(response);
        PartDto partExpected = response.getParts().get(0);
        PartDto partActual = parts.get(0);

        assertEquals(partExpected.getPartNumber(), partActual.getPartNumber());
        assertEquals(partExpected.getPartName(), partActual.getPartName());
        assertEquals(partExpected.getVendor(), partActual.getVendor());
        assertEquals(partExpected.getQty(), partActual.getQty());
        assertEquals(partExpected.getShipped(), partActual.getShipped());
        assertEquals(partExpected.getReceive(), partActual.getReceive());
    }

}