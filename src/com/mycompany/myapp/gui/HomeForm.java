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
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Tournoi;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
    TableLayout tl = new TableLayout(3, 2);

Form current = new Form("Pixel Perfect", tl);
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnListTasks = new Button("List Tournoi");
        Button btnaddT = new Button("add Tournoi");
        Button btnaddTb = new Button("gestion Tournoi");
        Button btnaddins = new Button("gestion inscription");
        Button btS= new Button("SEARCH TOURNOI BY NAME ");

        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        btnaddT.addActionListener(e-> new AddTaskForm(current).show());
        btnaddTb.addActionListener(e-> new ListTB(current).show());
        btnaddins.addActionListener(e-> new Listins(current).show());
                    Button btrie=new Button("TRi Tournoi par date");
        btrie.addActionListener(e-> new Tritournoi(current).show());
        btS.addActionListener(e-> new Search(current).show());


        addAll(btnListTasks,btnaddT,btnaddTb,btnaddins,btrie,btS);
        
        
    }
    
    
}
