/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author xDrais
 */
public class ModifyCoach extends Form{
 
public ModifyCoach(Form previous,Coach coach1) {
        setTitle("Modify a Coach");
        setLayout(BoxLayout.y());
        Label lbid=new Label(coach1.idtoString());
        TextField tfname = new TextField("","name");
        TextField tflastname = new TextField("","lastname");
        TextField tfcategorie = new TextField("","categorie");
        TextField tfrank = new TextField("","rank");
        
 
        tfname.setText(coach1.getName());
        tflastname.setText(coach1.getLastname());
        tfcategorie.setText(coach1.getCategorie());
        tfrank.setText(coach1.getRank()+"");
        Button btnValider = new Button("Update");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfname.getText().length()==0) || (tflastname.getText().length()==0) || (tfcategorie.getText().length()==0) || (tfrank.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Coach U = new Coach(Integer.parseInt(tfrank.getText()),tfname.getText().toString(), tflastname.getText().toString(), tfcategorie.getText().toString());     
                        U.setName(tfname.getText().toString());
                        U.setLastname(tflastname.getText().toString());
                        U.setCategorie(tfcategorie.getText().toString());
                        U.setRank(Integer.parseInt(tfrank.getText()));
                        U.setId(coach1.getId());
                        if( ServiceTask.getInstance().ModifyCoach(U))
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
        
        addAll(lbid,tfname,tflastname,tfcategorie,tfrank,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }   
}
