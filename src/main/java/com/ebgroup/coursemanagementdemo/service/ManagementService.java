package com.ebgroup.coursemanagementdemo.service;

import com.ebgroup.coursemanagementdemo.dao.CourseRepository;
import com.ebgroup.coursemanagementdemo.dao.LecturerRepository;
import com.ebgroup.coursemanagementdemo.entity.Course;
import com.ebgroup.coursemanagementdemo.entity.Lecturer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService implements ManagementServiceInterface{

    private CourseRepository courseRepository;
    private LecturerRepository lecturerRepository;
    private EntityManager entityManager;

    @Autowired
    public ManagementService(CourseRepository courseRepository, LecturerRepository lecturerRepository,EntityManager entityManager){
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
        this.entityManager = entityManager;
    }

    @Override
    public ResponseEntity<String> newLecturer(String name){
        Lecturer newLecturer = new Lecturer(name);
        lecturerRepository.save(newLecturer);
        return new ResponseEntity<>("Created and saved: "+newLecturer, HttpStatus.CREATED);
    }

    @Override
    public String newCourse(String name,int lecturerID){
        Optional<Lecturer> a = lecturerRepository.findById(lecturerID);
        Lecturer courseLecturer = null;
        if (a.isPresent()) {
            courseLecturer = a.get();
        }
        else {
            throw new RuntimeException("Did not find lecturer id: " + lecturerID);
        }
        Course newCourse = new Course(name,courseLecturer);
        courseRepository.save(newCourse);
        return "Created and saved: " + newCourse.getCourseName();
    }

    @Override
    public String deleteCourse(int courseID){
        courseRepository.deleteById(courseID);
        return courseID + " removed.";
    }

    @Override
    public String deleteLecturer(int lecturerID){
        lecturerRepository.deleteById(lecturerID);
        return lecturerID + " removed.";
    }

    @Override
    @Transactional
    public void updateLecturer(Lecturer lecturer){
        entityManager.merge(lecturer);
    }

    @Override
    @Transactional
    public void updateCourse(Course course){
        entityManager.merge(course);
    }

    @Override
    public List<Course> showListCourse(){
        return courseRepository.findAll();
    }

    @Override
    public List<Lecturer> showListLecturer(){
        return lecturerRepository.findAll();
    }

    @Override
    public Course findByIDCourse(int id){
        Optional<Course> a = courseRepository.findById(id);
        Course courseTemp = null;
        if (a.isPresent()) {
            courseTemp= a.get();
        }
        else {
            throw new RuntimeException("Did not find course id: " + id);
        }

        return courseTemp;
    }

    @Override
    public Lecturer findByIDLecturer(int lecturerID){
        Optional<Lecturer> a = lecturerRepository.findById(lecturerID);
        Lecturer courseLecturer = null;
        if (a.isPresent()) {
            courseLecturer = a.get();
        }
        else {
            throw new RuntimeException("Did not find lecturer id: " + lecturerID);
        }
        return courseLecturer;
    }

    public void saveCourse(Course course){
        courseRepository.save(course);
    }

}
