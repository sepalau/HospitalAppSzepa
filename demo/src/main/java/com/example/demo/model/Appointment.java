package com.example.demo.model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private String patientName;
    private String doctorName;
    private LocalDateTime dateTime;

    public Appointment() {}

    public Appointment(String id, String patientName, String doctorName, LocalDateTime dateTime) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
    }

    public String getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public LocalDateTime getDateTime() { return dateTime; }

    public void setId(String id) { this.id = id; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
