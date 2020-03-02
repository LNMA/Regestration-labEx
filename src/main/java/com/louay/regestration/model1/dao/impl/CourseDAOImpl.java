package com.louay.regestration.model1.dao.impl;

import com.louay.regestration.model1.dao.CourseDAO;
import com.louay.regestration.model1.entity.Course;
import com.louay.regestration.model1.entity.Student;
import com.louay.regestration.model1.util.MyConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseDAOImpl implements CourseDAO {
    MyConnectionPool pool;

    public CourseDAOImpl() {
        this.pool = new MyConnectionPool();
    }

    @Override
    public Course findById(String key) {
        Course course =null ;
        try{
            ResultSet resultSet = pool.prepareStatementQuery("select * from course where idCourse = ? ",key);
            if (resultSet.next())
                course = new Course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5));
        }catch(Exception e){
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course findByName(String key) {
        Course course =null ;
        try{
            ResultSet resultSet = pool.prepareStatementQuery("select * from course where name = ? ",key);
            if (resultSet.next())
                course = new Course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5));
        }catch(Exception e){
            e.printStackTrace();
        }
        return course;    }

    @Override
    public Course creat(Course course) {
        try{
            String idCourse = course.getId();
            String name = course.getName();
            String code = course.getCode();
            Integer capacity = course.getCapacity();
            String instructor = course.getInstructor();
            PreparedStatement creat = pool.getConnection().prepareStatement("insert into course(idCourse,`name`,code,capacity,instructor) value(?,?,?,?,?); ");
            creat.setString(1,idCourse);
            creat.setString(2,name);
            creat.setString(3,code);
            creat.setInt(4,capacity);
            creat.setString(5,instructor);
            creat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course update(Course course) {
        try{
            String idCourse = course.getId();
            String name = course.getName();
            String code = course.getCode();
            Integer capacity = course.getCapacity();
            String instructor = course.getInstructor();
            PreparedStatement creat = pool.getConnection().prepareStatement("update course set `name`= ?,code = ?,capacity = ?,instructor=? where idCourse = ?; ");
            creat.setString(1,name);
            creat.setString(2,code);
            creat.setInt(3,capacity);
            creat.setString(4,instructor);
            creat.setString(5,idCourse);
            creat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course delete(Course course) {
        try{
            PreparedStatement delete = pool.getConnection().prepareStatement("delete from course where idCourse = ?");
            delete.setString(1, course.getId());
            delete.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return course;
    }
}
