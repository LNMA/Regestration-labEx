package com.louay.projects.registration.model;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.dao.impl.CourseDAOImpl;

public class Main {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAOImpl();
        System.out.println(courseDAO.findById("1").getCapacity());
    }

}
