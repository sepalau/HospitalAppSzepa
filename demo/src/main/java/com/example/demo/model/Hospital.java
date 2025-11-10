package com.example.demo.model;
import com.example.demo.enums.RoomStatus;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private String Id;
    private String Name;
    private String City;
    private List<String> Departments;
    private List<RoomStatus> rooms;

    public Hospital() {}

    // Constructor folosit în DemoApplication
    public Hospital(String Id, String Name, String City) {
        this.Id = Id;
        this.Name = Name;
        this.City = City;
        this.Departments = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public Hospital(String Id, String Name, String City, List<String> Departments, List<String> Rooms) {
        this.Id = Id;
        this.Name = Name;
        this.City = City;
        this.Departments = Departments;
        this.rooms = rooms;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public List<String> getDepartments() {
        return Departments;
    }

    public void setDepartments(List<String> departments) {
        Departments = departments;
    }

    public List<RoomStatus> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomStatus> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", City='" + City + '\'' +
                ", Departments=" + Departments +
                ", Rooms=" + rooms +
                '}';
    }
}
