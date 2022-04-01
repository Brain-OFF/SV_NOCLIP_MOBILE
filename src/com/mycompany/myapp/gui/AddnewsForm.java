/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class AddnewsForm extends Form{

    public AddnewsForm(Form previous) {
        setTitle("Add a news");
        setLayout(BoxLayout.y());
        
        TextField tftitre = new TextField("","titre");
        TextField tftext = new TextField("","text");
        TextField tfTjeu = new TextField("","Jeu");
         TextField tfdate = new TextField("","Date");
         TextField tfcategorie = new TextField("","categorie");
        
        
        Button btnValider = new Button("Add News");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitre.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        News t = new News( tftext.getText().toString() , tfTjeu.getText().toString(), tftitre.getText().toString(),tfcategorie.getText().toString(), tfdate.getText().toString());
                        if( ServiceTask.getInstance().addnews(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tftitre,tftext,tfTjeu,tfdate,tfcategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
