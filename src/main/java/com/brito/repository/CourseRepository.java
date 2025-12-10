package com.brito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
