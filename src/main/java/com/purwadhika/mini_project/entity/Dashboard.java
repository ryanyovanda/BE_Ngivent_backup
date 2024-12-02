package com.purwadhika.mini_project.entity;

public class Dashboard {
    private int id;
    private String name;
    private int marks;


    public Dashboard(int id, String name, int marks) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "id=" + id +
                ", name=" + name +
                ", marks=" + marks +
                '}';
    }
}