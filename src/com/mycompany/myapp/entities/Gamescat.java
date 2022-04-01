/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author month
 */
public class Gamescat {
    private int id;
    private String nom,description;

    public Gamescat() {
    }

    public Gamescat(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Gamescat(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gamescat(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }


    
}
