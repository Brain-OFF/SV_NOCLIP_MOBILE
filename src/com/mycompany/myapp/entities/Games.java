/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author month
 */
public class Games {
    private int id,prix,cat;
    private String name,descreption,img;

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    public Games(int id, int prix, String name, String descreption, String img) {
        this.id = id;
        this.prix = prix;
        this.name = name;
        this.descreption = descreption;
        this.img = img;
    }
public Games() {

    }
@Override
    public String toString() {
        return "game{" + "id=" + id +  ", name=" + name + ", desc=" + descreption + ",prix=" + prix +  ",img=" + img +"\n";
    }

   public Games(  String name, String descreption,int prix, String img) {
        
        this.prix = prix;
        this.name = name;        this.img = img;

        this.descreption = descreption;
    }

}
