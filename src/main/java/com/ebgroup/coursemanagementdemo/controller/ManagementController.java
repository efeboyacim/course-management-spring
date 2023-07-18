package com.ebgroup.coursemanagementdemo.controller;

import com.ebgroup.coursemanagementdemo.entity.Course;
import com.ebgroup.coursemanagementdemo.entity.Lecturer;
import com.ebgroup.coursemanagementdemo.model.CourseModel;
import com.ebgroup.coursemanagementdemo.model.LecturerModel;
import com.ebgroup.coursemanagementdemo.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagementController {

    @Autowired
    ManagementService managementService;


    @PostMapping("/lecturer/create")
    public ResponseEntity<String> createLecturer(@RequestParam String name){
        return managementService.newLecturer(name);
    }

    @PostMapping("/course/create")
    public String createCourse(@RequestParam String name,@RequestParam int lecturerID){
        return managementService.newCourse(name,lecturerID);
    }

    @PutMapping("/course/update/{id}")
    public Course updateCourse(@RequestBody CourseModel courseModel, @PathVariable int id){
        Course tempCourse = managementService.findByIDCourse(id);
        tempCourse.setCourseName(courseModel.getCourseName());
        boolean check = false;
        for(Lecturer lec : showLecList()){
            if(lec.getId()==courseModel.getLecturerID()) {
                check = true;
            }
        }
        if(check){
            tempCourse.setLecturer(managementService.findByIDLecturer(courseModel.getLecturerID()));
        }else{
            return null;
        }
        managementService.updateCourse(tempCourse);
        return tempCourse;
    }

    @PutMapping("/lecturer/update/{id}")
    public Lecturer updateLecturer(@RequestBody LecturerModel lecturerModel, @PathVariable int id){
        Lecturer tempLec = managementService.findByIDLecturer(id);
        tempLec.setLecturerName(lecturerModel.getLecturerName());
        managementService.updateLecturer(tempLec);
        return tempLec;
    }

    @DeleteMapping("/lecturer/{id}")
    public String deleteLecturer(@PathVariable int id) {
        Lecturer getLecture = managementService.findByIDLecturer(id);
        managementService.deleteLecturer(id);
        return "Lecturer "+id+" deleted.";
    }

    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable int id) {

        Course theCourse = managementService.findByIDCourse(id);
        managementService.deleteCourse(id);
        return "Course "+id+" deleted.";
    }


    @GetMapping("/lecturer")
    public List<Lecturer> showLecList(){
        return managementService.showListLecturer();
    }

    @GetMapping("/course")
    public List<Course> showCourseList(){
        return (List<Course>) managementService.showListCourse();
    }

    @GetMapping("/")
    public String helloMsg(){
        return "Welcome to the Course management system.";
    }

    @GetMapping("/error")
    public String helloMsgForErrorPage(){
        return "Welcome to the Course management system.";
    }

    @GetMapping("/course/{id}")
    public Course getCourse(@PathVariable int id) {

        Course theCourse = managementService.findByIDCourse(id);

        if (theCourse == null) {
            throw new RuntimeException("Course id not found - " + id);
        }

        return theCourse;
    }

    @GetMapping("/lecturer/{id}")
    public Lecturer getLecture(@PathVariable int id) {

        Lecturer getLecture = managementService.findByIDLecturer(id);

        if (getLecture == null) {
            throw new RuntimeException("Course id not found - " + id);
        }

        return getLecture;
    }





}
