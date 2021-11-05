package com.qa.puppies.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.puppies.domain.Puppy;

@SpringBootTest // boots the entire context
@AutoConfigureMockMvc // creates the MockMVC object for sending our test requests
@Sql(scripts = { "classpath:puppy-schema.sql",
		"classpath:puppy-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
// runs schema and data file before each test
// if you see error like 'x is not a column' check you're using '' for strings
@ActiveProfiles("test") // sets the profile to 'test'
public class PuppyIntegrationTest {

	@Autowired
	private MockMvc mvc;

	// Too Long, Didn't Read:
	@Autowired
	private ObjectMapper mapper; // the EXACT SAME mapper that spring uses to convert objects to and from JSON

	@Test
	void testCreate() throws Exception {
		Puppy requestBody = new Puppy("Rex 2", "G. Shepherd", 44);
		String requestBodyAsJSON = this.mapper.writeValueAsString(requestBody);

		RequestBuilder request = post("/puppy/create").contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyAsJSON); // sets up the test request

		Puppy responseBody = new Puppy(2, "Rex 2", "G. Shepherd", 44);
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

		ResultMatcher checkStatus = status().isCreated(); // check the status code is 201
		ResultMatcher checkBody = content().json(responseBodyAsJSON); // check the body matches the example

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody); // performs request and checks the
																				// response
	}

	@Test
	void testPuppyNotFound() throws Exception {
		this.mvc.perform(get("/puppy/get/9999999")).andExpect(status().isNotFound());
	}

	@Test
	void testGetAll() throws Exception {

		RequestBuilder request = get("/puppy/getAll");

		ResultMatcher checkStatus = status().isOk();

		Puppy puppy = new Puppy(1, "Woofy", "Daschund", 10);
		List<Puppy> puppies = List.of(puppy);
		String responseBody = this.mapper.writeValueAsString(puppies);
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGet() throws Exception {
		final String responseBody = this.mapper.writeValueAsString(new Puppy(1, "Woofy", "Daschund", 10));
		this.mvc.perform(get("/puppy/get/1")).andExpect(status().isOk()).andExpect(content().json(responseBody));
	}

	@Test
	void testReplace() throws Exception {
		final String responseBody = this.mapper.writeValueAsString(new Puppy(1, "Rex", "Doberman", 30));

		RequestBuilder request = put("/puppy/replace/1").contentType(MediaType.APPLICATION_JSON).content(responseBody);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(responseBody);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/puppy/remove/1")).andExpect(status().isNoContent());
	}

}
