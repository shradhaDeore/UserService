package com.swarish.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarish.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(long id);

	void deleteById(long id);
	

}
