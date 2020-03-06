package com.louay.projects.registration.model.dao;

import com.louay.projects.registration.model.entity.Schedule;

import java.util.List;

public interface ScheduleDAO {

    List<Schedule> findByIDSchedule(String key);

    List<Schedule> findByIDCourse(String key);

    List<Schedule> findByIDStudent(String key);

    int create(Schedule schedule);

    int update(Schedule schedule);

    int delete(Schedule schedule);

}
