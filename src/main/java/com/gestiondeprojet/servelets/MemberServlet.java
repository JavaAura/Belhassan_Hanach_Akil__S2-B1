package com.gestiondeprojet.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondeprojet.Enteties.Member;
import com.gestiondeprojet.Enteties.enums.Role;
import com.gestiondeprojet.service.MemberService;



public class MemberServlet extends HttpServlet{

	 
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
	            case "edit":
	                showEditForm(request, response);
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
	    	  int equipeId = Integer.parseInt(request.getParameter("equipeId"));
	          int page = Integer.parseInt(request.getParameter("page"));
	          int pageSize = 5;

	          List<Member> membres = memberService.getMembresByEquipe(equipeId, page, pageSize);
	          int totalMembres = memberService.getTotalMembresCountByEquipe(equipeId);
	          int totalPages = (int) Math.ceil((double) totalMembres / pageSize);

	          request.setAttribute("membres", membres);
	          request.setAttribute("totalPages", totalPages);
	          request.setAttribute("currentPage", page);
	          request.setAttribute("equipeId", equipeId);
	          System.out.println(membres);
              request.getRequestDispatcher("/pages/member.jsp").forward(request, response);
	    }

	    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	  String nom = request.getParameter("nom");
	          String prenom = request.getParameter("prenom");
	          String email = request.getParameter("email");
	          Role role = Role.valueOf(request.getParameter("role"));

	          Member newMembre = new Member(nom, prenom, email, role);
	          memberService.addMember(newMembre);
	          response.sendRedirect(request.getContextPath() + "/MemberServlet?action=list");
	    }

	    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Member member = memberService.getMemberById(id);
	        request.setAttribute("member", member);
	        request.getRequestDispatcher("/pages/member.jsp").forward(request, response);
	    }

	    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String firstName = request.getParameter("prenom");
	        String lastName = request.getParameter("nom");
	        String email = request.getParameter("email");
	        Role role = Role.valueOf(request.getParameter("role"));

	        Member member = new Member();
	        member.setId(id);
	        member.setPrenom(firstName);
	        member.setNom(lastName);
	        member.setEmail(email);
	        member.setRole(role);

	        memberService.updateMember(member);
	        response.sendRedirect("MemberServlet?action=list");
	    }

	    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        memberService.deleteMember(id);
	        response.sendRedirect("MemberServlet?action=list");
	    }
	}