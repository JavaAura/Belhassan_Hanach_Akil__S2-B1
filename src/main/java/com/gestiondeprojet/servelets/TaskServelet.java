package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.gestiondeprojet.Dao.MemberDao;

import com.gestiondeprojet.Enteties.Equipe;
import com.gestiondeprojet.Enteties.Member;
import com.gestiondeprojet.Enteties.Task;
import com.gestiondeprojet.Enteties.enums.Priorite;
import com.gestiondeprojet.Enteties.enums.Statut;
import com.gestiondeprojet.service.EquipeService;
import com.gestiondeprojet.service.TaskService;




public class TaskServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EquipeService equipeService = null;
	private TaskService taskService = null;
	
	MemberDao memberDao=null;
	 /**
     * @see HttpServlet#HttpServlet()
     */
	public TaskServelet() {
	    super();
		
	}
	
	 @Override
	    public void init(ServletConfig config) throws ServletException {
		
	    	super.init(config); 
	    	
	    	taskService=new TaskService();
	    	equipeService=new EquipeService();
	    	memberDao=new MemberDao(); 
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
        case "/getTeam":
        	getTeams(request, response);
        	break; 
        default:
            getAllTasks(request, response);
            break;
    }
}

    private void addNewTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int projectid = Integer.parseInt(request.getParameter("id"));	
    	    String titre = request.getParameter("titre");
    	    String description = request.getParameter("description");
    	    Priorite priorite = Priorite.valueOf(request.getParameter("priorite")) ;
    	    Statut statut =   Statut.valueOf(request.getParameter("statut")) ;
    	    LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
    	    int membreId = Integer.parseInt(request.getParameter("membreId"));
    	    Task task =new Task( titre,  description,  priorite,  statut,  dateEcheance, membreId, projectid);
    	    try {
				taskService.addTask(task);
				List<Task> tasks = taskService.getAllTasks();
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
    	 int projectid = Integer.parseInt(request.getParameter("id"));
    	  String titre = request.getParameter("titre");
  	    String description = request.getParameter("description");
  	    Priorite priorite = Priorite.valueOf(request.getParameter("priorite")) ;
  	    Statut statut =   Statut.valueOf(request.getParameter("statut")) ;
  	    LocalDate dateEcheance = LocalDate.parse(request.getParameter("dateEcheance"));
  	    int membreId = Integer.parseInt(request.getParameter("membreId"));
  	    Task task=new Task( taskId,titre,  description,  priorite,  statut,  dateEcheance, membreId, projectid);
  	    try {
  	    	taskService.updateTask(task);
			List<Task> tasks = taskService.getAllTasks();
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
    		taskService.deleteTaskById(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void getAllTasks(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 int projectid = Integer.parseInt(request.getParameter("id"));	 
    		
    	        int page = 1; 
    	        int pageSize = 10; 

    	       
    	        if (request.getParameter("page") != null) {
    	            page = Integer.parseInt(request.getParameter("page"));
    	        }

    	        if (request.getParameter("pageSize") != null) {
    	            pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	        }

    	        List<Task> tasks = taskService.getTasksPaginated(page, pageSize ,projectid); 
    	        int totalTasks = taskService.getTotalTaskCount(projectid);
    	        List<Equipe> equipes=null;
    	        if(totalTasks == 0) {
    	        	 equipes= equipeService.fetchAllEquipes();
    	 	         System.out.println(equipes);
    	 	        
    	 
    	        }else {
    	        	 System.out.println(totalTasks);
    	        		         Task firstTask = tasks.get(0); 
    	 	        
    	 	        int memberId=firstTask.getMembreId();
    	 	       Equipe equipe = equipeService.fetchEquipeByMembreId(memberId);  

    	 	        
    	 	        equipes = new ArrayList<>();
    	 	        equipes.add(equipe); 
    	        }  request.setAttribute("equipes", equipes);
    	      
    	        int totalPages = (int) Math.ceil((double) totalTasks / pageSize);
System.out.println(tasks);
request.setAttribute("projectid", projectid);
    	        request.setAttribute("tasks", tasks);
    	        request.setAttribute("currentPage", page);
    	        request.setAttribute("totalPages", totalPages);

    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    	        dispatcher.forward(request, response);
    	    } catch (ServletException | IOException | SQLException e) {
    	        e.printStackTrace();
    	    }	
    }
    private void getTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
    	int id = Integer.parseInt(request.getParameter("taskId"));						
		System.out.println("task" + id);
		
		
			
		try 
		{Task task = taskService.getTaskById(id);
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
 private void getTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int teamId = Integer.parseInt(request.getParameter("TeamId"));						
		System.out.println("task" + teamId);
		
		
			
		try 
		{List<Member> members = memberDao.getMembreByEquipe(teamId);
		System.out.println("getEmployee, result is ==> " + members);	
			ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String employeeStr = mapper.writeValueAsString(members);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(employeeStr.getBytes());
		}
		catch ( IOException  e ) 
		{
			e.printStackTrace();
		}	
    }
  
}