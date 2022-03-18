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
import com.mycompany.myapp.entities.Tournoi;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnListTasks = new Button("List Tournoi");
        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        addAll(btnListTasks);
        
        
    }
    
    
}
