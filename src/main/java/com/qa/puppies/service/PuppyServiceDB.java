package com.qa.puppies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.puppies.domain.Puppy;
import com.qa.puppies.exceptions.PuppyNotFoundException;
import com.qa.puppies.repos.PuppyRepo;

@Service
public class PuppyServiceDB implements PuppyService {

	private PuppyRepo repo;

	public PuppyServiceDB(PuppyRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Puppy createPuppy(Puppy newPuppy) {
		return this.repo.save(newPuppy);
	}

	@Override
	public List<Puppy> getPuppies() {
		return this.repo.findAll();
	}

	@Override
	public Puppy getPuppy(Integer id) {
//		return this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No puppy found with id: " + id));
		Optional<Puppy> puppyOptional = this.repo.findById(id);

		if (puppyOptional.isPresent()) {
			Puppy puppy = puppyOptional.get();
			return puppy;
		} else {
			throw new PuppyNotFoundException();
		}
	}

	@Override
	public Puppy replacePuppy(Integer id, Puppy newPuppy) {
		Puppy existing = this.getPuppy(id);

		existing.setBreed(newPuppy.getBreed());
		existing.setHeight(newPuppy.getHeight());
		existing.setName(newPuppy.getName());

		return this.repo.save(existing);
	}

	@Override
	public boolean removePuppy(Integer id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
