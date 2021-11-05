package com.qa.puppies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.puppies.service.PuppyService;

@SpringBootApplication
public class SpringPuppiesApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = SpringApplication.run(SpringPuppiesApplication.class, args);

		PuppyService service = ac.getBean(PuppyService.class);

		System.out.println(service);

	}

}
