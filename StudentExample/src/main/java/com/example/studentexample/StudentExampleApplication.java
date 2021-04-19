package com.example.studentexample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.studentexample.controller.StudentController;
import com.example.studentexample.model.Student;

@SpringBootApplication
@ComponentScan("com.example")
public class StudentExampleApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentExampleApplication.class, args);
	}

	/*@PostConstruct
	private void initDb() {

		//List<Course> courselst = new ArrayList<Course>();


		Course course = new Course();

		//course.setId("C1");
		course.setName("SpringBoot");
		course.setDescription("SpringBoot New Concepts");
		courseRepository.save(course);


		Student student = new Student();
		student.setName("Anupama J");
		// student.setId("A1234");
		student.setDescription("NOVICE");
		studentCtrl.saveDetails(student);
	}
*/
	
}
