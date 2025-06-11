package com.example.testdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.example.testdemo.model.AttendanceStatus;
import com.example.testdemo.model.DailyAttendance;

public class AttendanceServiceTest {
    
    private final AttendanceService attendanceService = new AttendanceService();
    private final Random random = new Random();

    @Test
    void shouldRecognizeFullAttendanceIfOver80Percent() {
        // Entity DTO
        DailyAttendance record = new DailyAttendance(LocalDate.of(2025, 6, 10), 6.5);

        // Service
        AttendanceStatus status = attendanceService.evaluateDailyAttendance(record);

        // validation
        assertEquals(AttendanceStatus.PRESENT, status, "hour: " + record.getHoursAttended() + ", result: " + status);
        System.out.println("Test passed for hours: " + record.getHoursAttended() + ", status: " + status);        
    }

    @Test
    void shouldRecognizeAbsenceIfLessThanOrEqualTo4Hours() {
        DailyAttendance record = new DailyAttendance(LocalDate.of(2025, 6, 10), 4.0);
        AttendanceStatus status = attendanceService.evaluateDailyAttendance(record);
        assertEquals(AttendanceStatus.ABSENT, status, "hour: " + record.getHoursAttended() + ", result: " + status);
        System.out.println("Test passed for hours: " + record.getHoursAttended() + ", status: " + status);
    }

    @Test
    void shouldRecognizeLateIfBetween4And6Point4Hours() {
        DailyAttendance record = new DailyAttendance(LocalDate.of(2025, 6, 10), 5.5);
        AttendanceStatus status = attendanceService.evaluateDailyAttendance(record);
        assertEquals(AttendanceStatus.LATE, status, "hour: " + record.getHoursAttended() + ", result: " + status);
        System.out.println("Test passed for hours: " + record.getHoursAttended() + ", status: " + status);
    }

    @Test
    void randomAttendanceTest() {
        for (int i = 0; i < 10; i++) {
            double hours = Math.round(random.nextDouble() * 8 * 10) / 10.0; // 0.0 ~ 8.0 사이, 소수점 1자리
            DailyAttendance record = new DailyAttendance(null, hours);
            AttendanceStatus status = attendanceService.evaluateDailyAttendance(record);

            System.out.println("Test " + (i + 1) + " => 수업시간: " + hours + "h → 상태: " + status);
        }
    }

    @Test
    void randomDataWithExpectedAssertions() {
        for (int i = 0; i < 20; i++) {
            double hours = Math.round(random.nextDouble() * 8 * 10) / 10.0;
            DailyAttendance record = new DailyAttendance(null, hours);
            AttendanceStatus status = attendanceService.evaluateDailyAttendance(record);

            if (hours <= 4.0) {
                assertEquals(AttendanceStatus.ABSENT, status);
            } else if (hours >= 6.4) {
                assertEquals(AttendanceStatus.PRESENT, status);
            } else {
                assertEquals(AttendanceStatus.LATE, status);
            }

        System.out.println(">>" + hours + "h → " + status);
        }
    }

    @Test
    void testWithRandomDtos() {
        for (int i = 0; i < 20; i++) {
            DailyAttendance dto = generateRandomDTO();
            AttendanceStatus result = attendanceService.evaluateDailyAttendance(dto);

            System.out.printf("date: %s | time: %.1f시간 → %s%n",
                dto.getDate(), dto.getHoursAttended(), result);

            if (dto.getHoursAttended() <= 4.0) {
                assertEquals(AttendanceStatus.ABSENT, result);
            } else if (dto.getHoursAttended() >= 6.4) {
                assertEquals(AttendanceStatus.PRESENT, result);
            } else {
                assertEquals(AttendanceStatus.LATE, result);
            }
        }
    }

    private DailyAttendance generateRandomDTO() {
        LocalDate randomDate = LocalDate.of(2025, 6, random.nextInt(28) + 1);
        double hours = Math.round(random.nextDouble() * 8 * 10) / 10.0;
        return new DailyAttendance(randomDate, hours);
    }

    /*
    @Test
    public void testInsertDummies(){
        int leftLimit = 97; // 'a'
        int rightLimit = 122;   // 'z'
        int targetStringLength = 10;
        Random random = new Random();
        System.out.println("==============================================");
        IntStream.rangeClosed(1,100).forEach(i -> {
            String genString = random.ints(leftLimit,rightLimit+1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                    .toString();
            BlogMember member = BlogMember.builder()
                .mem_nickname("nick"+i)
                .mem_id("id"+i)
                .mem_pwd("1111")
                .mem_blogname("Blog"+i)
                .mem_grade("일반")
                .mem_email(genString+"@trash.com")
                .mem_category("카테고리"+i)
                .mem_profile("나는 "+i+"번째 가입함")
                .build();
            memberRepository.save(member);
        });
    }
     */

}
