package com.louay.regestration.model1.dao.impl;

import com.louay.regestration.model1.entity.Course;
import com.louay.regestration.model1.entity.Student;

public class Main {
    public static void main(String[] args) {
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        System.out.println(courseDAO.findById("1").getCapacity());

    }
}
