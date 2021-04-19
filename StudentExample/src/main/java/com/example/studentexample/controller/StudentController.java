package com.example.studentexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentexample.model.Student;
import com.example.studentexample.service.StudentService;
import com.example.studentexample.util.RecordNotFoundException;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;


	@RequestMapping(value = "/student", method =RequestMethod.GET)
	public List<Student> fetchAllStudentDetails() {
		return studentService.fetchAllStudentDetails();
	}

	@RequestMapping(value = "/student/{id}", method =RequestMethod.GET, produces="application/json")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") long id) throws Exception{

		Student student = studentService.getStudentById(id);
		if(student == null) {
	         throw new RecordNotFoundException("Invalid student id : " + id);
	    }
		return new ResponseEntity<Student>(student, HttpStatus.OK); 
	}

	@RequestMapping(value = "student/{id}", method = RequestMethod.PUT)
	public Student update(@PathVariable (value = "id")  long id, @RequestBody Student student){		
		return studentService.saveDetails(student);
	}

	@RequestMapping(value = "student/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		Student student = studentService.getStudentById(id);
		studentService.deleteDetails(student);

	}
	@RequestMapping(value="/student" ,method = RequestMethod.POST)
	public Student saveDetails(@RequestBody Student student) {
		return studentService.saveDetails(student);
	}
}
