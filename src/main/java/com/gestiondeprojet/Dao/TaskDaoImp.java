package com.gestiondeprojet.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestiondeprojet.Enteties.Task;
import com.gestiondeprojet.Enteties.enums.Priorite;
import com.gestiondeprojet.Enteties.enums.Statut;
import com.gestiondeprojet.db.DBConnection;

public class TaskDaoImp implements TaskDao {
	private Connection getConnection() {
		return DBConnection.getInstance().getConnection();
	}
	
	  @Override
	  public void addTask(Task tache) throws SQLException {
	        String sql = "INSERT INTO tache (titre, description, dateCreation, dateEcheance, priorite, statut, membreId, projetId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, tache.getTitre());
	            ps.setString(2, tache.getDescription());
	            ps.setDate(3, java.sql.Date.valueOf(tache.getDateCreation()));
	            ps.setDate(4, java.sql.Date.valueOf(tache.getDateEcheance()));
	            ps.setString(5, tache.getPriorite().name());
	            ps.setString(6, tache.getStatut().name());
	            ps.setInt(7, tache.getMembreId());
	            ps.setInt(8, tache.getProjetId());
	            
	            ps.executeUpdate();
	        }
	    }
	  @Override
	  public void updateTask(Task tache) throws SQLException {
	        String sql = "UPDATE tache SET titre = ?, description = ?, dateEcheance = ?, priorite = ?, statut = ?, membreId = ?, projetId = ? WHERE id = ?";
	        
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, tache.getTitre());
	            ps.setString(2, tache.getDescription());
	            ps.setDate(3, java.sql.Date.valueOf(tache.getDateEcheance()));
	            ps.setString(4, tache.getPriorite().name());
	            ps.setString(5, tache.getStatut().name());
	            ps.setInt(6, tache.getMembreId());
	            ps.setInt(7, tache.getProjetId());
	            ps.setInt(8, tache.getId());
	            
	            ps.executeUpdate();
	        }
	    }

	@Override
	public List<Task> getAllTasks() throws SQLException {
		// TODO Auto-generated method stub
		 String sql = "SELECT * FROM tache";
	        List<Task> taches = new ArrayList<>();
	        
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            
	            while (rs.next()) {
	            	Task tache = mapResultSetToTache(rs);
	                taches.add(tache);
	            }
	        }
	        return taches;
		
	}
	
	 private Task mapResultSetToTache(ResultSet rs) throws SQLException {
	        Task tache = new Task();
	        tache.setId(rs.getInt("id"));
	        tache.setTitre(rs.getString("titre"));
	        tache.setDescription(rs.getString("description"));
	        tache.setDateEcheance(rs.getDate("dateEcheance").toLocalDate());
	        tache.setPriorite(Priorite.valueOf(rs.getString("priorite")));
	        tache.setStatut(Statut.valueOf(rs.getString("statut")));
	        tache.setMembreId(rs.getInt("membreId"));
	        tache.setProjetId(rs.getInt("projetId"));
	        
	        return tache;
	    }
	  
	  

}
