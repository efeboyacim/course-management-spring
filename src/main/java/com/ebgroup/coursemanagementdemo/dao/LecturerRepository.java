package com.ebgroup.coursemanagementdemo.dao;

import com.ebgroup.coursemanagementdemo.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,Integer> {
}
