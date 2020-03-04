package com.louay.projects.model.dao.impl;

import com.louay.projects.model.dao.StudentDAO;
import com.louay.projects.model.entity.Student;
import com.louay.projects.model.util.pool.DBConnectionConfig;
import com.louay.projects.model.util.pool.MyConnectionPool;

import java.sql.*;

public class StudentDAOImpl implements StudentDAO {
    private MyConnectionPool pool ;
    private DBConnectionConfig dbConfig ;


    public StudentDAOImpl() {
        this.dbConfig = new DBConnectionConfig();
        this.dbConfig.setDriver("jdbc:mysql");
        this.dbConfig.setHost("localhost");
        this.dbConfig.setPort("3306");
        this.dbConfig.setSchema("regestration");
        this.dbConfig.setUsername("root");
        this.dbConfig.setPassword("1729384#General");
        this.pool = new MyConnectionPool(this.dbConfig.getUrl(), dbConfig.getUsername(), this.dbConfig.getPassword());
    }

    @Override
    public Student findById(String key) {
        Student student = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from student where idStudent = ? ", key);
            student = buildStudent(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public Student findByEmail(String key) {
        Student student = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from student where email = ? ", key);
            student = buildStudent(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    private Student buildStudent(ResultSet resultSet) {
        Student student = null;
        try {
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getString(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
                student.setEmail(resultSet.getString(4));
                student.setPassword(resultSet.getString(5));
                student.setJoinYear(resultSet.getInt(6));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public Student create(Student student) {
        try {
            String idStudent = student.getId();
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String password = student.getPassword();
            Integer joinYear = student.getJoinYear();
            PreparedStatement create = pool.getConnection().prepareStatement("insert into student(idStudent,firstName,lastName,email,password,joinYear) value(?,?,?,?,?,?) ");
            create.setString(1, idStudent);
            create.setString(2, firstName);
            create.setString(3, lastName);
            create.setString(4, email);
            create.setString(5, password);
            create.setInt(6, joinYear);
            create.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public Student update(Student student) {
        try {
            String idStudent = student.getId();
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String password = student.getPassword();
            Integer joinYear = student.getJoinYear();
            PreparedStatement update = pool.getConnection().prepareStatement("update student set firstName = ?,lastName = ?,email = ?,password = ?,joinYear = ? where idStudent = ?");
            update.setString(1, firstName);
            update.setString(2, lastName);
            update.setString(3, email);
            update.setString(4, password);
            update.setInt(5, joinYear);
            update.setString(6, idStudent);
            update.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public boolean delete(Student student) {
        try {
            PreparedStatement delete = pool.getConnection().prepareStatement("delete from student where idStudent = ?");
            delete.setString(1, student.getId());
            delete.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
