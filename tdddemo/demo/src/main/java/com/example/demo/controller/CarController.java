package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

//    @GetMapping("/test")
//    public String index(){
//        return "index";
//    }


    // 6. model(entity) Car Entity 생성: 생성전에는 테스트 실패, 생성후 테스트 성공
    @GetMapping("/{name}")
    public ResponseEntity<Car> getCarDetails(@PathVariable String name) throws Exception {
//        Car car = new Car();    // 7. null 객체라서 실패
        Car car = carService.getCarDetails(name);
//        return new ResponseEntity<>(HttpStatus.OK);   // 6.
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}
