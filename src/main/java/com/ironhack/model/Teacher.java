package com.ironhack.model;

import java.util.UUID;

public class Teacher {

    private final String id;
    private String name;
    private double salary;


    public Teacher(String name, double salary) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
