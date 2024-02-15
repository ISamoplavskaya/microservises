package servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Employee;
import exception.IOStreamException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import service.SalaryService;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/salary")
public class SalaryServlet extends HttpServlet {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final SalaryService salaryService = new SalaryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            String jsonEmployee = jsonStringBuilder.toString();

            Employee employee = mapper.readValue(jsonEmployee, Employee.class);
            double fullSalary = salaryService.getFullSalary(employee);
            Salary salary = new Salary(fullSalary);
            String jsonString = mapper.writeValueAsString(salary);
            response.setContentType("application/json");
            response.getWriter().println(jsonString);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new IOStreamException("Error writing JSON to response stream", e);
        }
    }

    @Data
    @AllArgsConstructor
    static class Salary {
        private double fullSalary;
    }
}
