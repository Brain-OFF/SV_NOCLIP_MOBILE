/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author admin
 */
public class categorie {
    private String name;
    private int id;
    public categorie() {
    }

    public categorie(String name) {
        this.name = name;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "categorie{" + "name=" + name + '}';
    }

    public void setId(int i) {
      id=i;
    }
    public int getId()
    {
        return id;
    }
    
    
}
