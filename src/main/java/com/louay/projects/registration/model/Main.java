package com.louay.projects.registration.model;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.dao.StudentDAO;
import com.louay.projects.registration.model.dao.impl.CourseDAOImpl;
import com.louay.projects.registration.model.dao.impl.StudentDAOImpl;
import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.entity.Student;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = new Student();
        student.setId("1");
        student.setFirstName("Amr");
        student.setLastName("Louay");
        student.setEmail("louay@project");
        student.setPassword("321");
        student.setJoinYear(2000);
        studentDAO.update(student);
    }

}
