package com.qa.puppies.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.puppies.domain.Puppy;

@SpringBootTest // boots the entire context
@AutoConfigureMockMvc // creates the MockMVC object for sending our test requests
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

		Puppy responseBody = new Puppy(1, "Rex 2", "G. Shepherd", 44);
		String responseBodyAsJSON = this.mapper.writeValueAsString(responseBody);

		ResultMatcher checkStatus = status().isCreated(); // check the status code is 201
		ResultMatcher checkBody = content().json(responseBodyAsJSON); // check the body matches the example

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody); // performs request and checks the
																				// response
	}

}
