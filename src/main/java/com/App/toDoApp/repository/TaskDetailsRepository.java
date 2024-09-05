package com.App.toDoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.App.toDoApp.model.TaskDetails;



public interface TaskDetailsRepository extends CrudRepository<TaskDetails, Integer>{

	 List<TaskDetails> findByTaskbyAndFlag(String taskby, String flag);
	 
	 List<TaskDetails> findByUseridAndFlag(int userid, String flag);
	
	
}
