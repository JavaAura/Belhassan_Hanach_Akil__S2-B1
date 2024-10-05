package com.gestiondeprojet.Enteties;

import com.gestiondeprojet.Enteties.enums.Role;

public class Member {
 	private int id;
    private String prenom;
    private String nom;
    private String email;
    private Role role;
    private Equipe equipe;
    
   
	public Member(int id, String firstName, String lastName, String email, Role role , Equipe equipe) {
		super();
		this.id = id;
		this.prenom = firstName;
		this.nom = lastName;
		this.email = email;
		this.role = role;
		this.equipe = equipe;
	}
    
	public Member(String firstName, String lastName, String email, Role role , Equipe equipe) {
		super();
		this.prenom = firstName;
		this.nom = lastName;
		this.email = email;
		this.role = role;
		this.equipe = equipe;
	}
    
	public Member() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String firstName) {
		this.prenom = firstName;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String lastName) {
		this.nom = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	 
	public Equipe getEquipe() {
	   return equipe;
	}

	public void setEquipe(Equipe equipe) {
	   this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", firstName=" + prenom + ", lastName=" + nom + ", email=" + email
				+ ", role=" + role + " equipe = " + equipe +" ]";
	}
	
	
}
