package com.louay.regestration.model1.dao;

import com.louay.regestration.model1.entity.Course;

public interface CourseDAO {
    Course findById(String key);
    Course findByName(String key);
    Course creat(Course course);
    Course update(Course course);
    Course delete(Course course);
}
