package com.qa.puppies.service;

import java.util.List;

import com.qa.puppies.domain.Puppy;

public interface PuppyService {

	Puppy createPuppy(Puppy newPuppy);

	List<Puppy> getPuppies();

	Puppy getPuppy(Integer id);

	Puppy replacePuppy(Integer id, Puppy newPuppy);

	boolean removePuppy(Integer id);

}
