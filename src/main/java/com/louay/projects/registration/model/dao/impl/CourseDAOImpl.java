package com.louay.projects.registration.model.dao.impl;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.util.pool.DBConnectionConfig;
import com.louay.projects.registration.model.util.pool.MyConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseDAOImpl implements CourseDAO {
    private MyConnectionPool pool;
    private DBConnectionConfig dbConfig;


    public CourseDAOImpl() {
        this.dbConfig = new DBConnectionConfig();
        this.dbConfig.setDriver("jdbc:mysql");
        this.dbConfig.setHost("localhost");
        this.dbConfig.setPort("3306");
        this.dbConfig.setSchema("regestration");
        this.dbConfig.setUsername("root");
        this.dbConfig.setPassword("1729384#General");
        this.pool = new MyConnectionPool(this.dbConfig.getUrl(), this.dbConfig.getUsername(), this.dbConfig.getPassword());
    }

    @Override
    public Course findById(String key) {
        Course course = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from course where idCourse = ? ", key);
            course = buildCourse(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course findByName(String key) {
        Course course = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from course where `name` = ? ", key);
            course = buildCourse(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course findByInstructor(String key) {
        Course course = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from course where instructor = ? ", key);
            course = buildCourse(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    private Course buildCourse(ResultSet resultSet) {
        Course course = null;
        try {
            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getString(1));
                course.setName(resultSet.getString(2));
                course.setCode(resultSet.getString(3));
                course.setCapacity(resultSet.getInt(4));
                course.setInstructor(resultSet.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course create(Course course) {
        try {
            String idCourse = course.getId();
            String name = course.getName();
            String code = course.getCode();
            Integer capacity = course.getCapacity();
            String instructor = course.getInstructor();
            PreparedStatement create = pool.getConnection().prepareStatement("insert into course(idCourse,`name`,code,capacity,instructor) value(?,?,?,?,?); ");
            create.setString(1, idCourse);
            create.setString(2, name);
            create.setString(3, code);
            create.setInt(4, capacity);
            create.setString(5, instructor);
            create.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course update(Course course) {
        try {
            String idCourse = course.getId();
            String name = course.getName();
            String code = course.getCode();
            Integer capacity = course.getCapacity();
            String instructor = course.getInstructor();
            PreparedStatement update = pool.getConnection().prepareStatement("update course set `name`= ?,code = ?,capacity = ?,instructor=? where idCourse = ?; ");
            update.setString(1, name);
            update.setString(2, code);
            update.setInt(3, capacity);
            update.setString(4, instructor);
            update.setString(5, idCourse);
            update.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public boolean delete(Course course) {
        try {
            PreparedStatement delete = pool.getConnection().prepareStatement("delete from course where idCourse = ?");
            delete.setString(1, course.getId());
            delete.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
