package com.example.studentexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.studentexample.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
