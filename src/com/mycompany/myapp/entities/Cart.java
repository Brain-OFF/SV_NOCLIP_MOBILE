/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Cart {

    public static Cart instance;

    private final ArrayList<Games> c;

    public Cart() {
        c = new ArrayList<Games>();

    }

    public ArrayList<Games> getCartList() {
        return c;
    }

    public void AddProduct(Games e) {
        this.c.add(e);
    }

    public void RemoveProduct(Games e) {
        
       int productid = e.getId();
        for(int i=0 ; i<this.c.size();i++){
            Games product = this.c.get(i);
            if(product.getName().equals(e.getName())){
                this.c.remove(i);
            }
        }
    }
    public void RemoveAll(){
    this.c.clear();
    }

    public ArrayList<Games> getC() {
        return c;
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void cleanCartSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "c=" + c
                + '}';
    }
    public double total(){
        double total=0;
     for(Games product : this.c){
                    total = total + (product.getPrix());
                }
    return total;
    }
    
}
