package com.App.toDoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.toDoApp.model.Userdetails;
import com.App.toDoApp.repository.userDetailsRepo;



@Service
public class UserDetailsService {

	
	@Autowired
	userDetailsRepo userDetailsRepo;
	
	@Autowired
	Userdetails userDetails;
	
	public void saveformDetails(Userdetails userDetails) {
		userDetailsRepo.save(userDetails);
    }
	
	
	 public Userdetails authenticateUser(String email, String password) {
	        return userDetailsRepo.findByEmailAndPassword(email, password);
	    }
	 
	 public Userdetails findByEmail(String email) {
	        return userDetailsRepo.findByEmail(email);
	    }
	 
	 public void updateUserDetails(Userdetails userDetails) {
		
		 userDetailsRepo.save(userDetails);
	    }
}
