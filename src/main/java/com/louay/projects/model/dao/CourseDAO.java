package com.louay.projects.model.dao;

import com.louay.projects.model.entity.Course;

public interface CourseDAO {

    Course findById(String key);

    Course findByName(String key);

    Course findByInstructor(String key);

    Course create(Course course);

    Course update(Course course);

    boolean delete(Course course);

}
