package com.example.vaccal.service;

import com.example.vaccal.entity.VacationRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationCalculationService {

    public double calculateVacationPay(VacationRequest request) {
        double dailySalary = request.getAverageSalary() / 30.0;
        return dailySalary * (double)request.getVacationDays();
    }

    public double calculateVacationPayWithDates(VacationRequest request) {
        int validVacationDays = this.countValidVacationDays(request.getStartDate(), request.getVacationDays());
        double dailySalary = request.getAverageSalary();
        return dailySalary * (double)validVacationDays;
    }

    private int countValidVacationDays(LocalDate startDate, int vacationDays) {
        int count = 0;
        Set<LocalDate> holidays = this.getHolidays(startDate.getYear());

        for(int i = 0; i < vacationDays; ++i) {
            LocalDate date = startDate.plusDays((long)i);
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY && !holidays.contains(date)) {
                ++count;
            }
        }

        return count;
    }

    private Set<LocalDate> getHolidays(int year) {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(LocalDate.of(year, 1, 1));
        holidays.add(LocalDate.of(year, 5, 1));
        holidays.add(LocalDate.of(year, 12, 25));
        return holidays;
    }
}
