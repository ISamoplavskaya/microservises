package entity;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Employee {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private long id;
    private long personnelNumber;
    private String fullName;
    private String position;
    private int category;
    private double hourlyRate;
    private int hoursWorked = 0;
    private int nightHours = 0;
    private int weekendHours = 0;
    private int harmfulness = 0;
    private int partTime = 0;
    private int premium = 0;
    private double fullSalary;


    public Employee(long id, long personnelNumber, String fullName, String position, int category, double hourlyRate) {
        this.id = id;
        this.personnelNumber = personnelNumber;
        this.fullName = fullName;
        this.position = position;
        this.category = category;
        this.hourlyRate = hourlyRate;
    }

    public Employee(long personnelNumber, String fullName, String position, int category, double hourlyRate) {
        this.id = idCounter.incrementAndGet();
        this.personnelNumber = personnelNumber;
        this.fullName = fullName;
        this.position = position;
        this.category = category;
        this.hourlyRate = hourlyRate;
    }

}

