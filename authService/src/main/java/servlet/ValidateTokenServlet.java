package servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.IOStreamException;
import exception.JsonSerializationException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import service.AuthService;

import java.io.IOException;

@WebServlet("/validate")
public class ValidateTokenServlet extends HttpServlet {
    private final AuthService authService = AuthService.getINSTANCE();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String token = req.getHeader("Authorization");
            boolean isValid = authService.validateToken(token);
            ValidationResult result = new ValidationResult(isValid);
            String json = mapper.writeValueAsString(result);
            resp.setContentType("application/json");
            resp.getWriter().println(json);
            resp.getWriter().flush();
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException("Error processing JSON", e);
        } catch (IOException e) {
            throw new IOStreamException("Error writing JSON to response stream", e);
        }
    }

    @Data
    @AllArgsConstructor
    static class ValidationResult {
        private final boolean valid;
    }
}
