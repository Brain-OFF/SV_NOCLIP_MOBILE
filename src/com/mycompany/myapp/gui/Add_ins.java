/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.InscriptionT;
import com.mycompany.myapp.services.ServiceTask;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Tournoi;


/**
 *
 * @author Taha
 */
public class Add_ins extends Form{
     public Add_ins(Form previous,Tournoi user1) {
       TextModeLayout tl = new TextModeLayout(3, 2);
        setTitle("Add a new inscription");
        setLayout(BoxLayout.y());
        
        TextField user_name = new TextField("","user name");
        TextField email= new TextField("", "email");
        RadioButton etat= new RadioButton("Accept all terms");
         ComboBox Rank = new ComboBox();
                    Rank.addItem("bronze");
                    Rank.addItem("silver");
                    Rank.addItem("gold");
                    Rank.addItem("platinum");
                    Rank.addItem("diamond");
                    Rank.addItem("Master");
                    Rank.addItem("grand");       

                    
                    
                   
        Button btnValider = new Button("Add inscription");
          btnValider.getAllStyles().setBorder(Border.createEmpty());
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        Validator val = new Validator();
        
val.addConstraint(user_name, new LengthConstraint(3));
val.addConstraint(email, RegexConstraint.validEmail());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((user_name.getText().length()==0)||(email.getText().length()==0)||(etat.animate()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        InscriptionT t = new InscriptionT(user_name.getText(),email.getText().toString(),etat.animate(),Rank.getSelectedItem().toString());
                        t.setTournoi(user1.getId());
                        if( !ServiceTask.getInstance().addIns(t,user1))
                            Dialog.show("ERROR", "Server error", new Command("OK"));else {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
add(new FloatingHint(user_name));
add(new FloatingHint(email));
add(etat);
add(Rank);    
add(btnValider);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
                }

