package service;

import entity.Employee;

public class SalaryService {
    public double getFullSalary(Employee employee) {
       double monthlyTariffSalary=employee.getHoursWorked()*employee.getHourlyRate();
       double partTimeSalary=employee.getPartTime()*employee.getHoursWorked()*employee.getHourlyRate()/100;
       double harmfulnessSalary=employee.getHarmfulness()*(monthlyTariffSalary+partTimeSalary)/100;
       double salaryForNightHours=employee.getNightHours()*employee.getHourlyRate();
       double salaryForWeekendHours=employee.getWeekendHours()*employee.getHourlyRate();
       double premium=employee.getPremium()*(monthlyTariffSalary+partTimeSalary)/100;
       return monthlyTariffSalary+partTimeSalary+harmfulnessSalary+salaryForNightHours+salaryForWeekendHours+premium;
    }
}
