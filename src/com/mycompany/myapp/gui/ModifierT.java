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
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author Taha
 */
public class ModifierT extends Form{
    public ModifierT(Form previous,Tournoi user1){
        setTitle("Modify");        
        setLayout(BoxLayout.y());
        
        TextField Name = new TextField("","Name");
        TextField Date= new TextField("", "date");
        TextField cathegorie= new TextField("", "cath");
        TextField discription= new TextField("", "disc");
        Button btnValider = new Button("Add task");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Name.getText().length()==0)||(Date.getText().length()==0)||(cathegorie.getText().length()==0)||(discription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Tournoi t = new Tournoi(Name.getText().toString(),(Date.getText().toString()),cathegorie.getText().toString(),discription.getText().toString());
                        t.setName(Name.getText().toString());
                        t.setId(user1.getId());
                        t.setCathegorie(cathegorie.getText().toString());
                        t.setDateT(Date.getText().toString());
                        t.setDiscription(discription.getText().toString());
                        if( ServiceTask.getInstance().ModifyUser(t))
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
        
        addAll(Name,Date,cathegorie,discription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    }

