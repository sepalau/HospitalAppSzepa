package com.example.demo;

import com.example.demo.model.Hospital;
import com.example.demo.service.HospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.service.PatientService;
import com.example.demo.model.Patient;

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

    @Bean
    CommandLineRunner initPatients(PatientService patientService) {
        return args -> {
            patientService.create(new Patient("P1", "John Doe"));
            patientService.create(new Patient("P2", "Maria Popescu"));
            patientService.create(new Patient("P3", "Alex Schmidt"));
        };
    }

}
