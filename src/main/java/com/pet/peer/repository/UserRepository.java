package com.pet.peer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.peer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String name);
}
