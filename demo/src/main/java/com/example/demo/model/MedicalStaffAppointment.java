package com.example.demo.model;

import java.time.LocalDateTime;

public class MedicalStaffAppointment {
    private String id;
    private String staffName;
    private String department;
    private LocalDateTime appointmentTime;

    public MedicalStaffAppointment() {}

    public MedicalStaffAppointment(String id, String staffName, String department, LocalDateTime appointmentTime) {
        this.id = id;
        this.staffName = staffName;
        this.department = department;
        this.appointmentTime = appointmentTime;
    }

    public String getId() { return id; }
    public String getStaffName() { return staffName; }
    public String getDepartment() { return department; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }

    public void setId(String id) { this.id = id; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public void setDepartment(String department) { this.department = department; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    @Override
    public String toString() {
        return "MedicalStaffAppointment{" +
                "id='" + id + '\'' +
                ", staffName='" + staffName + '\'' +
                ", department='" + department + '\'' +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
