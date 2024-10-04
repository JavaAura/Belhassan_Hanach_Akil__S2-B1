package com.gestiondeprojet.Enteties;

import java.time.LocalDate;

import com.gestiondeprojet.Enteties.enums.Priorite;
import com.gestiondeprojet.Enteties.enums.Statut;

public class Task {
	private int id;
    private String titre;
    private String description;
    private Priorite priorite;
    private Statut statut;
    private LocalDate dateCreation;
    private LocalDate dateEcheance;
    private int membreId;
    private int projetId;
    public Task() {}
    
    public Task(String titre, String description, Priorite priorite, Statut statut, LocalDate dateEcheance,int membreId,int projetId) {
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.dateCreation = LocalDate.now(); 
        this.dateEcheance = dateEcheance;
        this.membreId=membreId;
        this.projetId=projetId;
    }

   
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getProjetId() {
		return projetId;
	}

	public void setProjetId(int projetId) {
		this.projetId = projetId;
	}

	public int getMembreId() {
		return membreId;
	}

	public void setMembreId(int membreId) {
		this.membreId = membreId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", titre=" + titre + ", description=" + description + ", priorite=" + priorite
				+ ", statut=" + statut + ", dateCreation=" + dateCreation + ", dateEcheance=" + dateEcheance
				+ ", membreId=" + membreId + ", projetId=" + projetId + "]";
	}
	}

