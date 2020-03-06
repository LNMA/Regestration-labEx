package com.louay.projects.registration.model.dao;

import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.entity.Schedule;
import com.louay.projects.registration.model.entity.Student;

import java.sql.ResultSet;
import java.util.List;

public interface ScheduleDAO {

    List<Schedule> findByIDSchedule(String key);

    List<Student> findByIDCourse(String key);

    List<Course> findByIDStudent(String key);

    List<Student> buildStudentList(ResultSet resultSet, List<Student> listType);

    List<Course> buildCourseList(ResultSet resultSet, List<Course> listType);

    List<Schedule> buildScheduleList(ResultSet resultSet,List<Schedule> listType);

    Schedule buildSchedule(ResultSet resultSet);

    int create(Schedule schedule);

    int update(Schedule schedule);

    int delete(Schedule schedule);

}
