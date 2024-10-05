package com.gestiondeprojet.service;

import java.util.List;
import java.util.Optional;

import com.gestiondeprojet.Dao.ProjetDao;
import com.gestiondeprojet.Enteties.Projet;

public class ProjetService {

    private final ProjetDao projetDao;

    public ProjetService() {
        this.projetDao = new ProjetDao();
    }

    public List<Projet> getAllProjects() {
        return projetDao.selectProjects();
    }

    public boolean createProject(Projet projet) {
        System.out.println("Insertion du projet dans la base de données: " + projet);
        try {
            projetDao.insertProject(projet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProject(Projet projet) {
        System.out.println("Service - Updating project with ID: " + projet.getId());
        
        Optional<Projet> existingProject = projetDao.selectProjectById(projet.getId());
        if (existingProject.isPresent()) {
            System.out.println("Project found, proceeding with update");
            projetDao.updateProject(projet);
            return true;
        } else {
            System.out.println("Project not found with ID: " + projet.getId());
            return false;
        }
    }

    public boolean deleteProject(int id) {
        Optional<Projet> existingProject = projetDao.selectProjectById(id);
        if (existingProject.isPresent()) {
            projetDao.deleteProject(id);
            return true;
        } else {
            return false; 
        }
    }
    
    public List<Projet> searchProjects(String searchName) {
        if (searchName == null || searchName.isEmpty()) {
            return getAllProjects(); 
        }
        return projetDao.searchProject(searchName);
    }
    
}
