package servlet;

import entity.Employee;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;


@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    ;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ServletUtils.forwardToPage(request, response, "addEmployee.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            long personnelNumber = Long.parseLong(request.getParameter("personnelNumber"));
            String fullName = request.getParameter("fullName");
            String position = request.getParameter("position");
            int category = Integer.parseInt(request.getParameter("category"));
            double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));

            Employee newEmployee = new Employee(personnelNumber, fullName, position, category, hourlyRate);
            if (!employeeService.isExistInList(newEmployee)) {
                employeeService.addEmployee(newEmployee);
                ServletUtils.redirectToPage(response,"/employees");
            } else {
                ServletUtils.handleException(request, response, "addEmployee.jsp", "already_exist", "Employees is already exist!");
            }
    }
}

