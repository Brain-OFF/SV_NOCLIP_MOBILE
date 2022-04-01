/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author bhk
 */
public class CommandeMenuForm extends Form{
Form current;
    public CommandeMenuForm(Form previous,User U) {
        
        User Current_user=U;
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
       add(new Label("Choisir une option"));
        Button btnAddTask = new Button("Passer une Commande");
        Button btnListTasks = new Button("Gestion des Commandes");
        Button btnpanier = new Button("votre panier");
        Button btnAddProduit = new Button("Ajouter produit");
        Button btnListProduit = new Button("Gestion des produit");
        btnListTasks.addActionListener(e -> new ListCommande(current).show());
        
        addAll(btnAddTask, btnListTasks,btnListProduit,btnAddProduit,btnpanier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
