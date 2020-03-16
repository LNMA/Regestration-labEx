package com.louay.projects.registration.model.dao.impl;

import com.louay.projects.registration.model.dao.StudentDAO;
import com.louay.projects.registration.model.entity.Student;
import com.louay.projects.registration.model.util.pool.ConnectionWrapper;
import com.louay.projects.registration.model.util.pool.DBConnectionConfig;
import com.louay.projects.registration.model.util.pool.MyConnectionPool;

import java.sql.*;

public class StudentDAOImpl implements StudentDAO {
    private MyConnectionPool pool;
    private DBConnectionConfig dbConfig;
    private ConnectionWrapper wrapper;


    public StudentDAOImpl() {
        this.dbConfig = new DBConnectionConfig();
        this.dbConfig.setDriver("jdbc:mysql");
        this.dbConfig.setHost("localhost");
        this.dbConfig.setPort("3306");
        this.dbConfig.setSchema("registration");
        this.dbConfig.setUsername("root");
        this.dbConfig.setPassword("1729384#General");
        this.pool = MyConnectionPool.getMyPooling(this.dbConfig.getUrl(), this.dbConfig.getUsername(), this.dbConfig.getPassword());
    }

    @Override
    public Student findById(String key) {
        Student student = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from student where `idStudent` = ? ", key);
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
            ResultSet resultSet = pool.selectResult("select * from student where `email` = ? ", key);
            student = buildStudent(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public Student buildStudent(ResultSet resultSet) {
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
            this.pool.updateQuery("insert into student(`idStudent`,`firstName`,`lastName`,`email`,`password`,`joinYear`)" +
                    " value(?,?,?,?,?,?) ",idStudent,firstName,lastName,email,password,joinYear);
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
            this.pool.updateQuery("update student set `firstName` = ?,`lastName` = ?,`email` = ?,`password`= ?," +
                    "`joinYear` = ? where `idStudent` = ?",firstName,lastName,email,password,joinYear,idStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public boolean delete(Student student) {
        try {
            this.wrapper = this.pool.getConnection();
            PreparedStatement delete = this.wrapper.getConnection().prepareStatement("delete from student where `idStudent` = ?");
            delete.setString(1, student.getId());
            delete.executeUpdate();
            this.pool.release(this.wrapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
