package com.qa.puppies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.puppies.domain.Puppy;

@SpringBootApplication
public class SpringPuppiesApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringPuppiesApplication.class, args);

		ObjectMapper mapper = new ObjectMapper();

		Puppy demoPuppy = new Puppy("Woofy", "Daschund", 10);

		String demoPuppyAsJSON = mapper.writeValueAsString(demoPuppy);

		System.out.println(demoPuppyAsJSON);

		Puppy backToPuppy = mapper.readValue(demoPuppyAsJSON, Puppy.class);

		System.out.println(backToPuppy);
	}

}
