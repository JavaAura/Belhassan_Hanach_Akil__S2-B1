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
        try {
            projetDao.insertProject(projet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProject(Projet projet) {
        Optional<Projet> existingProject = projetDao.selectProjectById(projet.getId());
        if (existingProject.isPresent()) {
            projetDao.updateProject(projet);
            return true;
        } else {
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
}
