package com.gestiondeprojet.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gestiondeprojet.Enteties.Projet;
import com.gestiondeprojet.Enteties.enums.etatProjet;
import com.gestiondeprojet.db.DBConnection;
import com.mysql.cj.xdevapi.PreparableStatement;

public class ProjetDao {
	
	private Connection getConnection() {
		return DBConnection.getInstance().getConnection();
	}
	
	public void insertProject(Projet projet) {
		String query = "INSERT INTO projet "
				+ "(nom,description,dateDebut,dateFin,etatProjet) VALUES"
				+ "(?,?,?,?,?) ";
		try(Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, projet.getNom());
			stmt.setString(2, projet.getDescription());
			stmt.setObject(3, projet.getDateDebut());
			stmt.setObject(4, projet.getDateFin());
			stmt.setObject(5, projet.getEtatProjet(),java.sql.Types.OTHER);
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateProject (Projet projet) {
		String query = "UPDATE projet SET nom = ?"
				+ "description = ? ,dateDebut = ?"
				+ "dateFin = ? ,etatProjet = ? WHERE id = ?";
		try (Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query)){
			stmt.setString(1, projet.getNom());
			stmt.setString(2, projet.getDescription());
			stmt.setObject(3, projet.getDateDebut());
			stmt.setObject(4, projet.getDateFin());
			stmt.setObject(5, projet.getEtatProjet(),java.sql.Types.OTHER);
			stmt.setInt(6, projet.getId());
			int rowsUpdated = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
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
				Projet projet = new Projet(rs.getString("nom"),
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
	
	
	
	
}
