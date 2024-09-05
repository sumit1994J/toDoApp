package com.App.toDoApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.App.toDoApp.model.TaskDetails;
import com.App.toDoApp.model.Userdetails;
import com.App.toDoApp.service.TaskDetailsService;
import com.App.toDoApp.service.UserDetailsService;

import jakarta.servlet.http.HttpSession;





@Controller
public class MainController {
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	TaskDetailsService taskDetailsService;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		
		ModelAndView login=new ModelAndView("login");
		return login;
	}
	
	@PostMapping("/Authentication")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session,Model model) {
		Userdetails authenticateUser = userDetailService.authenticateUser(username, password);
		
		if (authenticateUser == null) {
			 model.addAttribute("error", "Invalid username or password."); 
	        return "redirect:/login"; 
	    }else {
	    	
	    	if (username.equals(authenticateUser.getEmail()) && password.equals(authenticateUser.getPassword())) {
	    		
	    		session.setAttribute("name", authenticateUser.getName());
	    		session.setAttribute("email", authenticateUser.getEmail());
	    		session.setAttribute("id", authenticateUser.getId());
	    		authenticateUser.getId();
	            return "redirect:/home"; 
	        } else {
	        	model.addAttribute("error", "Invalid username or password."); 
	            return "redirect:/login?error=true"; 
	        }
	    }
	    
        
    }
	
	
	
	
	
	@GetMapping("/registration")
	public ModelAndView getRegistrationPage(@ModelAttribute("UserDetails") Userdetails userDetails){
		
		
		ModelAndView registration=new ModelAndView("registration");
		return registration;
	}
	
	/*
	 * @PostMapping("/registrationSubmit") public String
	 * saveUser(@ModelAttribute("UserDetails") UserDetails userDetails, Model model)
	 * {
	 * 
	 * try { userDetailService.saveformDetails(userDetails);
	 * model.addAttribute("message", "Registered Successfully"); } catch
	 * (DataIntegrityViolationException e) { // Catch the exception thrown when
	 * trying to insert a non-unique entry // Assuming the email field is unique
	 * model.addAttribute("errorMessage",
	 * "Email already exists. Please choose a different email."); return
	 * "registration"; // Return the registration page with the error message }
	 * return "registration"; }
	 */
	@PostMapping("/registrationSubmit")
	public String saveUser(@ModelAttribute("UserDetails") Userdetails userDetails, Model model) {
	    ModelAndView registration = new ModelAndView("registration");

	    // Check if the email already exists in the database
	    Userdetails existingUser = userDetailService.findByEmail(userDetails.getEmail());
	    if (existingUser != null) {
	        // Email already exists, display an error message to the user
	        model.addAttribute("message", "User already exists.");
	        return "registration"; // Return the registration page with the error message
	    }

	    // Save the user details if the email is unique
	    userDetailService.saveformDetails(userDetails);
	    model.addAttribute("message", "Registered Successfully");

	    return "registration";
	}

	
	@RequestMapping("/home")
	public ModelAndView home(HttpSession session) {
		 try {
			 
			 
		 }catch(Exception e) {
			 
		 }
		String name3 = (String) session.getAttribute("name");
		
		Object id = session.getAttribute("id");
		 int userId = Integer.parseInt(id.toString());
		 
		 
		List<TaskDetails> allTaskDetails = taskDetailsService.getAllTaskDetails(userId);
		
		Object name2 = session.getAttribute("name");
		Object email2 = session.getAttribute("email");
		
		ModelAndView home=new ModelAndView("home");
		home.addObject("AllTaskDetails", allTaskDetails);
		home.addObject("email", email2);
		home.addObject("name", name2);
		return home;
	}
	
	@PostMapping("/taskSubmit")
	public String saveTask(@ModelAttribute("TaskDetails") TaskDetails taskDetails, Model model,HttpSession session) {
		
		Object name = session.getAttribute("name");
		Object id = session.getAttribute("id");
		 int userId = Integer.parseInt(id.toString());
		ModelAndView home=new ModelAndView("home");
		taskDetails.setFlag("100");
		taskDetails.setTaskby(name.toString());
		taskDetails.setUserid(userId);
		taskDetailsService.saveTaskDetails(taskDetails);
		
		List<TaskDetails> allTaskDetails = taskDetailsService.getAllTaskDetails(userId);
		
		model.addAttribute("message","task saved  Successfully");
		home.addObject("AllTaskDetails", allTaskDetails);
	
		return "redirect:/home";
	}
	
	@PostMapping("/finishTask")
	public String finishTask(@ModelAttribute("TaskDetails") TaskDetails taskDetails, Model model,HttpSession session) {
		
		ModelAndView home=new ModelAndView("home");
		
		Object name = session.getAttribute("name");
		
		Object id = session.getAttribute("id");
		 int userId = Integer.parseInt(id.toString());
		
		int Taskid = taskDetails.getId();
		
		Optional<TaskDetails> taskDetailsById = taskDetailsService.getTaskDetailsById(Taskid);
		TaskDetails taskDetails2 = taskDetailsById.get();
	     
		taskDetails2.setFlag("50");
		
		taskDetailsService.saveTaskDetails(taskDetails2);
		
		List<TaskDetails> allTaskDetails = taskDetailsService.getAllTaskDetails(userId);
		
	
		home.addObject("AllTaskDetails", allTaskDetails);
		model.addAttribute("message","task Completed");
		
		return "redirect:/home";
	}
	
	@GetMapping("/upgradeProfile")
	public ModelAndView upgradeProfile( HttpSession session, Model model) {
	   
		Object email = session.getAttribute("email");
	     
		ModelAndView updateForm=new ModelAndView("updateForm");
		 
		Userdetails userDetails = userDetailService.findByEmail(email.toString());
	    
		model.addAttribute("userDetails", userDetails);
	    // Redirect to home page after profile update
	    return updateForm;
	}
	
	
	 @PostMapping("/updateSubmit")
	    public String processUpdateForm(HttpSession session, Userdetails userDetails, String confirmPassword,Model model) {
	        // Assuming you have userDetails stored in the session
		  
		 Object email = session.getAttribute("email");
		 Userdetails oldUserDetails = userDetailService.findByEmail(email.toString());
		 if(!userDetails.getPassword().isEmpty()) {
			 oldUserDetails.setPassword(userDetails.getPassword());
		 }
		 oldUserDetails.setName(userDetails.getName());
		 oldUserDetails.setEmail(userDetails.getEmail());
		 oldUserDetails.setContact(userDetails.getContact());
		 
		
		 userDetailService.updateUserDetails(oldUserDetails);
		 model.addAttribute("message","User details upgraded sucessfully");
	        return "redirect:/upgradeProfile";
	    }
	 
	 @RequestMapping("/finishedTask")
		public ModelAndView finishedTask(HttpSession session) {
			 
			Object name3 = session.getAttribute("name");
			
			Object id = session.getAttribute("id");
			 int userId = Integer.parseInt(id.toString());
			List<TaskDetails> allTaskDetails = taskDetailsService.getAllFinishedTaskDetails(userId);
			
			Object name2 = session.getAttribute("name");
			Object email2 = session.getAttribute("email");
			
			ModelAndView home=new ModelAndView("finishtask");
			home.addObject("AllTaskDetails", allTaskDetails);
			home.addObject("email", email2);
			home.addObject("name", name2);
			return home;
		}

}
