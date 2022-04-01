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
import com.mycompany.myapp.entities.Task;

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
        
        Button btnAddTask = new Button("Add News");
        Button btnListTasks = new Button("List News");
          Button btnmodifierTasks = new Button("update News");
        
         Button btncategorie = new Button("Add categorie");
        Button btnListcategorie = new Button("List categorie");
          Button btnmdfcategorie = new Button("update categorie");
        
        
        btnAddTask.addActionListener(e-> new AddTaskForm(current).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
         btncategorie.addActionListener(e-> new  categorieForm(current).show());
           btnmodifierTasks.addActionListener(e-> new  modifiernewsForm(current).show());
            btnmdfcategorie.addActionListener(e-> new  modifiercategorieForm(current).show());
         
         
         
        
        addAll(btnAddTask,btnListTasks,btncategorie,btnListcategorie,btnmodifierTasks,btnmdfcategorie);
        
        
    }
    
    
}
