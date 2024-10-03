package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Dao.ProjetDao;
import com.gestiondeprojet.Enteties.Projet;
import com.gestiondeprojet.Enteties.enums.etatProjet;

/**
 * Servlet implementation class projectServlet
 */
public class projectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjetDao projetDao;
    /**
     * Default constructor. 
     */
    public projectServlet() {
       this.projetDao = new ProjetDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/new": 
			break;
		case "/insert":
			break;
		case "/edit":
			break;
		case "/update":
			break;
		case "delete" :
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response)
	}
	
	private void listProjects(HttpServletRequest request,HttpServletResponse response)
	throws SQLException, IOException,ServletException{
		List<Projet> projectsList = projetDao.selectProjects();
		request.setAttribute("listProjects", projectsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("projectsList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertProject(HttpServletRequest request,HttpServletResponse response)
	throws SQLException, IOException{
		String nom = request.getParameter("name");
		String description = request.getParameter("description");
		String dateDebutStr = request.getParameter("dateDebut");
		LocalDate dateDebut = LocalDate.parse(dateDebutStr);
		String dateFinStr = request.getParameter("dateFin");
		LocalDate dateFin = LocalDate.parse(dateFinStr);
		String etat = request.getParameter("etatProjet");
		etatProjet etat_projet = etatProjet.valueOf(etat);
		Projet projet = new Projet(nom, description, dateDebut, dateFin, etat_projet);
		projetDao.insertProject(projet);
		response.sendRedirect("list");
	}
	
	private void ipdateProject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException{
				int id = Integer.parseInt(request.getParameter("id"));
				String nom = request.getParameter("name");
				String description = request.getParameter("description");
				String dateDebutStr = request.getParameter("dateDebut");
				LocalDate dateDebut = LocalDate.parse(dateDebutStr);
				String dateFinStr = request.getParameter("dateFin");
				LocalDate dateFin = LocalDate.parse(dateFinStr);
				String etat = request.getParameter("etatProjet");
				etatProjet etat_projet = etatProjet.valueOf(etat);
				Projet projet = new Projet(nom, description, dateDebut, dateFin, etat_projet);
				projetDao.updateProject(projet);
				response.sendRedirect("list");
			}
	private void deleteProject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException{
				int id = Integer.parseInt(request.getParameter("id"));
				projetDao.deleteProject(id);
				response.sendRedirect("list");
			}
	
	
	

}
