package com.example.testdemo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyAttendance {
    private LocalDate date;
    private double hoursAttended;

    // public DailyAttendance(){}

    // public DailyAttendance(LocalDate date, double hoursAttended) {
    //     this.date = date;
    //     this.hoursAttended = hoursAttended;
    // }

    // public LocalDate getDate() {
    //     return date;
    // }

    // public void setDate(LocalDate date) {
    //     this.date = date;
    // }

    // public double getHoursAttended() {
    //     return hoursAttended;
    // }

    // public void setHoursAttended(double hoursAttended) {
    //     this.hoursAttended = hoursAttended;
    // }    
}
