package com.louay.regestration.model1.entity;

public class Course {
    String id;
    String name;
    String code;
    Integer capacity;
    String Instructor;

    public Course() {
    }

    public Course(String id, String name, String code, Integer capacity, String instructor) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        Instructor = instructor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", capacity=" + capacity +
                ", Instructor='" + Instructor + '\'' +
                '}';
    }
}
