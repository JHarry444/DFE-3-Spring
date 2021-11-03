package com.qa.puppies.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.puppies.domain.Puppy;

@Service
public class PuppyServiceDB implements PuppyService {

	@Override
	public Puppy createPuppy(Puppy newPuppy) {
		// TODO Auto-generated method stub
		return null;
	}

	public void patchPuppy() {

	}

	@Override
	public List<Puppy> getPuppies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puppy getPuppy(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puppy replacePuppy(Integer id, Puppy newPuppy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removePuppy(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
