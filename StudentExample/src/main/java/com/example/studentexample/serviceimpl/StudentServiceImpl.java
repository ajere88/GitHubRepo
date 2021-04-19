package com.example.studentexample.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentexample.model.Student;
import com.example.studentexample.repository.StudentRepository;
import com.example.studentexample.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	  private StudentRepository studentRepository;

	@Override
	public List<Student> fetchAllStudentDetails() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		return studentRepository.getOne(id);
	}

	@Override
	public Student saveDetails(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteDetails(Student student) {
		studentRepository.delete(student);
		
	}
	  
}
