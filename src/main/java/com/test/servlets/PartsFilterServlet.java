package com.test.servlets;

import com.test.dto.FilterRequestDto;
import com.test.dto.ResponseDto;
import com.test.services.PartsFilterService;
import com.test.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * сервлет фильтрации
 */

@Slf4j
@WebServlet("/filter")
public class PartsFilterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processPost(req, resp);
        } catch (Throwable th) {
            resp.setStatus(500);
        }
    }

    private void processPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestContent = getContent(req);

        final FilterRequestDto filterRequestDto = JSONUtil.fromJSON(requestContent, FilterRequestDto.class);
        final ResponseDto response = PartsFilterService.filter(filterRequestDto);

        final PrintWriter writer = resp.getWriter();
        writer.write(JSONUtil.toJSON(response));
    }

    private String getContent(HttpServletRequest req) throws IOException {
        ServletInputStream reader = req.getInputStream();
        byte[] content = new byte[req.getContentLength()];
        reader.read(content);
        return new String(content);
    }
}
