package com.example.demo.model;
import java.util.ArrayList;
import java.util.List;
public class Department {

    private String id;
    private String name;
    private String hospitalId;
    private List<MedicalStaff> medicalStaff;
    private List <Appointment> appointments;

    public Department() {
    }

    public Department(String id, String name, String hospitalId, List<MedicalStaff> medicalStaff, List <Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
        this.medicalStaff = medicalStaff;
        this.appointments = appointments;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHospitalId() {
        return hospitalId;
    }
    public List<MedicalStaff> getMedicalStaff() {
        return medicalStaff;
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setMedicalStaff(List<MedicalStaff> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}

