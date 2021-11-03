package com.qa.puppies.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.puppies.domain.Puppy;

public class PuppyServiceList implements PuppyService {

	private List<Puppy> puppies = new ArrayList<>();

	@Override
	public Puppy createPuppy(Puppy newPuppy) { // a puppy object in the request + response
		this.puppies.add(newPuppy);
		return this.puppies.get(this.puppies.size() - 1);
	}

	@Override
	public List<Puppy> getPuppies() {
		return this.puppies;
	}

	@Override
	public Puppy getPuppy(Integer id) {
		return this.puppies.get(id);
	}

	@Override
	public Puppy replacePuppy(Integer id, Puppy newPuppy) {
		return this.puppies.set(id, newPuppy); // replaces the puppy at that index
	}

	@Override
	public boolean removePuppy(Integer id) {
		Puppy toRemove = this.puppies.get(id);
		this.puppies.remove(id.intValue());
		return !this.puppies.contains(toRemove);
	}

}
