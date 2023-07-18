package com.ebgroup.coursemanagementdemo.dao;

import com.ebgroup.coursemanagementdemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
