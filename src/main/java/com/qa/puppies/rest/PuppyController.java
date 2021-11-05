package com.qa.puppies.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.puppies.domain.Puppy;
import com.qa.puppies.service.PuppyService;

@RestController // enables request handling
@RequestMapping(value = "/puppy") // prepends /puppy to all of the endpoints
public class PuppyController {

	private PuppyService service;

	@Autowired
	public PuppyController(PuppyService service) {
		super();
		this.service = service;
	}

	@GetMapping("/hello") // listen for a request at /hello
	public String hello() {
		return "Hello, World!"; // sends response
	}

	@PostMapping("/create") // triggered by a POST request to /create
	public ResponseEntity<Puppy> createPuppy(@RequestBody Puppy newPuppy) { // a puppy object in the request + response
		Puppy responseBody = this.service.createPuppy(newPuppy);
		return new ResponseEntity<Puppy>(responseBody, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Puppy>> getPuppies() {
		return ResponseEntity.ok(this.service.getPuppies()); // unnecessary due to default being 200 anyway
	}

	@GetMapping("/get/{id}") // getpuppy with id of {id}
	public Puppy getPuppy(@PathVariable Integer id) {
		return this.service.getPuppy(id);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Puppy> replacePuppy(@PathVariable Integer id, @RequestBody Puppy newPuppy) {
		System.out.println("Replacing puppy with id " + id + " with " + newPuppy);
		Puppy body = this.service.replacePuppy(id, newPuppy);
		return new ResponseEntity<Puppy>(body, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removePuppy(@PathVariable Integer id) {
		System.out.println("Removing puppy with id " + id);
		boolean removed = this.service.removePuppy(id);
		if (removed) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
