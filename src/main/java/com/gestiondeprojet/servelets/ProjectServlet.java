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

import com.gestiondeprojet.Enteties.Projet;
import com.gestiondeprojet.Enteties.enums.etatProjet;
import com.gestiondeprojet.service.ProjetService;

public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjetService projetService;

    public void init() throws ServletException {
        super.init();
        if (projetService == null) {
            projetService = new ProjetService();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            if (action.equals("/")) {
                response.sendRedirect(request.getContextPath() + "/projects");
            } else {
                listProjects(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo == null) {
                listProjects(request, response);
                return;
            }
            
            switch (pathInfo) {
                case "/insert":
                    insertProject(request, response);
                    break;
                case "/update":
                    updateProject(request, response);
                    break;
                case "/delete":
                    deleteProject(request, response);
                    break;
                case "/search":
                	saerchProject(request, response);
                	break;
             
                default:
                    listProjects(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Une erreur s'est produite: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/projects");
        }
    }
   

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Projet> projectsList = projetService.getAllProjects();
        request.setAttribute("listProjects", projectsList);
        System.out.println(projectsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/projects.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                String nom = request.getParameter("name");
                String description = request.getParameter("description");
                String dateDebutParam = request.getParameter("dateDebut");
                String dateFinParam = request.getParameter("dateFin");
                String etatProjetParam = request.getParameter("etatProjet");

                LocalDate dateDebut = LocalDate.parse(dateDebutParam);
                LocalDate dateFin = LocalDate.parse(dateFinParam);
                etatProjet etat_projet = etatProjet.valueOf(etatProjetParam);

                Projet projet = new Projet(nom, description, dateDebut, dateFin, etat_projet);
                projetService.createProject(projet);

                request.setAttribute("successMessage", "Le projet a été ajouté avec succès!");
                response.sendRedirect(request.getContextPath() + "/projects");
                
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Erreur lors de l'ajout du projet: " + e.getMessage());
                request.getRequestDispatcher("/pages/projects.jsp").forward(request, response);
            }
        }

    private void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("name");
            String description = request.getParameter("description");
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
            etatProjet etatprojet = etatProjet.valueOf(request.getParameter("etatProjet"));

            Projet projet = new Projet();
            projet.setId(id);
            projet.setNom(nom);
            projet.setDescription(description);
            projet.setDateDebut(dateDebut);
            projet.setDateFin(dateFin);
            projet.setEtatProjet(etatprojet);

            boolean updated = projetService.updateProject(projet);
            
            if (updated) {
                request.getSession().setAttribute("successMessage", "Projet mis à jour avec succès!");
            } else {
                request.getSession().setAttribute("errorMessage", "Projet non trouvé!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Erreur lors de la mise à jour: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/projects");
    }


    private void deleteProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String idParam = request.getParameter("id");
            
            int id = Integer.parseInt(idParam);
            projetService.deleteProject(id);
            
            request.getSession().setAttribute("successMessage", "Project deleted successfully");
            
            response.sendRedirect(request.getContextPath() + "/projects");

    }
        
    private void saerchProject (HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
    	String searchTerm = request.getParameter("projectName");
    	List<Projet> searchResults = null;
    	if(searchTerm != null && !searchTerm.isEmpty()) {
    		searchResults = projetService.searchProjects(searchTerm);
    		request.setAttribute("projectName", searchTerm);
    	}else {
    		projetService.getAllProjects();
    	}
    	request.setAttribute("listProjects", searchResults);
        request.getRequestDispatcher("/pages/projects.jsp").forward(request, response);
    	
    }
}