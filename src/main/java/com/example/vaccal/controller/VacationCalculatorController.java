package com.example.vaccal.controller;

import com.example.vaccal.entity.VacationRequest;
import com.example.vaccal.service.VacationCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {
    private final VacationCalculationService calculatorService;

    public VacationCalculatorController(VacationCalculationService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public ResponseEntity<Double> calculate(@RequestParam double averageSalary,
                                            @RequestParam int vacationDays,
                                            @RequestParam(required = false) String startDate) {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays);
        if (startDate != null) {
            try {
                request.setStartDate(LocalDate.parse(startDate));
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        double result;
        if (startDate != null) {
            result = this.calculatorService.calculateVacationPayWithDates(request);
        } else {
            result = this.calculatorService.calculateVacationPay(request);
        }

        return ResponseEntity.ok(result);
    }
}
