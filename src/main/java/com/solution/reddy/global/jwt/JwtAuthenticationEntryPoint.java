package com.solution.reddy.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.DefaultMessage;
import com.solution.reddy.global.message.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        ResponseMessage errorMessage = DefaultMessage.UNAUTHORIZED;
        ReddyApiResponse apiResponse = ReddyApiResponse.createResponse(null, errorMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(apiResponse);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorMessage.getStatus().value());
        response.getWriter().write(json);
    }
}
