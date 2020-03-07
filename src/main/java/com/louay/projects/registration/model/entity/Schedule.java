package com.louay.projects.registration.model.entity;

public class Schedule {
    private Integer idSchedule;
    private String idCourse;
    private String idStudent;

    public Schedule() {
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
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
