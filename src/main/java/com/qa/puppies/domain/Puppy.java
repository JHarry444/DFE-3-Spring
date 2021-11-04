package com.qa.puppies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Puppy {

	@Id // tells Spring this is the PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Integer id;

	private String name;

	private String breed;

	private int height;

	public Puppy(Integer id, String name, String breed, int height) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.height = height;
	}

	public Puppy(String name, String breed, int height) {
		super();
		this.name = name;
		this.breed = breed;
		this.setHeight(height);
	}

	// REQUIRED
	public Puppy() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (height > 8 && height < 100) {
			this.height = height;
		} else {
			System.out.println("Invalid height: " + height);
		}
	}

	// Not required
	@Override
	public String toString() {
		return "Puppy [name=" + name + ", breed=" + breed + ", height=" + height + "]";
	}

}
