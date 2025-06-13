package com.example.testdemo.controller;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testdemo.model.DailyAttendance;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc(addFilters = false) // Disable security filters for testing
@Import(com.example.testdemo.service.AttendanceService.class) // Import the service for testing
@WebMvcTest(AttendanceController.class)
public class AttendanceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // JSON Serialization/Deserialization


    private final Random random = new Random();

    private DailyAttendance generateRandomDTO() {
        LocalDate randomDate = LocalDate.of(2025, 6, random.nextInt(28) + 1);
        double hours = Math.round(random.nextDouble() * 8 * 10) / 10.0;
        return new DailyAttendance(randomDate, hours);
    }

    @Test
    void postAttendanceMultipleTimes() throws Exception {
        for (int i = 1; i <= 10; i++) {
            DailyAttendance dto = generateRandomDTO();
            String json = objectMapper.writeValueAsString(dto);

            System.out.printf("요청 %02d → %s (%.1f시간)%n",
                i, dto.getDate(), dto.getHoursAttended());

            mockMvc.perform(
                    post("/attendance/evaluate")
                    .contentType("application/json")
                    .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(
                    anyOf(is("\"PRESENT\""), is("\"LATE\""), is("\"ABSENT\"")
            )));
            System.out.println("==========================");
        }
    }

}
