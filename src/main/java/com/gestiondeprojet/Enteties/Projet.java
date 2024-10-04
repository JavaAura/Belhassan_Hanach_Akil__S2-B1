package com.gestiondeprojet.Enteties;

import java.time.LocalDate;

import com.gestiondeprojet.Enteties.enums.etatProjet;

public class Projet {
private int id;
private String nom;
private String description;
private LocalDate dateDebut;
private LocalDate dateFin;
private etatProjet etatProjet;

	public Projet(int id, String nom, String description, LocalDate dateDebut, LocalDate dateFin,etatProjet etatProjet) {
	        this.id = id;
	        this.nom = nom;
	        this.description = description;
	        this.dateDebut = dateDebut;
	        this.dateFin = dateFin;
	        this.etatProjet = etatProjet;
	}
	
	public Projet(String nom, String description, LocalDate dateDebut, LocalDate dateFin,etatProjet etatProjet) {
	    this.nom = nom;
	    this.description = description;
	    this.dateDebut = dateDebut;
	    this.dateFin = dateFin;
	    this.etatProjet = etatProjet;
	}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    
    public etatProjet getEtatProjet() {
		return etatProjet;
	}

	public void setEtatProjet(etatProjet etatProjet) {
		this.etatProjet = etatProjet;
	}

	@Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", etat projet = "+etatProjet+
                '}';
    }
}
