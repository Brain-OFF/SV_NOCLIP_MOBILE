/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddGame = new Button("Add Game");
        Button btnAddcat = new Button("Add cat");
        Button btnListgame = new Button("List games");
        Button btnListcat = new Button("List cat");

        
        btnAddcat.addActionListener(e -> new Addgamescat(current).show());
        btnListgame.addActionListener(e -> new Listgames(current).show());
        btnListcat.addActionListener(e -> new Listcat(current).show());
        addAll(btnAddGame, btnAddcat,btnListgame,btnListcat);

    }

}
