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
public class UserMenuForm extends Form{
Form current;
    public UserMenuForm(Form previous,User U) {
        
        User Current_user=U;
        current=this; //Back 
        setTitle("Home User :"+U.getUsername());
        System.out.println(U);
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add User");
        Button btnListUsers = new Button("List User");
        Button btnListreservation = new Button("List reservation");
        btnListreservation.addActionListener(e-> new ListReservationForm(current).show());
        Button btrie=new Button("TRi Tournoi par date");
        btrie.addActionListener(e-> new Tritournoi(current).show());
        btnAddTask.addActionListener(e-> new AddUserForm(current).show());
        btnListUsers.addActionListener(e-> new ListUsersForm(current).show());
        addAll(btnListUsers,btnAddTask);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
