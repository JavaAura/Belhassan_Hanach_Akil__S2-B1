package com.gestiondeprojet.Enteties;

import java.util.List;

public class Equipe {

    private int id;
    private String nom;
    private List<Member> membres; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Member> getMembres() {
        return membres;
    }

    public void setMembres(List<Member> membres) {
        this.membres = membres;
    }

    @Override
    public String toString() {
        return "Equipe [id=" + id + ", nom=" + nom + ", membres=" + membres + "]";
    }


}