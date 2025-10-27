package com.example.demo.model;
import java.util.List;
public class Hospital {
    private String Id;
    private String Name;
    private String City;
    private List Departments;
    private List Rooms;

    public Hospital(){}
    public Hospital(String Id, String Name, String City, List Departments, List Rooms) {
        this.Id = Id;
        this.Name = Name;
        this.City = City;
        this.Departments = Departments;
        this.Rooms = Rooms;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public List getDepartments() {
        return Departments;
    }

    public List getRooms() {
        return Rooms;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setDepartments(List departments) {
        Departments = departments;
    }

    public void setRooms(List rooms) {
        Rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", City='" + City + '\'' +
                ", Departments=" + Departments +
                ", Rooms=" + Rooms +
                '}';
    }
}
