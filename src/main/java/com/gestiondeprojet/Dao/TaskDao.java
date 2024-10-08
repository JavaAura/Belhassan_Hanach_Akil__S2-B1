package com.gestiondeprojet.Dao;

import java.sql.SQLException;
import java.util.List;

import com.gestiondeprojet.Enteties.Task;

public interface TaskDao {
	
	
	  public void addTask(Task tache) throws SQLException ;
	  public void updateTask(Task tache) throws SQLException;
	  public List<Task> getAllTasks()throws SQLException;
	  Task getTaskById(int id)throws SQLException;
	  public void deleteTaskById(int id) throws SQLException;
	  public List<Task> getTasksPaginated(int page, int pageSize) throws SQLException;
	  public int getTotalTaskCount() throws SQLException;
}