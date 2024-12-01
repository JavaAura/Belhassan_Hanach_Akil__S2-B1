package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Enteties.Equipe;
import com.gestiondeprojet.Enteties.Member;
import com.gestiondeprojet.service.EquipeService;
import com.gestiondeprojet.service.MemberService;

public class EquipeServlet extends HttpServlet {
	
	 private EquipeService equipeService = new EquipeService();
	 private MemberService memberService = new MemberService();

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("add".equals(action)) {
	            addEquipe(request, response);
	        } else if ("update".equals(action)) {
	            updateEquipe(request, response);
	        }
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("list".equals(action)) {
	            listEquipes(request, response);
	        } else if ("delete".equals(action)) {
	            deleteEquipe(request, response);
	        }
	    }

	    private void addEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String nom = request.getParameter("nom");
	        Equipe equipe = new Equipe();
	        equipe.setNom(nom);
	        equipeService.createEquipe(equipe);
	        response.sendRedirect("EquipeServlet?action=list");
	    }

	    private void updateEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String nom = request.getParameter("nom");

	        Equipe equipe = new Equipe();
	        equipe.setId(id);
	        equipe.setNom(nom);
	        equipeService.modifyEquipe(equipe);

	        response.sendRedirect("EquipeServlet?action=list");
	    }

	    private void listEquipes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Equipe> equipes = equipeService.fetchAllEquipes();
	        for (Equipe equipe : equipes) {
	            List<Member> membres = memberService.getMembresByEquipe(equipe.getId());
	            equipe.setMembres(membres);
	        }
	        System.out.println(equipes);
	        request.setAttribute("equipes", equipes);
	        request.getRequestDispatcher("/pages/equipe.jsp").forward(request, response);
	    }

	    private void deleteEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        equipeService.removeEquipe(id);
	        response.sendRedirect("EquipeServlet?action=list");
	    }

}