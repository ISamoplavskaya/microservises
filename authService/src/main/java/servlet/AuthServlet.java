package servlet;

import exception.AuthenticationException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthService;
import lombok.extern.slf4j.Slf4j;


@WebServlet("/auth")
@Slf4j
public class AuthServlet extends HttpServlet {

    private final AuthService authService = AuthService.getINSTANCE();
    private final String EMPLOYEE_URL = "http://localhost:2223/employees";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || password == null) {
                throw new IllegalArgumentException("Username or password is null");
            }

            String token = authService.authenticate(username, password);
            if (token != null) {
                Cookie tokenCookie = new Cookie("authToken", token);
                tokenCookie.setPath("/");
                tokenCookie.setMaxAge(3600);
                response.addCookie(tokenCookie);
                response.sendRedirect(EMPLOYEE_URL);
            } else {
                throw new AuthenticationException("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            ServletUtils.handleException(request, response, "/login.jsp", e.getMessage());
        } catch (Exception e) {
            log.error("An unexpected error occurred", e);
            ServletUtils.handleException(request, response, "/error.jsp", "An unexpected error occurred");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.forwardToPage(req, resp, "/login.jsp");
    }

}
