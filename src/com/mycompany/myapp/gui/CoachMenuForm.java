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
public class CoachMenuForm extends Form{
Form current;
    public CoachMenuForm(Form previous,User U) {
        
        User Current_user=U;
        current=this; //Back 
        setTitle("Home User :"+U.getUsername());
        System.out.println(U);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        
        Button btnAddCoach = new Button("Add Coach");
        
        Button btnListCoach = new Button("List Coach");
        
        Button btnstat = new Button("stat Coach");
        btnstat.addActionListener(e-> new chart().createPieChartForm(current).show());

        btnAddCoach.addActionListener(e-> new AddCoachForm(current).show());
        btnListCoach.addActionListener(e-> new ListCoachsForm(current,Current_user).show());
        
        Button btnListreservation = new Button("List reservation");
        
        btnListreservation.addActionListener(e-> new ListReservationForm(current).show());
     
        if (U.getStatus().compareTo("admin")==0)  
            addAll(btnAddCoach,btnListCoach,btnstat,btnListreservation);
        else
            addAll(btnListreservation);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
