package com.pet.peer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pet.peer.model.Pet;
import com.pet.peer.model.User;

@Service
public interface UserService {

	public User addUser(User user);
	
	public User findByName(String name);
	
	public List<User> listUsers();
	
	public void updateUser(User user);
	
	public User getUserById(long id);
	
	public void removeUser(long id);
	
}
