package com.pet.peer.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pet.peer.model.Pet;
import com.pet.peer.model.User;

@Service
public interface PetService {
	
	public Pet savePets(Pet pet);
	
	public List<Pet> getAllPets();
	
	public User buyPet(long petId, String username);
	
	public Set<Pet> getMyPets(String username);
	
	public Pet findByNameAgePlace(String petname,int age,String Place);

}
