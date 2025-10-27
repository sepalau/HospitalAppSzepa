package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private String id;
    private String departmentId;
    private String patientId;
    private String admissionDate;
    private String status;
    private List<MedicalStaff> medicalStaff;
    public Appointment() {}
    public Appointment(String id, String departmentId, String patientId, String admissionDate, String status,  List<MedicalStaff> medicalStaff) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
        this.medicalStaff = medicalStaff;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getStatus() {
        return status;
    }

    public List<MedicalStaff> getMedicalStaff() {
        return medicalStaff;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMedicalStaff(List<MedicalStaff> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", status='" + status + '\'' +
                ", medicalStaff=" + medicalStaff +
                '}';
    }
}
