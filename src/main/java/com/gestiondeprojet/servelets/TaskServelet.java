package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Dao.TaskDao;
import com.gestiondeprojet.Dao.TaskDaoImp;
import com.gestiondeprojet.Enteties.Task;




public class TaskServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TaskDao taskDao=null;
	 /**
     * @see HttpServlet#HttpServlet()
     */
	public TaskServelet() {
	    super();
		
	}
	
	 @Override
	    public void init(ServletConfig config) throws ServletException {
		
	    	super.init(config);  taskDao=new TaskDaoImp(); 		
	    }
	 
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
        case "/add":
            addNewTask(request, response);
            break;
        case "/update":
            updateTask(request, response);
            break;
        case "/delete":
            deleteTask(request, response);
            break;
        case "/list":
            getAllTasks(request, response);
            break;
        case "/get":
            getTask(request, response);
            break;
        default:
            getAllTasks(request, response);
            break;
    }
}

    private void addNewTask(HttpServletRequest request, HttpServletResponse response) {}
    private void updateTask(HttpServletRequest request, HttpServletResponse response) {}
    private void deleteTask(HttpServletRequest request, HttpServletResponse response) {}
    private void getAllTasks(HttpServletRequest request, HttpServletResponse response) {
    	
		
				
		try 
		{List<Task> tasks = taskDao.getAllTasks();
		    System.out.println(tasks);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("tasks", tasks);
			dispatcher.forward(request, response);
		}
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    private void getTask(HttpServletRequest request, HttpServletResponse response) {}
  
}
