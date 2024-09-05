package com.App.toDoApp.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_user")
@Component
public class Userdetails {
	
	   @Id 
	   @GeneratedValue(strategy = GenerationType.AUTO)
	 	private int id;
	   
	   private String email;
	   
	   private String name;
	   
	   private String contact;
	   
	   private String password;
	   
	   private String role;

	public Userdetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Userdetails(int id, String email, String name, String contact, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.contact = contact;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Userdetails [id=" + id + ", email=" + email + ", name=" + name + ", contact=" + contact + ", password="
				+ password + ", role=" + role + "]";
	}


	   
	   
	   

}
