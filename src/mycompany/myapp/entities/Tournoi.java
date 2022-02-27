/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author Taha
 */
public class Tournoi {
        private int id;
        private String name;
        Date dateT;
        private String cathegorie;
        private String Discription;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateT(Date dateT) {
        this.dateT = dateT;
    }

    public void setCathegorie(String cathegorie) {
        this.cathegorie = cathegorie;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateT() {
        return dateT;
    }

    public String getCathegorie() {
        return cathegorie;
    }

    public String getDiscription() {
        return Discription;
    }

    public Tournoi() {
    }

    public Tournoi(int id, String name, Date dateT, String cathegorie, String Discription) {
        this.id = id;
        this.name = name;
        this.dateT = dateT;
        this.cathegorie = cathegorie;
        this.Discription = Discription;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", name=" + name + ", dateT=" + dateT + ", cathegorie=" + cathegorie + ", Discription=" + Discription + '}';
    }

    public Tournoi(String name, String cathegorie) {
        this.name = name;
        this.cathegorie = cathegorie;
    }

    
}
