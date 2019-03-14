package com.pet.peer.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pet.peer.exception.UserNotFoundException;
import com.pet.peer.model.Employee;
import com.pet.peer.model.EmployeeList;
import com.pet.peer.model.Pet;
import com.pet.peer.model.Quote;
import com.pet.peer.model.User;
import com.pet.peer.repository.PetRepository;
import com.pet.peer.service.PetService;
import com.pet.peer.service.UserService;

@RestController
@RequestMapping("/api")
public class RegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	//BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PetService petService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(path="/registeruser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/listusers")
	public ResponseEntity<List<User>> allUser(User user) {
		return new ResponseEntity<List<User>>(userService.listUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/listpets")
	public ResponseEntity<List<Pet>> allPets(Pet pet){
		return new ResponseEntity<List<Pet>>(petService.getAllPets(),HttpStatus.OK);
	}
	
	@PostMapping("/addpet")
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
		return new ResponseEntity<Pet>(petService.savePets(pet), HttpStatus.OK);
	}
	
	@PostMapping("/registerpet")
	public ResponseEntity<User> registerPet(@RequestParam long petId, @RequestParam String name ) {
		return new ResponseEntity<User>(petService.buyPet(petId, name), HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable long userId) {
		User user = userService.getUserById(userId);
		if(user != null) {
			return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.OK);
		} else {
			throw new UserNotFoundException("User Id"+userId);
		}
	}
	
	@GetMapping("/searchPet")
	public ResponseEntity<Pet> searchPet(@RequestParam String name,
										  @RequestParam int age,
										  @RequestParam String place) {
		
		Pet pet = petService.findByNameAgePlace(name, age, place);


		return new ResponseEntity<Pet>(pet,HttpStatus.OK);
		
	}
	
	// REST Template 
	@GetMapping("/getQuote")
	public String getQuote() {
		Quote quote = restTemplate.getForObject(
				"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		
		return quote.toString();
	}
	
	@GetMapping("/getEmployees")
	public EmployeeList getEmployees() {
		EmployeeList empList = restTemplate.getForObject(
				"http://dummy.restapiexample.com/api/v1/employees", EmployeeList.class);
		
		return empList;
	}
}
