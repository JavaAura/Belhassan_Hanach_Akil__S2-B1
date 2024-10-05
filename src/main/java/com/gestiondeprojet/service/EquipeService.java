package com.gestiondeprojet.service;


import java.util.List;

import com.gestiondeprojet.Dao.EquipeDao;
import com.gestiondeprojet.Enteties.Equipe;

public class EquipeService {
	
	private EquipeDao equipeDao = new EquipeDao();

    
    public void createEquipe(Equipe equipe) {
        equipeDao.addEquipe(equipe);
    }

    
    public void modifyEquipe(Equipe equipe) {
        equipeDao.updateEquipe(equipe);
    }

    
    public void removeEquipe(int id) {
        equipeDao.deleteEquipe(id);
    }

    
    public List<Equipe> fetchAllEquipes() {
        return equipeDao.getAllEquipes();
    }

    
    public Equipe fetchEquipeById(int id) {
        return equipeDao.getEquipeById(id);
    }
    public Equipe fetchEquipeByMembreId(int id) {
        return equipeDao.getEquipeByMembreId(id);
    }
   
}