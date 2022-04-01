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
import com.mycompany.myapp.entities.Cart;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author bhk
 */
public class GamesMenuForm extends Form{
Form current;
    public GamesMenuForm(Form previous,User U,Cart Panier) {
        
        User Current_user=U;
        current=this; //Back 
       current = this; //Récupération de l'interface(Form) en cours
        setTitle("Games");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddcat = new Button("Add cat");
        Button btnListgame = new Button("List games");
        Button btnListcat = new Button("List cat");
         Button btnfavs = new Button("List Favs");
        btnAddcat.addActionListener(e -> new Addgamescat(current).show());
        btnListgame.addActionListener(e -> new Listgames(current,U).show());
        btnListcat.addActionListener(e -> new Listcat(current,U,Panier).show());
        btnfavs.addActionListener(e -> new ListgamesFav(current,U).show());
        if (U.getStatus().compareTo("admin")==0)
        addAll(btnAddcat,btnListgame,btnListcat,btnfavs);
        else 
            addAll(btnListgame,btnListcat,btnfavs);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
