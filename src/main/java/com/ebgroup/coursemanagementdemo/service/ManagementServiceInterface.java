package com.ebgroup.coursemanagementdemo.service;

import com.ebgroup.coursemanagementdemo.entity.Course;
import com.ebgroup.coursemanagementdemo.entity.Lecturer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManagementServiceInterface {
    public ResponseEntity<String> newLecturer(String name);
    public String newCourse(String name,int lecturerID);
    public String deleteCourse(int courseID);
    public String deleteLecturer(int lecturerID);
    public void updateLecturer(Lecturer lecturer);
    public void updateCourse(Course course);
    public List<Course> showListCourse();
    public List<Lecturer> showListLecturer();
    public Course findByIDCourse(int id);
    public Lecturer findByIDLecturer(int lecturerID);
}
