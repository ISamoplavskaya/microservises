package service;


import entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static EmployeeService INSTANCE;
    private final List<Employee> employees;
    private final SalaryService salaryService = new SalaryService();

    private EmployeeService() {
        employees = new ArrayList<>();
        employees.add(new Employee(811, "Bezkorovnyi V.I.", "asphalt concrete worker", 5, 77.033));
        employees.add(new Employee(817, "Taranets V. A.", "asphalt concrete worker", 4, 67.529));
        employees.add(new Employee(820, "Honcharuk L. I.", "roadmender", 4, 67.529));
    }

    public static synchronized EmployeeService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeService();
        }
        return INSTANCE;
    }

    public List<Employee> getAllEmployee() {
        for (Employee employee : employees) {
            setFullSalaryForTheMonth(employee);
        }
        return employees;
    }

    public Employee getEmployeeById(long id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                setFullSalaryForTheMonth(employee);
                return employee;
            }
        }
        return null;
    }

    private void setFullSalaryForTheMonth(Employee employee) {
        employee.setFullSalary(getFullSalaryForMonth(employee));
    }

    private double getFullSalaryForMonth(Employee employee) {
        return salaryService.getFullSalary(employee);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployee(Employee updatedEmployee) {
        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getId() == updatedEmployee.getId()) {
                employees.set(i, updatedEmployee);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + updatedEmployee.getId() + " not found.");
        }
    }

    public void deleteEmployee(long id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public boolean isExistInList(Employee employee) {
        return getAllEmployee()
                .stream().
                anyMatch(employee1 -> employee1.getPersonnelNumber() == employee.getPersonnelNumber());
    }
}

