package com.louay.regestration.model1.dao.impl;

import com.louay.regestration.model1.dao.StudentDAO;
import com.louay.regestration.model1.entity.Student;
import com.louay.regestration.model1.util.MyConnectionPool;
import com.mysql.cj.MysqlConnection;

import java.sql.*;

public class StudentDAOImpl implements StudentDAO {
    MyConnectionPool pool;

    public StudentDAOImpl( ) {
        this.pool = new MyConnectionPool();
    }

    @Override
    public Student findById(String key) {
        Student student = null;
        try{
            ResultSet resultSet = pool.prepareStatementQuery("select * from student where idStudent = ? ",key);
            if (resultSet.next())
            student = new Student(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6));
        }catch(Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student findByEmail(String key) {
        Student student = null;
        try{
            ResultSet resultSet = pool.prepareStatementQuery("select * from student where email = ? ", key);
            if (resultSet.next())
                student = new Student(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6));
        }catch(Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student creat(Student student) {
        try{
            String idStudent = student.getId();
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String password = student.getPassword();
            Integer joinYear = student.getJoinYear();
            PreparedStatement creat = pool.getConnection().prepareStatement("insert into student(idStudent,firstName,lastName,email,password,joinYear) value(?,?,?,?,?,?) ");
            creat.setString(1,idStudent);
            creat.setString(2,firstName);
            creat.setString(3,lastName);
            creat.setString(4,email);
            creat.setString(5,password);
            creat.setInt(6,joinYear);
            creat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student update(Student student) {
        try{
            String idStudent = student.getId();
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String password = student.getPassword();
            Integer joinYear = student.getJoinYear();
            PreparedStatement update = pool.getConnection().prepareStatement("update student set firstName = ?,lastName = ?,email = ?,password = ?,joinYear = ? where idStudent = ?");;
            update.setString(1,firstName);
            update.setString(2,lastName);
            update.setString(3,email);
            update.setString(4,password);
            update.setInt(5,joinYear);
            update.setString(6,idStudent);
            update.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student delete(Student student) {
        try{
            PreparedStatement delete = pool.getConnection().prepareStatement("delete from student where idStudent = ?");
            delete.setString(1, student.getId());
            delete.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return student;
    }
}
