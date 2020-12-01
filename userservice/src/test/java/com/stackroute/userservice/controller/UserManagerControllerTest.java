package com.stackroute.userservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserManagerService;

@RunWith(SpringRunner.class)
@WebMvcTest

public class UserManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserManagerService accountManagerService;

	private User user;

	@InjectMocks
	private UserManagerController accountManagerController;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountManagerController).build();

		user = new User();
		user.setUsername("Deepak123");
		user.setFirstName("Deepak123");
		user.setLastName("Smith");
		user.setPassword("abcdef");
	}

	@Test
	public void testRegisterUserSucess() throws Exception {
		Mockito.when(accountManagerService.saveUser(user)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isNotAcceptable())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testRegisterUserNegative() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(new User()))).andExpect(MockMvcResultMatchers.status().isNotAcceptable())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testLoginUserSuccess() throws Exception {
		String userId = "Deepak123";
		String password = "abcdef";

		Mockito.when(accountManagerService.findByUsernameAndPassword(userId, password)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/validate").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testLoginUserFail() throws Exception {
		String userId = "Deepak123";
		String password = "abcdef";

		Mockito.when(accountManagerService.findByUsernameAndPassword(userId, password)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/validate").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
