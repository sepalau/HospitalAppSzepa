package com.example.demo;

import com.example.demo.model.Hospital;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.HospitalService;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Populează spitalele la pornirea aplicației
    @Bean
    CommandLineRunner initHospitals(HospitalService hospitalService) {
        return args -> {
            hospitalService.create(new Hospital("1", "Central Hospital", "Berlin"));
            hospitalService.create(new Hospital("2", "City Clinic", "Munich"));
            hospitalService.create(new Hospital("3", "St. George Medical Center", "Hamburg"));
        };
    }

    // Populează programările medicale la pornirea aplicației
    @Bean
    CommandLineRunner initAppointments(MedicalStaffAppointmentService appointmentService) {
        return args -> {
            appointmentService.create(new MedicalStaffAppointment(
                    "A1", "John Doe", "Dr. Adams", LocalDateTime.of(2025, 11, 1, 10, 30)));
            appointmentService.create(new MedicalStaffAppointment(
                    "A2", "Jane Smith", "Dr. Brown", LocalDateTime.of(2025, 11, 2, 15, 0)));
            appointmentService.create(new MedicalStaffAppointment(
                    "A3", "Mark Taylor", "Dr. Davis", LocalDateTime.of(2025, 11, 3, 9, 45)));
        };
    }
}
