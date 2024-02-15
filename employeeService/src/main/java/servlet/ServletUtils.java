package servlet;

import exception.ForwardingException;
import exception.RedirectException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ServletUtils {
    public static void handleException(HttpServletRequest request, HttpServletResponse response, String destination, String nameAttribute, String errorMessage) {
        request.setAttribute(nameAttribute, errorMessage);
        forwardToPage(request, response, destination);
    }

    public static void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String destination) {
        try {
            req.getRequestDispatcher(destination).forward(req, resp);
        } catch (ServletException | IOException e) {
            log.error("Error forwarding request", e);
            throw new ForwardingException("Error forwarding request", e);
        }
    }

    public static void redirectToPage(HttpServletResponse resp, String destination) {
        try {
            resp.sendRedirect(destination);
        } catch (IOException e) {
            log.error("Error redirecting request", e);
            throw new RedirectException("Error redirecting to page.", e);
        }
    }
}
