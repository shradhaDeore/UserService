package com.swarish.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swarish.api.dto.UserRequest;
import com.swarish.api.entity.User;
import com.swarish.api.exception.UserNotFoundException;
import com.swarish.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(UserRequest userrequest ) {
		System.out.println(userrequest.getId());
		User users = null;
		if(userrequest.getId()==0) {
			System.out.println("UserService.saveUser()"+userrequest.getId());
			User user=new  User(userrequest.getId(),userrequest.getName(),userrequest.getEmail(),
					userrequest.getMobile(),userrequest.getGender() ,userrequest.getAge(),  userrequest.getNationality());
			users=  userRepository.save(user);
		}else {
			System.out.println("UserService.saveUser()"+userrequest.getId());
			User existinguser=userRepository.findByUserId(userrequest.getId());
			User user=new  User(userrequest.getId(),userrequest.getName(),userrequest.getEmail(),
					userrequest.getMobile(),userrequest.getGender() ,userrequest.getAge(),  userrequest.getNationality());
			users = userRepository.save(user);
		}
		return users;
	}

	public List<User> getAllUsers(){
		return  userRepository.findAll();
	}
	
	public User getUserById(long id) throws UserNotFoundException {
		User user=userRepository.findByUserId(id);
		if(user!=null) {
			return user;
		}else {
			throw new UserNotFoundException("User not found with id"+id);
		}
	}

	public void deleteUserById (long id) throws UserNotFoundException {
		User user=userRepository.findByUserId(id);
		if(user!=null) {
			userRepository.deleteById(id);
		}else {
			throw new UserNotFoundException("User not found with id"+id);

		}

	}
}
