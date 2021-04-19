package com.example.studentexample.service;

import java.util.List;

import com.example.studentexample.model.Student;

public interface StudentService {

	List<Student> fetchAllStudentDetails();

	Student getStudentById(long id);

	Student saveDetails(Student student);

	void deleteDetails(Student student);

	
	
}
