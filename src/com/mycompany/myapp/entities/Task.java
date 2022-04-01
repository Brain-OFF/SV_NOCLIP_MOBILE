/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class Task {
    private int id;
    private String Text,jeux,titre;
    private String categorie;
    private String date;

    public Task(String Text, String jeux, String titre, String categorie, String date) {
        this.Text = Text;
        this.jeux = jeux;
        this.titre = titre;
        this.categorie = categorie;
        this.date = date;
    }

    public Task() {
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setJeux(String jeux) {
        this.jeux = jeux;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return Text;
    }

    public String getJeux() {
        return jeux;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDate() {
        return date;
    }

   

   
  
    
    
}
