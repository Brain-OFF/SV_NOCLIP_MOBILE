/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;
import com.mycompany.myapp.entities.Tournoi;
/**
 *
 * @author Taha
 */
public class InscriptionT {
    private int id;
    private String user_name;
    private String email;
    private boolean etat;
    private String Rank;
    private int  tournoi;
    
    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEtat() {
        return etat;
    }

    public int getTournoi() {
        return tournoi;
    }

    public void setTournoi(int tournoi) {
        this.tournoi = tournoi;
    }

    
    public String getRank() {
        return Rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void setRank(String Rank) {
        this.Rank = Rank;
    }

    @Override
    public String toString() {
        return "InscriptionT{" + "id=" + id + ", user_name=" + user_name + ", email=" + email + ", etat=" + etat + ", Rank=" + Rank + '}';
    }

    public InscriptionT(String user_name, String email, boolean etat, String Rank) {
       
        this.user_name = user_name;
        this.email = email;
        this.etat = etat;
        this.Rank = Rank;
    }
    
    
}
