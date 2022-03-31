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
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());



        
        current.add(new Label("Choose an option"));
        Button btnAddCoach = new Button("Add Coach");
        Button btnListCoach = new Button("List Coach");
        
        btnAddCoach.addActionListener(e-> new AddCoachForm(current).show());
        btnListCoach.addActionListener(e-> new ListCoachsForm(current).show());
        Button btnListreservation = new Button("List reservation");
        
        btnListreservation.addActionListener(e-> new ListReservationForm(current).show());
        current.addAll(btnAddCoach,btnListCoach,btnListreservation);

 
current.getToolbar().addCommandToSideMenu("coach", null, (e)->current.show() );

       





        
    }
    
    
}

