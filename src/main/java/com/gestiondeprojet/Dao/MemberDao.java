package com.gestiondeprojet.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestiondeprojet.Enteties.Member;
import com.gestiondeprojet.Enteties.Equipe;
import com.gestiondeprojet.Enteties.enums.Role;
import com.gestiondeprojet.db.DBConnection;

public class MemberDao {

	 private Connection getConnection() throws SQLException {
	        return DBConnection.getInstance().getConnection();
	    }

	    // Assuming you have an EquipeDao to fetch Equipe objects
	    private EquipeDao equipeDao = new EquipeDao();

	    public List<Member> getAllMembers() {
	        String query = "SELECT * FROM membre";
	        List<Member> members = new ArrayList<>();
	        
	        try (Connection conn = getConnection()) {
	            if (conn == null) {
	                throw new SQLException("Failed to obtain a database connection.");
	            }
	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                ResultSet rs = pstmt.executeQuery();
	                
	                while (rs.next()) {
	                    Member member = new Member();
	                    member.setId(rs.getInt("id"));
	                    member.setPrenom(rs.getString("prenom"));
	                    member.setNom(rs.getString("nom"));
	                    member.setEmail(rs.getString("email"));
	                    member.setRole(Role.valueOf(rs.getString("role")));

	                    // Fetch the corresponding Equipe object
	                    int equipeId = rs.getInt("equipeId");
	                    Equipe equipe = equipeDao.getEquipeById(equipeId);
	                    member.setEquipe(equipe);

	                    members.add(member);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return members;
	    }

	    public Member getMemberById(int id) {
	        String query = "SELECT * FROM membre WHERE id = ?";
	        Member member = null;
	        
	        try (Connection conn = getConnection()) {
	            if (conn == null) {
	                throw new SQLException("Failed to obtain a database connection.");
	            }
	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                pstmt.setInt(1, id);
	                ResultSet rs = pstmt.executeQuery();
	                
	                if (rs.next()) {
	                    member = new Member();
	                    member.setId(rs.getInt("id"));
	                    member.setPrenom(rs.getString("prenom"));
	                    member.setNom(rs.getString("nom"));
	                    member.setEmail(rs.getString("email"));
	                    member.setRole(Role.valueOf(rs.getString("role")));

	                    // Fetch the corresponding Equipe object
	                    int equipeId = rs.getInt("equipeId");
	                    Equipe equipe = equipeDao.getEquipeById(equipeId);
	                    member.setEquipe(equipe);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return member;
	    }

	    public void addMember(Member member) {
	        String query = "INSERT INTO membre (prenom, nom, email, role, equipeId) VALUES (?, ?, ?, ?, ?)";
	        
	        try (Connection conn = getConnection()) {
	            if (conn == null) {
	                throw new SQLException("Failed to obtain a database connection.");
	            }
	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                pstmt.setString(1, member.getPrenom());
	                pstmt.setString(2, member.getNom());
	                pstmt.setString(3, member.getEmail());
	                pstmt.setString(4, member.getRole().toString());
	                pstmt.setInt(5, member.getEquipe().getId()); // Change to get the equipeId
	                pstmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateMember(Member member) {
	        String query = "UPDATE membre SET prenom = ?, nom = ?, email = ?, role = ?, equipeId = ? WHERE id = ?";
	        
	        try (Connection conn = getConnection()) {
	            if (conn == null) {
	                throw new SQLException("Failed to obtain a database connection.");
	            }
	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                pstmt.setString(1, member.getPrenom());
	                pstmt.setString(2, member.getNom());
	                pstmt.setString(3, member.getEmail());
	                pstmt.setString(4, member.getRole().toString());
	                pstmt.setInt(5, member.getEquipe().getId());
	                pstmt.setInt(6, member.getId());
	                pstmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteMember(int id) {
	        String query = "DELETE FROM membre WHERE id = ?";
	        
	        try (Connection conn = getConnection()) {
	            if (conn == null) {
	                throw new SQLException("Failed to obtain a database connection.");
	            }
	            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	                pstmt.setInt(1, id);
	                pstmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

    // Using pagination
    public List<Member> getMembresByEquipe(int equipeId, int page, int pageSize) {
        List<Member> membres = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM membre WHERE equipeId = ? LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, equipeId);
            stmt.setInt(2, pageSize);
            stmt.setInt(3, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Member membre = new Member();
                membre.setId(rs.getInt("id"));
                membre.setNom(rs.getString("nom"));
                membre.setRole(Role.valueOf(rs.getString("role")));
                membres.add(membre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membres;
    }

    
    public List<Member> getMembresByEquipeId(int equipeId) {
        List<Member> membres = new ArrayList<>();
        String query = "SELECT * FROM membre WHERE equipeId = ?";
        
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setInt(1, equipeId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String roleString = rs.getString("role");
                Role role = Role.valueOf(roleString);
                
                
                Member membre = new Member();
                membre.setId(rs.getInt("id"));
                membre.setPrenom(rs.getString("prenom"));
                membre.setNom(rs.getString("nom"));
                membre.setEmail(rs.getString("email"));
                membre.setRole(role);
                
               
                Equipe equipe = equipeDao.getEquipeById(equipeId);
                membre.setEquipe(equipe);
                
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return membres;
    }
    
    public List<Member> getMembreByEquipe(int equipeId) {
        List<Member> membres = new ArrayList<>();
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM membre WHERE equipeId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, equipeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Member membre = new Member();
                membre.setId(rs.getInt("id"));
                membre.setNom(rs.getString("nom"));
                membre.setRole(Role.valueOf(rs.getString("role")));
                membres.add(membre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membres;
    }

}
