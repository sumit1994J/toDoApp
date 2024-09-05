package com.App.toDoApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.toDoApp.model.TaskDetails;
import com.App.toDoApp.model.Userdetails;
import com.App.toDoApp.repository.TaskDetailsRepository;



@Service
public class TaskDetailsService {

	@Autowired
	TaskDetailsRepository taskDetailsRepository;
	
	public void saveTaskDetails(TaskDetails taskDetails) {
		
		taskDetailsRepository.save(taskDetails);
    }
	
	
	/*
	 * public List<TaskDetails> getAllTaskDetails(String name) {
	 * Iterable<TaskDetails> iterable =
	 * taskDetailsRepository.findByTaskbyAndFlag(name, "100"); List<TaskDetails>
	 * list = new ArrayList<>(); iterable.forEach(list::add); return list; }
	 */
	
	public List<TaskDetails> getAllTaskDetails(int id) {
        Iterable<TaskDetails> iterable = taskDetailsRepository.findByUseridAndFlag(id, "100");
        List<TaskDetails> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
	
	public List<TaskDetails> getAllFinishedTaskDetails(int id) {
        Iterable<TaskDetails> iterable = taskDetailsRepository.findByUseridAndFlag(id, "50");
        List<TaskDetails> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
	
	
	public Optional<TaskDetails> getTaskDetailsById(int id) {
        return taskDetailsRepository.findById(id);
    }
}
