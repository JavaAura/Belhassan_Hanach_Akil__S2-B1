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
		try {
			return DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	 @Override
	    public Task getTaskById(int id) throws SQLException {
	        String sql = "SELECT * FROM tache WHERE id = ?";
	        Task tache = null;

	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setInt(1, id);
	            
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    tache = mapResultSetToTache(rs);
	                }
	            }
	        }
	        return tache;
	    }
	 
	 @Override
	 public void deleteTaskById(int id) throws SQLException {
	     String sql = "DELETE FROM tache WHERE id = ?";

	     try (Connection conn = getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        
	         ps.setInt(1, id);
	         
	         
	         int rowsAffected = ps.executeUpdate();
	         if (rowsAffected > 0) {
	             System.out.println("Task with ID " + id + " deleted successfully.");
	         } else {
	             System.out.println("No task found with ID " + id);
	         }
	     }
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
	 @Override
	 public List<Task> getTasksPaginated(int page, int pageSize) throws SQLException {
		    int offset = (page - 1) * pageSize;
		    String sql = "SELECT * FROM tache LIMIT ? OFFSET ?";
		    
		    try (Connection conn = getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setInt(1, pageSize);
		        ps.setInt(2, offset);

		        try (ResultSet rs = ps.executeQuery()) {
		            List<Task> tasks = new ArrayList<>();
		            while (rs.next()) {
		            	Task tache = mapResultSetToTache(rs);
		            	tasks.add(tache);
		            }
		            return tasks;
		        }
		    }
		}
	 @Override
		public int getTotalTaskCount() throws SQLException {
		    String sql = "SELECT COUNT(*) FROM tache";
		    try (Connection conn = getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {
		        if (rs.next()) {
		            return rs.getInt(1);
		        }
		    }
		    return 0;
		}


}