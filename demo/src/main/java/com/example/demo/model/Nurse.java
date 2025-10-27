package com.example.demo.model;

import java.util.List;

public class Nurse extends MedicalStaff {

    private String qualificationLevel;

    public Nurse() {
        super();
    }

    public Nurse(String id, String name, String departmentId,
                 List<Appointment> appointments, String qualificationLevel) {
        super(id, name, departmentId, appointments);
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        if (!qualificationLevel.equals("Registered Nurse") &&
                !qualificationLevel.equals("Practical Nurse")) {
            throw new IllegalArgumentException("Qualification must be 'Registered Nurse' or 'Practical Nurse'");
        }
        this.qualificationLevel = qualificationLevel;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", departmentId='" + getDepartmentId() + '\'' +
                ", qualificationLevel='" + qualificationLevel + '\'' +
                ", appointments=" + (getAppointments() != null ? getAppointments().size() : 0) +
                '}';
    }
}