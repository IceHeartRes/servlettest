package com.test.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

public class StartServletTest extends CommonServletTest {
    private ArgumentCaptor dispatcherCapture;
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Before
    public void setUp() throws Exception {
        request = Mockito.mock(HttpServletRequest.class);
        dispatcherCapture = ArgumentCaptor.forClass(String.class);
        final RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(request.getRequestDispatcher((String) dispatcherCapture.capture())).thenReturn(requestDispatcher);

        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void doGet() throws Exception {
        new StartServlet().doGet(request, response);

        assertEquals(dispatcherCapture.getValue(), "home.jsp");
    }


}
