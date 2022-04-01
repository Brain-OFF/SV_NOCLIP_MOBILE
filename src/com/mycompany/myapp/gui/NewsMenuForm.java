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
public class NewsMenuForm extends Form{
Form current;
    public NewsMenuForm(Form previous,User U) {
        
        User Current_user=U;
        current=this; //Back 
        setTitle("Home User :"+U.getUsername());
        add(new Label("Choose an option"));
        
        Button btnAddTask = new Button("Add News");
        Button btnListTasks = new Button("List News");
          Button btnmodifierTasks = new Button("update News");
        
         Button btncategorie = new Button("Add categorie");
        Button btnListcategorie = new Button("List categorie");
          Button btnmdfcategorie = new Button("update categorie");
        
        
        btnAddTask.addActionListener(e-> new AddnewsForm(current).show());
        btnListTasks.addActionListener(e-> new ListnewsForm(current).show());
         btncategorie.addActionListener(e-> new  categorieForm(current).show());
           btnmodifierTasks.addActionListener(e-> new  modifiernewsForm(current).show());
            btnmdfcategorie.addActionListener(e-> new  modifiercategorieForm(current).show());
         btnListcategorie.addActionListener(e-> new  listecategorieForm(current).show());
         
         
        if (U.getStatus().compareTo("admin")==0)
        addAll(btnAddTask,btnListTasks,btncategorie,btnListcategorie,btnmodifierTasks,btnmdfcategorie);
        else   
        addAll(btnListTasks,btnListcategorie);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
}
