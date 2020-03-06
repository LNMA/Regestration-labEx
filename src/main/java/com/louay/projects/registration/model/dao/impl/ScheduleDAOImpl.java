package com.louay.projects.registration.model.dao.impl;

import com.louay.projects.registration.model.dao.ScheduleDAO;
import com.louay.projects.registration.model.entity.Schedule;
import com.louay.projects.registration.model.entity.Student;
import com.louay.projects.registration.model.util.pool.DBConnectionConfig;
import com.louay.projects.registration.model.util.pool.MyConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    private MyConnectionPool pool;
    private DBConnectionConfig dbConfig;


    public ScheduleDAOImpl() {
        this.dbConfig = new DBConnectionConfig();
        this.dbConfig.setDriver("jdbc:mysql");
        this.dbConfig.setHost("localhost");
        this.dbConfig.setPort("3306");
        this.dbConfig.setSchema("regestration");
        this.dbConfig.setUsername("root");
        this.dbConfig.setPassword("1729384#General");
        this.pool = MyConnectionPool.getMyPooling(this.dbConfig.getUrl(), this.dbConfig.getUsername(), this.dbConfig.getPassword());
    }

    @Override
    public List<Schedule> findByIDSchedule(String key) {
        List<Schedule> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule where `idSchedule` = ? ", key);
            list = buildScheduleList(resultSet, new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Schedule> findByIDCourse(String key) {
        List<Schedule> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule where `idCourse` = ? ", key);
            list = buildScheduleList(resultSet,new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Schedule> findByIDStudent(String key) {
        List<Schedule> list = null;
        try {
            ResultSet resultSet = pool.selectResult("select * from schedule where `idCourse` = ? ", key);
            list = buildScheduleList(resultSet,new ArrayList<>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private List<Schedule> buildScheduleList(ResultSet resultSet,List<Schedule> listType){
        List <Schedule> list = listType;
        try {
            while (resultSet.next()){
                list.add(new Schedule(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int create(Schedule schedule) {
        int result = 0;
        try {
            String idStudent = schedule.getIdStudent();
            String idCourse = schedule.getIdCourse();
            result = this.pool.updateQuery("insert into schedule(`idCourse`,`idStudent`) value(?,?) ",idCourse,idStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
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
            PreparedStatement delete = pool.getConnection().prepareStatement("delete from schedule where `idSchedule` = ?");
            delete.setInt(1, schedule.getIdSchedule());
            result = delete.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
