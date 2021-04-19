package com.example.studentexample.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentexample.model.Student;
import com.example.studentexample.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application.properties")
public class StudentControllerTest {


	private static final ObjectMapper om = new ObjectMapper();
	private static EmbeddedDatabase embDb;

	@Autowired
	private MockMvc mockMvc;
	

	@Mock
	private StudentRepository studentRepository;

	
	@BeforeClass
	public static void setUp() throws Exception {
		embDb = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.setName(EmbeddedDatabaseFactory.DEFAULT_DATABASE_NAME).build();
		assertThat(embDb, is(notNullValue()));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		embDb.shutdown();
	}


	@Test
	public void find_studentId_OK() throws Exception {

		mockMvc.perform(get("/student/1"))
		/*.andDo(print())*/
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Anupama")))
		.andExpect(jsonPath("$.description", is("Beginner")));

	}

	@Test
	public void find_allStudent_OK() throws Exception {

		mockMvc.perform(get("/student"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("Anupama")))
		.andExpect(jsonPath("$[0].description", is("Beginner")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].name", is("Mickey")))
		.andExpect(jsonPath("$[1].description", is("Expert")));
	}

	@Test
	@Rollback(false)
	public void save_student_OK() throws Exception {

		Student newStudent = new Student(6, "Anupama Jere","Novice");
		when(studentRepository.save(newStudent)).thenReturn(newStudent);
		

		mockMvc.perform(post("/student")
				.content(om.writeValueAsString(newStudent))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(6)))
		.andExpect(jsonPath("$.name", is("Anupama Jere")))
		.andExpect(jsonPath("$.description", is("Novice")));

	}

	@Test
	@Rollback(false)
	public void update_student_OK() throws Exception {

		Student updateStudentDtl = new Student(1, "Anupama V J", "Beginner");
		when(studentRepository.saveAndFlush(updateStudentDtl)).thenReturn(updateStudentDtl);

		mockMvc.perform(put("/student/1")
				.content(om.writeValueAsString(updateStudentDtl))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Anupama V J")))
		.andExpect(jsonPath("$.description", is("Beginner")));

	}

	@Test
	public void delete_student_OK() throws Exception {

		doNothing().when(studentRepository).deleteById(5L);
		mockMvc.perform(delete("/student/5"))
		.andExpect(status().isOk());
	}

}
