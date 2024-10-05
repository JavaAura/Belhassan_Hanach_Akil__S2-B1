package com.gestiondeprojet.service;

import java.sql.SQLException;
import java.util.List;

import com.gestiondeprojet.Dao.TaskDao;
import com.gestiondeprojet.Dao.TaskDaoImp;
import com.gestiondeprojet.Enteties.Task;

public class TaskService {

	private TaskDao taskDao=new TaskDaoImp();

    public void addTask(Task tache) throws SQLException {
        taskDao.addTask(tache);
    }

    public void updateTask(Task tache) throws SQLException {
        taskDao.updateTask(tache);
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskDao.getAllTasks();
    }

    public Task getTaskById(int id) throws SQLException {
        return taskDao.getTaskById(id);
    }

    public void deleteTaskById(int id) throws SQLException {
        taskDao.deleteTaskById(id);
    }

    public List<Task> getTasksPaginated(int page, int pageSize) throws SQLException {
        return taskDao.getTasksPaginated(page, pageSize);
    }

    public int getTotalTaskCount() throws SQLException {
        return taskDao.getTotalTaskCount();
    }
}