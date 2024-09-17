package com.example.vaccal.service;

import com.example.vaccal.entity.VacationRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VacationCalculationServiceTest {

    private final VacationCalculationService service = new VacationCalculationService();

    @Test
    public void testCalculateVacationPay() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(60000);
        request.setVacationDays(10);

        double result = service.calculateVacationPay(request);
        assertEquals(20000.0, result);
    }

    @Test
    public void testCalculateVacationPayWithDates() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(60000);
        request.setVacationDays(10);
        request.setStartDate(LocalDate.of(2023, 1, 1));

        double result = service.calculateVacationPayWithDates(request);
        assertEquals(420000.0, result);
    }

}