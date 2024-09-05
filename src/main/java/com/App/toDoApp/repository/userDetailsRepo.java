package com.App.toDoApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.App.toDoApp.model.Userdetails;

@Repository
public interface userDetailsRepo  extends CrudRepository<Userdetails, Integer>{

	 Userdetails findByEmailAndPassword(String email, String password);
	 
	 Userdetails findByEmail(String email);
	
}
