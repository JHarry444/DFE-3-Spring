package com.qa.puppies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Puppy not found with that ID") // converts error to 404
public class PuppyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1213048928725526908L;

}
