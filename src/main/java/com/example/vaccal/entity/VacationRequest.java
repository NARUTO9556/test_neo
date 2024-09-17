package com.example.vaccal.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacationRequest {
    private double averageSalary;
    private int vacationDays;
    private LocalDate startDate;
}
