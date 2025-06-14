package com.example.demo.service;

//import com.example.demo.exception.CarNotFoundException;
import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
//import com.example.demo.repository.CarViewRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void contextLoads() {

    }

    @Test
    public void getCarDetails() throws Exception {
        given(carRepository.findByName("pulse")).willReturn(Optional.of(new Car("pulse","hatchback")));

        Car car = carService.getCarDetails("pulse");

        assertNotNull(car);
        assertEquals("pulse", car.getName());
        assertEquals("hatchback", car.getType());
    }
}
