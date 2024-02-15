package servlet;

import entity.Employee;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        for (int i = 0; ; i++) {
            String employeeId = request.getParameter("employeeId" + i);
            if (employeeId == null) {
                break;
            }
            String personnelNumber = request.getParameter("personnelNumber" + i);
            String fullName = request.getParameter("fullName" + i);
            String position = request.getParameter("position" + i);
            String category = request.getParameter("category" + i);
            String hourlyRate = request.getParameter("hourlyRate" + i);
            String hoursWorked = request.getParameter("hoursWorked" + i);
            String nightHours = request.getParameter("nightHours" + i);
            String weekendHours = request.getParameter("weekendHours" + i);
            String harmfulness = request.getParameter("harmfulness" + i);
            String partTime = request.getParameter("partTime" + i);
            String premium = request.getParameter("premium" + i);


            Employee updatedEmployee = new Employee(Long.parseLong(employeeId), Long.parseLong(personnelNumber),
                    fullName, position, Integer.parseInt(category), Double.parseDouble(hourlyRate));
            updatedEmployee.setHoursWorked(Integer.parseInt(hoursWorked));
            updatedEmployee.setNightHours(Integer.parseInt(nightHours));
            updatedEmployee.setWeekendHours(Integer.parseInt(weekendHours));
            updatedEmployee.setHarmfulness(Integer.parseInt(harmfulness));
            updatedEmployee.setPartTime(Integer.parseInt(partTime));
            updatedEmployee.setPremium(Integer.parseInt(premium));

            employeeService.updateEmployee(updatedEmployee);
        }
        ServletUtils.redirectToPage(response, "/employees");
    }
}
