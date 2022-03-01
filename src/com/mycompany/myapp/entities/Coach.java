/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author xDrais
 */
public class Coach {
    private int id,rank;
    private String name,lastname,categorie;

    public Coach() {
    }

    public Coach( int rank, String name, String lastname, String categorie) {
        this.rank = rank;
        this.name = name;
        this.lastname = lastname;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", rank=" + rank + ", name=" + name + ", lastname=" + lastname + ", categorie=" + categorie + "\n";
    }
 public String idtoString ()
    {
        return String.valueOf(id);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }










}
