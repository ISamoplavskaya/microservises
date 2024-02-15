package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthService;

import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private final AuthService authService = new AuthService();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Cookie[] cookies = httpRequest.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    token = cookie.getValue();
                }
            }
        }

        if (token != null && authService.isTokenValid(token)) {
            if (isAdminUser(token)) {
                chain.doFilter(request, response);
            } else {
                if (httpRequest.getRequestURI().contains("/addEmployee") || httpRequest.getRequestURI().contains("/removeEmployee")) {
                    httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    httpResponse.getWriter().write("You haven't permission to perform this operation");
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized access");
        }
    }


    private boolean isAdminUser(String token) {
        return token.contains("admin");
    }
}
