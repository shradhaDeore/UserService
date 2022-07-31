package com.swarish.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swarish.api.dto.UserRequest;
import com.swarish.api.entity.User;
import com.swarish.api.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final ResponseEntity<User> UserNotFoundException = null;
	
	@Autowired
	private UserService userService;

	@PostMapping(value= "/save")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
		return new ResponseEntity<>(userService.saveUser(userRequest),HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}


	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) throws com.swarish.api.exception.UserNotFoundException{
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) throws com.swarish.api.exception.UserNotFoundException {
		userService.deleteUserById(id);
		return "User information deleted with id" +id;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity <User> updateUserById(@RequestBody UserRequest userRequest,
			@PathVariable long id) {
		userService.saveUser(userRequest);
		return UserNotFoundException ;
	}
}
	


