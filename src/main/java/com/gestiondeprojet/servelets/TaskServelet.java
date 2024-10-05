package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.gestiondeprojet.Dao.TaskDao;
import com.gestiondeprojet.Dao.TaskDaoImp;
import com.gestiondeprojet.Enteties.Task;
import com.gestiondeprojet.Enteties.enums.Priorite;
import com.gestiondeprojet.Enteties.enums.Statut;




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
		
	    	super.init(config); 
	    	taskDao=new TaskDaoImp(); 		
	    }
	 
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getRequestURI().substring(request.getContextPath().length() + request.getServletPath().length());
    System.out.println(action);

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

    private void addNewTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    
    	    String titre = request.getParameter("titre");
    	    String description = request.getParameter("description");
    	    Priorite priorite = Priorite.valueOf(request.getParameter("priorite")) ;
    	    Statut statut =   Statut.valueOf(request.getParameter("statut")) ;
    	    LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
    	    int membreId = Integer.parseInt(request.getParameter("membreId"));
    	    Task task =new Task( titre,  description,  priorite,  statut,  dateEcheance, membreId, 1);
    	    try {
				taskDao.addTask(task);
				List<Task> tasks = taskDao.getAllTasks();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("tasks", tasks);
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    private void updateTask(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
    	int taskId = Integer.parseInt(request.getParameter("taskId"));
    	
    	  String titre = request.getParameter("titre");
  	    String description = request.getParameter("description");
  	    Priorite priorite = Priorite.valueOf(request.getParameter("priorite")) ;
  	    Statut statut =   Statut.valueOf(request.getParameter("statut")) ;
  	    LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
  	    int membreId = Integer.parseInt(request.getParameter("membreId"));
  	    Task task=new Task( taskId,titre,  description,  priorite,  statut,  dateEcheance, membreId, 1);
  	    try {
			taskDao.updateTask(task);
			List<Task> tasks = taskDao.getAllTasks();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("tasks", tasks);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void deleteTask(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
    	int id = Integer.parseInt(request.getParameter("taskId"));	
    	try {
			taskDao.deleteTaskById(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void getAllTasks(HttpServletRequest request, HttpServletResponse response) {
      	try 
		{
      		List<Task> tasks = taskDao.getAllTasks();
      		
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
    private void getTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
    	int id = Integer.parseInt(request.getParameter("taskId"));						
		System.out.println("task" + id);
		
		
			
		try 
		{Task task = taskDao.getTaskById(id);
		System.out.println("getEmployee, result is ==> " + task);	
			ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String employeeStr = mapper.writeValueAsString(task);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(employeeStr.getBytes());
		}
		catch ( IOException  | SQLException e ) 
		{
			e.printStackTrace();
		}		
    }
  
}
