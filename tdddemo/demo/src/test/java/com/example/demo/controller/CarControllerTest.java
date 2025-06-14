package com.example.demo.controller;


import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = CarController0.class) // 1. 생성하지 않은 상태에서 테스트 = cannot find...
@WebMvcTest(controllers = CarController.class) // 2. 빈 내용의 컨트롤러 생성후 테스트 -> CarController go
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarService carService;

    @Test
    void contextLoads() {

    }

    // 3. 빈 내용이기때문에 url endpoint가 없어서 오류
    // 4. endpoint가 없어서 실패
    @Test
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(status().isOk());
    }

    // 5. endpoint가 있으니 테스트 통과
    @Test
    public void getCarDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/Scala"))
                .andExpect(status().isOk());
    }

    // 7. new Car() 는 null 테스트실패
    // 8. Service에서 Car를 받아온후
    @Test
    public void getCarDetail2() throws Exception {
        given(carService.getCarDetails(Mockito.anyString())).willReturn(new Car("Scala", "Sadan"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/Scala"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("name").value("Scala"))
                .andExpect(jsonPath("type").value("Sadan"));
    }

    @Test
    public void Car_NotFoud_HttpStatus() throws Exception {
        given(carService.getCarDetails(Mockito.anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/Scala"))
                .andExpect(status().isNotFound());
    }

    // Exception
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public class CarNotFoundException extends RuntimeException {

        public CarNotFoundException() {
        }
    }
}
