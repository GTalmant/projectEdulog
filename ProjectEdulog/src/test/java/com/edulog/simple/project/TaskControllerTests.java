package com.edulog.simple.project;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.services.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TaskControllerTests {
	
	private MockMvc mockMvc;
	
	@Autowired 
	private  WebApplicationContext ctx;
	
	@MockBean
	private TaskService serviceMock;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/task"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPostToShort() throws Exception {
		Task task = new Task("toSmall", "small");
		mockMvc.perform(post("/task")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostToLong() throws Exception {
		Task task = new Task("toLong", "Unfortunatly, this is a too long description to be accepted by the service, it is too bad, but it is the law");
		mockMvc.perform(post("/task")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostOk() throws Exception {
		Task task = new Task("goodSize", "this description as a good size");
		mockMvc.perform(post("/task")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutToShort() throws Exception {
		Task task = new Task("toSmall", "small");
		mockMvc.perform(put("/task/toSmall")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutToLong() throws Exception {
		Task task = new Task("toLong", "Unfortunatly, this is a too long description to be accepted by the service, it is too bad, but it is the law");
		mockMvc.perform(put("/task/toLong")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutOk() throws Exception {
		Task task = new Task("goodSize", "this description as a good size");
		mockMvc.perform(put("/task/goodSize")
				.contentType(MediaType.APPLICATION_JSON)
				.content(task.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}

}
