package com.sanjeev.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanjeev.learning.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	@Query(value = "Select * from user_table where email_id = ?1",nativeQuery= true)
	User findUserDetailsByEmailId(String emailId);
	
	@Query(value = "Select * from user_table",nativeQuery =true)
	List<User> findAllusers();

}
