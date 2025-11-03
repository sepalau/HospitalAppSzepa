package com.example.demo;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(HospitalService hospitalService) {
        return args -> {
            hospitalService.create(new Hospital("1", "Central Hospital", "Berlin"));
            hospitalService.create(new Hospital("2", "City Clinic", "Munich"));
            hospitalService.create(new Hospital("3", "St. George Medical Center", "Hamburg"));
        };
    }
}
