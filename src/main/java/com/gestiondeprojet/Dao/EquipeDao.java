package com.gestiondeprojet.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gestiondeprojet.Enteties.Equipe;
import com.gestiondeprojet.db.DBConnection;



public class EquipeDao {

	public void addEquipe(Equipe equipe) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO equipe (nom) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, equipe.getNom());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public void updateEquipe(Equipe equipe) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "UPDATE equipe SET nom = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, equipe.getNom());
            stmt.setInt(2, equipe.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public void deleteEquipe(int id) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "DELETE FROM equipe WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

  
        public List<Equipe> getAllEquipes() {
            List<Equipe> equipes = new ArrayList<>();
            try (Connection conn = DBConnection.getInstance().getConnection()) {
                String query = "SELECT * FROM equipe";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNom(rs.getString("nom"));
                equipes.add(equipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipes;
    }

    
    public Equipe getEquipeById(int id) {
        Equipe equipe = null;
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM equipe WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNom(rs.getString("nom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return equipe;
        
        }
    
}