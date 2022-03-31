/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;
import java.util.Date;
/**
 *
 * @author xDrais
 */
public class reservation {

private int id , coach ;
private String tempsstart , tempsend  ;
private boolean dispo;

    public reservation() {
    }


    public reservation( int coach, String tempsstart, String tempsend , boolean dispo) {
        this.coach = coach;
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;

    }
 public reservation( String tempsstart, String tempsend , boolean dispo) {
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;

    }

  @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", coach=" + coach + ", time start=" + tempsstart + ", time end=" + tempsend +  ", dispo =" + dispo + "\n";
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

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public String getTempsstart() {
        return tempsstart;
    }

    public void setTempsstart(String tempsstart) {
        this.tempsstart = tempsstart;
    }

    public String getTempsend() {
        return tempsend;
    }

    public void setTempsend(String tempsend) {
        this.tempsend = tempsend;
    }


    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }



  


  
}
