package com.ironhack.model;

import java.util.UUID;

public class Course {
    private final String id;
    private String name;
    private Double price;
    private Double moneyEarned;
    private Teacher teacher;

    public Course(String name, Double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(Double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
