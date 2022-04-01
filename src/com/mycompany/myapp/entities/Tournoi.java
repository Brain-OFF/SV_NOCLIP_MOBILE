/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author bhk
 */
public class Tournoi implements Comparable<Tournoi>{

    public Tournoi(String name) {
        this.name = name;
    }

    public Tournoi(String string, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateT() {
        return Date;
    }

    public String getCathegorie() {
        return cathegorie;
    }

    public Tournoi() {
    }

    public String getDiscription() {
        return Discription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateT(String dateT) {
        this.Date = dateT;
    }

    public void setCathegorie(String cathegorie) {
        this.cathegorie = cathegorie;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", name=" + name + ", dateT=" + Date + ", cathegorie=" + cathegorie + ", Discription=" + Discription + '}';
    }

    public Tournoi(int id, String name, String dateT, String cathegorie, String Discription) {
        this.id = id;
        this.name = name;
        this.Date = dateT;
        this.cathegorie = cathegorie;
        this.Discription = Discription;
    }

    public Tournoi(String name, String Date, String cathegorie, String Discription) {
        this.name = name;
        this.Date = Date;
        this.cathegorie = cathegorie;
        this.Discription = Discription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.Date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tournoi other = (Tournoi) obj;
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        return true;
    }
   
        private int id;
        private String name;
        String Date;
        private String cathegorie;
        private String Discription;

    @Override
    public int compareTo(Tournoi o) {
return name.compareTo(o.getDateT());    }
    
    
}
