package br.com.mateusfilpo.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationHeaderFilter extends OncePerRequestFilter {

    private static final String EXPECTED_TOKEN = "vYQIYxOpyfr==";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !"/foo-bar".equals(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.equals(EXPECTED_TOKEN)) {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", System.currentTimeMillis());
            errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            errorDetails.put("error", "Authorization token is missing or invalid");
            errorDetails.put("path", request.getRequestURI());

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(errorDetails));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
