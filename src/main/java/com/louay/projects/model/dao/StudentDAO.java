package com.louay.projects.model.dao;

import com.louay.projects.model.entity.Student;

public interface StudentDAO {
    Student findById(String key);
    Student findByEmail(String key);
    Student creat(Student student);
    Student update(Student student);
    Student delete(Student student);

}
