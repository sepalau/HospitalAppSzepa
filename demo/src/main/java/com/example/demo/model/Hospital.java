package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private String id;
    private String name;
    private String city;
    private List<Department> departments = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    public Hospital() {}

    public Hospital(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public List<Department> getDepartments() { return departments; }
    public void setDepartments(List<Department> departments) { this.departments = departments; }

    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}
