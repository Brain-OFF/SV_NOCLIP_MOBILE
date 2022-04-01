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
public class TournamentMenuForm extends Form{
Form current;
    public TournamentMenuForm(Form previous,User U) {
        
        User Current_user=U;
        current=this; //Back 
        setTitle("Home User :"+U.getUsername());
        System.out.println(U);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnListTasks = new Button("List Tournoi");
        Button btnaddT = new Button("add Tournoi");
        Button btnaddTb = new Button("gestion Tournoi");
        Button btnaddins = new Button("gestion inscription");
        Button btS= new Button("SEARCH TOURNOI BY NAME ");        
        btnListTasks.addActionListener(e-> new ListTasksForm(current,Current_user).show());
        btnaddT.addActionListener(e-> new AddTaskForm(current).show());
        btnaddTb.addActionListener(e-> new ListTB(current,Current_user).show());
        btnaddins.addActionListener(e-> new Listins(current).show());
        Button btrie=new Button("TRi Tournoi par date");
        btrie.addActionListener(e-> new Tritournoi(current).show());
        btS.addActionListener(e-> new Search(current).show());
        if (U.getStatus()=="Admin")
        addAll(btnListTasks,btnaddT,btnaddTb,btnaddins,btS,btrie);
        else
            addAll(btnListTasks,btS,btrie);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
