package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Enteties.Equipe;
import com.gestiondeprojet.Enteties.Member;
import com.gestiondeprojet.Enteties.enums.Role;
import com.gestiondeprojet.service.EquipeService;
import com.gestiondeprojet.service.MemberService;
import com.gestiondeprojet.Dao.EquipeDao;


public class MemberServlet extends HttpServlet{

		private EquipeDao equipeDao = new EquipeDao();	
 	
	    private MemberService memberService = new MemberService();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        if (action == null) {
	            action = "list";
	        }

	        switch (action) {
	            case "list":
	                listMembers(request, response);
	                break;
	            case "delete":
	                deleteMember(request, response);
	                break;
	            default:
	                listMembers(request, response);
	                break;
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        if ("add".equals(action)) {
	            addMember(request, response);
	        } else if ("update".equals(action)) {
	            updateMember(request, response);
	        }
	    }

	    private void listMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	EquipeService equipeService = new EquipeService();

	        List<Member> allMembers = memberService.getAllMembers();
	        List<Equipe> equipes = equipeService.fetchAllEquipes();
	        
	        // Pagination logic
	        int pageSize = 3;
	        int totalMembers = allMembers.size();
	        int noOfPages = (int) Math.ceil((double) totalMembers / pageSize);
	        
	        
	        int currentPage = 1;
	        String pageParam = request.getParameter("page");
	        if (pageParam != null && !pageParam.isEmpty()) {
	            currentPage = Integer.parseInt(pageParam);
	        }

	        
	        int start = (currentPage - 1) * pageSize;
	        int end = Math.min(start + pageSize, totalMembers);
	        
	        
	        List<Member> membersForCurrentPage = allMembers.subList(start, end);

	        request.setAttribute("equipes", equipes);
	        request.setAttribute("membres", membersForCurrentPage);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", currentPage);
	        request.getRequestDispatcher("/pages/member.jsp").forward(request, response);

	    }


	    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
	    	 String nom = request.getParameter("nom");
	    	    String prenom = request.getParameter("prenom");
	    	    String email = request.getParameter("email");
	    	    Role role = Role.valueOf(request.getParameter("role"));
	    	    int equipeId = Integer.parseInt(request.getParameter("equipeId"));
	    	    
	    	    Equipe equipe = equipeDao.getEquipeById(equipeId);
	    	
	    	    Member newMembre = new Member(nom, prenom, email, role, equipe);
	    	    memberService.addMember(newMembre);

	    	    response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");	    
	    }


	    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	 int id = Integer.parseInt(request.getParameter("id"));
	    	    String firstName = request.getParameter("prenom");
	    	    String lastName = request.getParameter("nom");
	    	    String email = request.getParameter("email");
	    	    Role role = Role.valueOf(request.getParameter("role"));
	    	    int equipeId = Integer.parseInt(request.getParameter("equipeId"));
	    	    
	    	   
	    	    Equipe equipe = equipeDao.getEquipeById(equipeId);

	    	    
	    	    Member member = new Member(firstName, lastName, email, role, equipe);
	    	    member.setId(id);

	    	    memberService.updateMember(member);
	    	    response.sendRedirect("MemberServlet?action=list");
	    }

	    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        memberService.deleteMember(id);
	        response.sendRedirect("MemberServlet?action=list");
	    }
	}