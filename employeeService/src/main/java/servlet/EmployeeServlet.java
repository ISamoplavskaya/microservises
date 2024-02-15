package servlet;

import entity.Employee;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;

import java.util.List;


@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = employeeService.getAllEmployee();
        request.setAttribute("listEmployees", employees);
        ServletUtils.forwardToPage(request, response, "/employees.jsp");
    }

}

