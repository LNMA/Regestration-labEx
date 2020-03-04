package com.louay.projects.model;

import com.louay.projects.model.dao.CourseDAO;
import com.louay.projects.model.dao.impl.CourseDAOImpl;

public class Main {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAOImpl();
        System.out.println(courseDAO.findById("1").getCapacity());

    }
}
