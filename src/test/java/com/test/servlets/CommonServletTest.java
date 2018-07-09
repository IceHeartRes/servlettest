package com.test.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dto.PartDto;
import com.test.dto.ResponseDto;
import com.test.services.PartsFilterService;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;

public class CommonServletTest {




}
