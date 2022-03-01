/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class AddCoachForm  extends Form{
    
public AddCoachForm(Form previous)  {
        setTitle("Add a new coach");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","name");
        TextField tflastname= new TextField("", "lastname");
        TextField rank = new TextField("","rank");
        TextField categorie = new TextField("","categorie");

        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tflastname.getText().length()==0)||(rank.getText().length()==0)||(categorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Coach t = new Coach(Integer.parseInt(rank.getText()), tfName.getText().toString(), tflastname.getText().toString(), categorie.getText().toString());
                        if( ServiceTask.getInstance().addCoach(t))
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
        
        addAll(tfName,tflastname,rank,categorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
