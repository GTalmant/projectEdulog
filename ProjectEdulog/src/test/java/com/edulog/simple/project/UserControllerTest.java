package com.edulog.simple.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserControllerTest {

private MockMvc mockMvc;
	
	@Autowired 
	private  WebApplicationContext ctx;
	
	@MockBean
	private UserService serviceMock;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/user"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutOk() throws Exception {
		User user = new User("good", "goodFirstName", "goodLastName");
		mockMvc.perform(put("/user/good")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutToShortFirstName() throws Exception {
		User user = new User("small", "i", "goodLastName");
		mockMvc.perform(put("/user/small")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutToLongFirstName() throws Exception {
		User user = new User("toLong", "Jean-Baptiste Frederique Jean-Rene", "goodLastName");
		mockMvc.perform(put("/user/toLong")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutToShortLastName() throws Exception {
		User user = new User("small", "goodFirstName", "i");
		mockMvc.perform(put("/user/small")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutToLongLastName() throws Exception {
		User user = new User("toLong", "goodFirstName", "Really To Long Name For This Constraint");
		mockMvc.perform(put("/user/toLong")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostOk() throws Exception {
		User user = new User("good", "goodFirstName", "goodLastName");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPostToShortFirstName() throws Exception {
		User user = new User("small", "i", "goodLastName");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostToLongFirstName() throws Exception {
		User user = new User("toLong", "Jean-Baptiste Frederique Jean-Rene", "goodLastName");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostToShortLastName() throws Exception {
		User user = new User("small", "goodFirstName", "i");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostToLongLastName() throws Exception {
		User user = new User("toLong", "goodFirstName", "Really To Long Name For This Constraint");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostOkWithTask() throws Exception {
		Task task = new Task("goodSize", "this description as a good size");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPostWithTaskToShort() throws Exception {
		Task task = new Task("short", "short");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPostWithTaskToLong() throws Exception {
		Task task = new Task("toLong", "Unfortunatly, this is a too long description to be accepted by the service, it is too bad, but it is the law");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutOkWithTask() throws Exception {
		Task task = new Task("goodSize", "this description as a good size");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(put("/user/good")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPutWithTaskToShort() throws Exception {
		Task task = new Task("short", "short");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(put("/user/good")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testPutWithTaskToLong() throws Exception {
		Task task = new Task("toLong", "Unfortunatly, this is a too long description to be accepted by the service, it is too bad, but it is the law");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		User user = new User("good", "goodFirstName", "goodLastName", tasks);
		mockMvc.perform(put("/user/good")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().is(Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testComplex() throws Exception {
		User user = new User("good", "goodFirstName", "goodLastName");
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user.toJson()))
		.andDo(print())
		.andExpect(status().isOk());
		mockMvc.perform(get("/user/good"))
		.andDo(print())
		.andExpect(status().isOk()).andReturn();
		mockMvc.perform(get("/user"))
		.andDo(print())
		.andExpect(status().isOk()).andReturn();
		
	}
}
