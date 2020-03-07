package com.louay.projects.registration.model;

import com.louay.projects.registration.model.dao.CourseDAO;
import com.louay.projects.registration.model.dao.ScheduleDAO;
import com.louay.projects.registration.model.dao.StudentDAO;
import com.louay.projects.registration.model.dao.impl.CourseDAOImpl;
import com.louay.projects.registration.model.dao.impl.ScheduleDAOImpl;
import com.louay.projects.registration.model.dao.impl.StudentDAOImpl;
import com.louay.projects.registration.model.entity.Course;
import com.louay.projects.registration.model.entity.Schedule;
import com.louay.projects.registration.model.entity.Student;

public class Main {
    public static void main(String[] args) {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl(new StudentDAOImpl(),new CourseDAOImpl());
        Schedule schedule = new Schedule();
        schedule.setIdStudent("1");
        schedule.setIdCourse("1");
        System.out.println(scheduleDAO.create(schedule));

    }

}
