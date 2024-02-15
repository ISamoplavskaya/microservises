package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class Employee {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private long id;
    private long personnelNumber;
    private String fullName;
    private String position;
    private int category;
    private double hourlyRate;
    private int hoursWorked=0;
    private int nightHours=0;
    private int weekendHours=0;
    private int harmfulness=0;
    private int partTime=0;
    private int premium=0;
    private double fullSalary=0;


}

