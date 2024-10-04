package com.gestiondeprojet.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gestiondeprojet.Enteties.Projet;
import com.gestiondeprojet.Enteties.enums.etatProjet;
import com.gestiondeprojet.db.DBConnection;
import com.mysql.cj.xdevapi.Statement;

public class ProjetDao {
	
	private Connection getConnection() {
		try {
			return DBConnection.getInstance().getConnection();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	public void insertProject(Projet projet) {
		String query = "INSERT INTO projet "
				+ "(nom,description,dateDebut,dateFin,etatProjet) VALUES"
				+ "(?,?,?,?,?) ";
		try(Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, projet.getNom());
			stmt.setString(2, projet.getDescription());
			stmt.setDate(3, java.sql.Date.valueOf(projet.getDateDebut()));
			stmt.setDate(4, java.sql.Date.valueOf(projet.getDateFin()));
			stmt.setString(5, projet.getEtatProjet().name());
			stmt.executeUpdate();
		    con.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateProject (Projet projet) {
		String query = "UPDATE projet SET nom = ?,"
				+ "description = ? ,"
				+ "dateDebut = ?,"
				+ "dateFin = ? ,"
				+ "etatProjet = ?"
				+ " WHERE id = ?";
		try (Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query)){
			stmt.setString(1, projet.getNom());
			stmt.setString(2, projet.getDescription());
			stmt.setObject(3, projet.getDateDebut());
			stmt.setObject(4, projet.getDateFin());
			stmt.setString(5, projet.getEtatProjet().name().toString());
			stmt.setInt(6, projet.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
	        System.err.println("Error updating project: ");
			e.printStackTrace();
		}		
	}
	
	public Optional<Projet> selectProjectById(int id) {
	    String query = "SELECT * FROM projet WHERE id = ?";
	    try (Connection con = getConnection();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String valeur = rs.getString("etatProjet");
	            etatProjet etat = null;
	            if (valeur != null) {
	                etat = etatProjet.valueOf(valeur);
	            }
	            LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
	            LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
	            Projet projet = new Projet(rs.getInt("id"),rs.getString("nom"),
	                                       rs.getString("description"),
	                                       dateDebut,
	                                       dateFin,
	                                       etat);
	            return Optional.of(projet);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return Optional.empty(); 
	}

	
	public List<Projet> selectProjects (){
		List<Projet> projets = new ArrayList();
		String query = "SELECT * FROM projet";
		try (Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String valeur = rs.getString("etatProjet");
				etatProjet etat = null;
				if (valeur!= null) {
					etat = etatProjet.valueOf(valeur);
				}
                LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
                LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
				Projet projet = new Projet(rs.getInt("id"), rs.getString("nom"),
						rs.getString("description"), dateDebut, dateFin,etat);
				projets.add(projet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projets;
	}
	
	public void deleteProject (int id) {
		String query = "DELETE FROM projet WHERE id = ?";
		try(Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query)) {
				stmt.setInt(1, id);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public List<Projet> searchProject(String Projectname){
		List<Projet> projetsResult = new ArrayList<Projet>();
		String query = " SELECT * FROM projet WHERE nom LIKE ? ";
		try(Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query)) {
				String searchName = "%"+Projectname+"%";
				stmt.setString(1,searchName);
				ResultSet rs = stmt.executeQuery();
		            while (rs.next()) {
		                Projet projet = new Projet(rs.getInt("id"),rs.getString("nom"),rs.getString("description"),
		                		rs.getObject("dateDebut", LocalDate.class),rs.getObject("dateFin", LocalDate.class),
		                		etatProjet.valueOf(rs.getString("etatProjet")));
		                projetsResult.add(projet);
		            }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return projetsResult;
	}
	
	
	
	
}
