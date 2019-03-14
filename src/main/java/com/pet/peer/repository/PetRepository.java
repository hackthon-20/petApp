package com.pet.peer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pet.peer.model.Pet;

@Repository
public interface PetRepository extends JpaRepository <Pet, Long> {

	@Query("SELECT p FROM Pet p where p.name = ?1 AND p.age = ?2 AND p.place=?3")
	public Pet findByNameAgePlace(String name,int age,String place);
}
