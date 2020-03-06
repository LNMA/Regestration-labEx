package com.louay.projects.registration.model.entity;

public class Schedule {
    private int idSchedule;
    private String idCourse;
    private String idStudent;

    public Schedule() {
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule='" + idSchedule + '\'' +
                ", idCourse='" + idCourse + '\'' +
                ", idStudent='" + idStudent + '\'' +
                '}';
    }
}
