package com.louay.projects.registration.model.entity;

import java.sql.Date;

public class Course {
    private String id;
    private String name;
    private String code;
    private Integer capacity;
    private String Instructor;
    private java.sql.Date startingDate;
    private int durationDay;
    private int hour;


    public Course() {
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

    public java.sql.Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(java.sql.Date startingDate) {
        this.startingDate = startingDate;
    }

    public int getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(int durationDay) {
        this.durationDay = durationDay;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", capacity=" + capacity +
                ", Instructor='" + Instructor + '\'' +
                ", startingDate=" + startingDate +
                ", durationDay=" + durationDay +
                ", hour=" + hour +
                '}';
    }
}
