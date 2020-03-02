package com.louay.regestration.model1.dao;

import com.louay.regestration.model1.entity.Student;

public interface StudentDAO {
    Student findById(String key);
    Student findByEmail(String key);
    Student creat(Student student);
    Student update(Student student);
    Student delete(Student student);

}
