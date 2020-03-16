package com.louay.projects.registration.model.dao.impl;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.util.pool.DBConnectionConfig;
import com.louay.projects.registration.model.util.pool.MyConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAOImpl implements CourseDAO {
    private MyConnectionPool pool;
    private DBConnectionConfig dbConfig;


    public CourseDAOImpl() {
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
    public Course findById(String key) {
        Course course = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from course where `idCourse` = ? ", key);
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
            ResultSet resultSet = pool.selectResult("select * from course where `instructor` = ? ", key);
            course = buildCourse(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course buildCourse(ResultSet resultSet) {
        Course course = null;
        try {
            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getString(1));
                course.setName(resultSet.getString(2));
                course.setCode(resultSet.getString(3));
                course.setCapacity(resultSet.getInt(4));
                course.setInstructor(resultSet.getString(5));
                course.setStartingDate(resultSet.getDate(6));
                course.setDurationDay(resultSet.getInt(7));
                course.setHour(resultSet.getInt(8));

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
            this.pool.updateQuery("insert into course(`idCourse`,`name`,`code`,`capacity`,`instructor`,`startingDate`,`durationDay`,`hour`)" +
                    " value(?,?,?,?,?,?,?,?); ",idCourse,name,code,capacity,instructor);
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
            java.sql.Date startingDate = course.getStartingDate();
            Integer durationDay = course.getDurationDay();
            Integer hour = course.getHour();
            this.pool.updateQuery("update course set `name`= ?,`code` = ?,`capacity` = ?" +
                    ",`instructor` = ?,`startingDate` = ?,`durationDay` = ?,`hour` = ?" +
                    " where `idCourse` = ?; ",name,code,capacity,instructor,startingDate,durationDay,hour,idCourse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public boolean delete(Course course) {
        PreparedStatement delete = null;
        try {
            delete = pool.getConnection().prepareStatement("delete from course where `idCourse` = ?");
            delete.setString(1, course.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
