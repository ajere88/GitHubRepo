package com.example.studentexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentexample.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
