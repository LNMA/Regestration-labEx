package com.louay.projects.registration.model.dao.impl;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.dao.ScheduleDAO;
import com.louay.projects.registration.model.dao.StudentDAO;
import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.entity.Schedule;
import com.louay.projects.registration.model.entity.Student;
import com.louay.projects.registration.model.util.pool.ConnectionWrapper;
import com.louay.projects.registration.model.util.pool.DBConnectionConfig;
import com.louay.projects.registration.model.util.pool.MyConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    private MyConnectionPool pool;
    private ConnectionWrapper wrapper;
    private DBConnectionConfig dbConfig;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;


    public ScheduleDAOImpl(StudentDAO studentDAO,CourseDAO courseDAO) {
        this.dbConfig = new DBConnectionConfig();
        this.dbConfig.setDriver("jdbc:mysql");
        this.dbConfig.setHost("localhost");
        this.dbConfig.setPort("3306");
        this.dbConfig.setSchema("registration");
        this.dbConfig.setUsername("root");
        this.dbConfig.setPassword("1729384#General");
        this.pool = MyConnectionPool.getMyPooling(this.dbConfig.getUrl(), this.dbConfig.getUsername(), this.dbConfig.getPassword());
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;

    }

    @Override
    public List<Schedule> findByIDCourseAndStudent(Student student, Course course) {
        List<Schedule> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule where `idCourse` = ? and `idStudent` = ?", course.getId(),student.getId());
            list = buildScheduleList(resultSet, new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> findByIDCourse(String key) {
        List<Student> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule inner join student on (schedule.idStudent = student.idStudent) where schedule.idCourse = ?", key);
            list = buildStudentList(resultSet,new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Course> findByIDStudent(String key) {
        List<Course> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule inner join course on (schedule.idCourse = course.idCourse) where schedule.idStudent = ?", key);
            list = buildCourseList(resultSet,new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> buildStudentList(ResultSet resultSet,List<Student> listType){
        try {
            while (resultSet.next()){
                listType.add(this.studentDAO.buildStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listType;
    }

    @Override
    public List<Course> buildCourseList(ResultSet resultSet, List<Course> listType){
        try {
            while (resultSet.next()){
                listType.add(this.courseDAO.buildCourse(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listType;
    }

    @Override
    public List<Schedule> buildScheduleList(ResultSet resultSet,List<Schedule> listType){
        try {
            while (resultSet.next()){
                listType.add(buildSchedule(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listType;
    }

    @Override
    public Schedule buildSchedule(ResultSet resultSet){
        Schedule schedule = null;
        try {
            if (resultSet.next()) {
                schedule = new Schedule();
                schedule.setIdSchedule(resultSet.getInt(1));
                schedule.setIdCourse(resultSet.getString(2));
                schedule.setIdStudent(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public int create(Schedule schedule) {
        int idSchedule=0;
        try {
            this.wrapper = this.pool.getConnection();
            String idStudent = schedule.getIdStudent();
            String idCourse = schedule.getIdCourse();
            PreparedStatement create = this.wrapper.getConnection().prepareStatement("insert into schedule(`idCourse`,`idStudent`) value(?,?);");
            create.setString(1, idCourse);
            create.setString(2, idStudent);
            create.executeUpdate();
            ResultSet resultSet = create.executeQuery("select last_insert_id() from schedule");
            if (resultSet.next()) {
                idSchedule = resultSet.getInt(1);
            }
            this.pool.release(this.wrapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return idSchedule;
    }

    @Override
    public int update(Schedule schedule) {
        int result = 0;
        try {
            int idSchedule = schedule.getIdSchedule();
            String idStudent = schedule.getIdStudent();
            String idCourse = schedule.getIdCourse();
            result = this.pool.updateQuery("update schedule set `idStudent` = ?,`idCourse` = ?" +
                    " where `idSchedule` = ?",idStudent,idCourse,idSchedule);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(Schedule schedule) {
        int result = 0;
        try {
            this.wrapper = this.pool.getConnection();
            PreparedStatement delete = this.wrapper.getConnection().prepareStatement("delete from schedule where `idSchedule` = ?");
            delete.setInt(1, schedule.getIdSchedule());
            result = delete.executeUpdate();
            this.pool.release(this.wrapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
