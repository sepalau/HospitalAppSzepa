package com.example.demo.model;

import java.util.List;

public class Nurse extends MedicalStaff {

    private String qualificationLevel; // "Registered Nurse" or "Practical Nurse"

    public Nurse() { super(); }

    public Nurse(String id, String name, String departmentId, String qualificationLevel) {
        super(id, name, departmentId);
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationLevel() { return qualificationLevel; }

    public void setQualificationLevel(String qualificationLevel) {
        if (!qualificationLevel.equals("Registered Nurse") && !qualificationLevel.equals("Practical Nurse")) {
            throw new IllegalArgumentException("Invalid qualification level");
        }
        this.qualificationLevel = qualificationLevel;
    }
}
