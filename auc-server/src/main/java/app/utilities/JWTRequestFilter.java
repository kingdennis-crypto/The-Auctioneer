package app.utilities;

import app.APIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    APIConfig apiConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (HttpMethod.OPTIONS.matches(request.getMethod()) ||
                Arrays.stream(APIConfig.SECURED_PATHS).noneMatch(servletPath::startsWith)) {

            filterChain.doFilter(request, response);
            return;
        }

        String encryptedToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (encryptedToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token provided. You need to login first.");
            return;
        }

        encryptedToken = encryptedToken.replace("Bearer ", "");
        JWToken jwToken;

        try {
            jwToken = JWToken.decode(encryptedToken, this.apiConfig.getPassphrase());
            System.out.println("NEE 2");
        } catch (RuntimeException e) {
            System.out.println("WEL");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, String.format("%s You need to login first.", e.getMessage()));
            return;
        }

        // Pass-on the token info as an attribute for the request
        request.setAttribute(JWToken.JWT_ATTRIBUTE_NAME, jwToken);
        filterChain.doFilter(request, response);
    }
}
