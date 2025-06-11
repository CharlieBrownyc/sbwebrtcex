package com.example.testdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testdemo.model.AttendanceStatus;
import com.example.testdemo.model.DailyAttendance;
import com.example.testdemo.service.AttendanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/evaluate")
    public AttendanceStatus evaluate(@RequestBody DailyAttendance attendance) {
        //TODO: process POST request
        
        return attendanceService.evaluateDailyAttendance(attendance);
    }

    /*
    POST /attendance/evaluate
    Content-Type: application/json

    {
    "date": "2025-06-10",
    "hoursAttended": 6.5
    }
     */
    
}
