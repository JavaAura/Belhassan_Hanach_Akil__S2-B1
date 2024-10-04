package com.gestiondeprojet.Dao;

import java.sql.SQLException;
import java.util.List;

import com.gestiondeprojet.Enteties.Task;

public interface TaskDao {
	
	
	  public void addTask(Task tache) throws SQLException ;
	  public void updateTask(Task tache) throws SQLException;
	  public List<Task> getAllTasks()throws SQLException;

}
