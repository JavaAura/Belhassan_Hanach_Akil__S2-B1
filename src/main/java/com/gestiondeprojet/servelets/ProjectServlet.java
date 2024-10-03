package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Dao.TaskDaoImp;
import com.gestiondeprojet.Enteties.Projet;
import com.gestiondeprojet.Enteties.enums.etatProjet;
import com.gestiondeprojet.service.ProjetService;

public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjetService projetService;

    public ProjectServlet() {
    	super();
    }
   

    @Override
    public void init(ServletConfig config) throws ServletException {
	
    	super.init(config); projetService = new ProjetService();

    }
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
   }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String action = request.getServletPath();

	 switch (action) {
	     case "insert":
	         insertProject(request, response);
	         break;
	     case "update":
	         try {
	             updateProject(request, response);
	         } catch (SQLException e) {
	             e.printStackTrace();
	             request.setAttribute("errorMessage", "Erreur lors de la mise à jour du projet.");
	             request.getRequestDispatcher("projects.jsp").forward(request, response);
	         }
	         break;
	     case "delete":
	         try {
	             deleteProject(request, response);
	         } catch (SQLException e) {
	             e.printStackTrace();
	             request.setAttribute("errorMessage", "Erreur lors de la suppression du projet.");
	             request.getRequestDispatcher("projects.jsp").forward(request, response);
	         }
	         break;
	     default:
		try {
			listProjects(request, response);
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	         break;
	 }

    }
    
    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Projet> projectsList = projetService.getAllProjects();
        System.out.println(projectsList);
        request.setAttribute("listProjects", projectsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/projects.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertProject(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String nom = request.getParameter("name");
            String description = request.getParameter("description");
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
            etatProjet etat_projet = etatProjet.valueOf(request.getParameter("etatProjet"));

            Projet projet = new Projet(nom, description, dateDebut, dateFin, etat_projet);
            projetService.createProject(projet);            

            request.setAttribute("successMessage", "Le projet a été ajouté avec succès !");
            request.getRequestDispatcher("projects.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de l'ajout du projet.");
        }
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("name");
        String description = request.getParameter("description");
        LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
        LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
        etatProjet etat_projet = etatProjet.valueOf(request.getParameter("etatProjet"));

        Projet projet = new Projet(id, nom, description, dateDebut, dateFin, etat_projet);
        projetService.updateProject(projet);
        response.sendRedirect("projects.jsp");
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        projetService.deleteProject(id);
        response.sendRedirect("projects.jsp");
    }
}