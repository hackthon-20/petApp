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
public class UserServiceImpl implements UserService {

	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User findByName(String name) {

		User user = userRepository.findByName(name);
		
		return user;
	}

	@Override
	public List<User> listUsers() {

		List<User> user = userRepository.findAll();
		return user;
	}

	public void updateUser(User user) {

		User userTmp = getUserById(user.getId());
		
		userTmp.setName(user.getName());
		userTmp.setPassword(user.getPassword());
		
		userRepository.flush();	
	}

	public User getUserById(long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	public void removeUser(long id) {

		userRepository.deleteById(id);
	}
	
}
