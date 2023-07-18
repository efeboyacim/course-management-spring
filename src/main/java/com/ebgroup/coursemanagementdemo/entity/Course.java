package com.ebgroup.coursemanagementdemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="course_name")
    private String courseName;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST , CascadeType.MERGE , CascadeType.REFRESH})
    @JoinColumn(name="lecturerID")
    private Lecturer lecturer;

    public Course(){

    }

    public Course(String courseName, Lecturer lecturer) {
        this.courseName = courseName;
        this.lecturer = lecturer;
    }

    public Course(String courseName){
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", lecturer=" + lecturer +
                '}';
    }
}
