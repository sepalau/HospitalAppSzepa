package com.example.demo;

import com.example.demo.model.Appointment;
import com.example.demo.model.Hospital;
import com.example.demo.model.MedicalStaffAppointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.HospitalService;
import com.example.demo.service.MedicalStaffAppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.service.PatientService;
import com.example.demo.model.Patient;

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
    CommandLineRunner initMedicalStaffAppointments(MedicalStaffAppointmentService appointmentService) {
        return args -> {
            appointmentService.create(new MedicalStaffAppointment(
                    "MS1", "Dr. Adams", "John Doe", LocalDateTime.of(2025, 11, 1, 10, 30)));
            appointmentService.create(new MedicalStaffAppointment(
                    "MS2", "Dr. Brown", "Jane Smith", LocalDateTime.of(2025, 11, 2, 15, 0)));
            appointmentService.create(new MedicalStaffAppointment(
                    "MS3", "Dr. Davis", "Mark Taylor", LocalDateTime.of(2025, 11, 3, 9, 45)));
        };
    }

    // Populează programările pentru pacienți la pornirea aplicației
    @Bean
    CommandLineRunner initAppointments(AppointmentService appointmentService) {
        return args -> {
            appointmentService.create(new Appointment(
                    "P1", "Alice Johnson", "Dr. Adams", LocalDateTime.of(2025, 11, 5, 11, 0)));
            appointmentService.create(new Appointment(
                    "P2", "Bob Lee", "Dr. Brown", LocalDateTime.of(2025, 11, 6, 14, 30)));
            appointmentService.create(new Appointment(
                    "P3", "Charlie Kim", "Dr. Davis", LocalDateTime.of(2025, 11, 7, 16, 15)));
        };
    }
    @Bean
    CommandLineRunner initPatients(PatientService patientService) {
        return args -> {
            patientService.create(new Patient("P1", "Alice Brown"));
            patientService.create(new Patient("P2", "Bob Smith"));
            patientService.create(new Patient("P3", "Charlie Wilson"));
        };
    }
}
