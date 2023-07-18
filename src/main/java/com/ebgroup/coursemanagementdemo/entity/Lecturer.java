package com.ebgroup.coursemanagementdemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lecturerID")
    private int id;

    @Column(name="lecturer_name")
    private String lecturer_name;


    @JsonBackReference
    @OneToMany(mappedBy = "lecturer",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    public Lecturer(){

    }

    public Lecturer(int id, String lecturer_name, List<Course> courses) {
        this.id = id;
        this.lecturer_name = lecturer_name;
        this.courses = courses;
    }

    public Lecturer(String lecturerName) {
        this.lecturer_name = lecturerName;
    }

    public void addToCourse(Course theCourse) {
        courses.add(theCourse);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturerName() {
        return lecturer_name;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturer_name = lecturerName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", lecturerName='" + lecturer_name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
