package com.example.testdemo.service;

import org.springframework.stereotype.Service;

import com.example.testdemo.model.AttendanceStatus;
import com.example.testdemo.model.DailyAttendance;

@Service
public class AttendanceService {
    
    private static final double FULL_DAY_HOURS = 8.0;
    private static final double REQUIRED_FOR_PRESENT = FULL_DAY_HOURS * 0.8;
    private static final double MAX_ABSENCE_THRESHOLD = 4.0;
    
    public AttendanceStatus evaluateDailyAttendance(DailyAttendance record) {
        double hours = record.getHoursAttended();

        if (hours <= MAX_ABSENCE_THRESHOLD) {
            return AttendanceStatus.ABSENT;
        } else if (hours >= REQUIRED_FOR_PRESENT) {
            return AttendanceStatus.PRESENT;
        } else {
            return AttendanceStatus.LATE;
        }
    }
}
