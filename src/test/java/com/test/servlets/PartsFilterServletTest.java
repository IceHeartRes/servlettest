package com.test.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dto.PartDto;
import com.test.dto.ResponseDto;
import com.test.services.PartsFilterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PartsFilterService.class})
public class PartsFilterServletTest extends CommonServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;
    private StringWriter stringWriter;
    private ObjectMapper objectMapper;
    private List<PartDto> parts;
    private ArgumentCaptor statusCapture;


    @Before
    public void setUp() throws Exception {
        final PartDto partDto = new PartDto();
        partDto.setPartNumber("number");
        partDto.setPartName("name");
        partDto.setVendor("vendor");
        partDto.setQty(1);
        partDto.setShipped(LocalDate.now());
        partDto.setReceive(LocalDate.now().plusDays(5));
        parts = Collections.singletonList(partDto);

        PowerMockito.mockStatic(PartsFilterService.class);
        PowerMockito.when(PartsFilterService.filter(Matchers.anyObject())).thenReturn(new ResponseDto(parts));

        request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getInputStream()).thenReturn(new MockInputStream());
        Mockito.when(request.getContentLength()).thenReturn(2);

        response = Mockito.mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        statusCapture = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(response).setStatus((Integer) statusCapture.capture());

        objectMapper = new ObjectMapper();
    }

    @Test
    public void doPost() throws ServletException, IOException {

        new PartsFilterServlet().doPost(request, response);

        writer.flush();
        final ResponseDto responseDto = objectMapper.readValue(stringWriter.toString(), ResponseDto.class);
        PartDto partExpected = responseDto.getParts().get(0);
        PartDto partActual = parts.get(0);

        assertEquals(partExpected.getPartNumber(), partActual.getPartNumber());
        assertEquals(partExpected.getPartName(), partActual.getPartName());
        assertEquals(partExpected.getVendor(), partActual.getVendor());
        assertEquals(partExpected.getQty(), partActual.getQty());
        assertEquals(partExpected.getShipped(), partActual.getShipped());
        assertEquals(partExpected.getReceive(), partActual.getReceive());
    }

    @Test
    public void doPostWithError() throws Exception {
        new PartsFilterServlet().doPost(null, response);

        assertEquals(statusCapture.getValue(), 500);
    }

    private class MockInputStream extends ServletInputStream {

        @Override
        public int read() throws IOException {
            return 3;
        }

        @Override
        public int read(byte[] bytes) throws IOException {
            bytes[0] = '{';
            bytes[1] = '}';
            return 2;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}