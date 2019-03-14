package com.pet.peer.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.peer.model.Pet;
import com.pet.peer.model.User;
import com.pet.peer.repository.PetRepository;
import com.pet.peer.repository.UserRepository;

@Service
public class PetServiceImpl  implements PetService {

	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Pet savePets(Pet pet) {
		
		return petRepository.save(pet);
	}

	public List<Pet> getAllPets() {

		List<Pet> pet = petRepository.findAll();
		return pet;
	}
	
	public User buyPet(long petId,String userName) {
		
		User user = userRepository.findByName(userName);
		Pet pet = petRepository.findById(petId).orElse(null);
		user.getPets().add(pet);
		return userRepository.save(user);
		
	}

	public Set<Pet> getMyPets(String userName) {

		Set<Pet> myPets = userRepository.findByName(userName).getPets();
		
		return myPets;
	}

	public Pet findByNameAgePlace(String petname, int age, String place) {
		
		return petRepository.findByNameAgePlace(petname, age, place);
	}
	
	

}
