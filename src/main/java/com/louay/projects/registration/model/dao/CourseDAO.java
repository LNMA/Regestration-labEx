package com.louay.projects.registration.model.dao;

import com.louay.projects.registration.model.entity.Course;

public interface CourseDAO {

    Course findById(String key);

    Course findByName(String key);

    Course findByInstructor(String key);

    Course create(Course course);

    Course update(Course course);

    boolean delete(Course course);

}
