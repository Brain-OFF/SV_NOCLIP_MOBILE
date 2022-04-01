/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.services.ServiceCoach;
import com.mycompany.myapp.services.ServiceTask;
import java.util.Date;

/**
 *
 * @author xDrais
 */
public class AddReservation extends Form{

public AddReservation(Form previous,Coach c,User U)  {
        setTitle("Add a new reservation");
        setLayout(BoxLayout.y());


Picker tfstart = new Picker();
tfstart.setType(Display.PICKER_TYPE_DATE_AND_TIME);



tfstart.setDate(new Date());

Picker tfend = new Picker();
tfend.setType(Display.PICKER_TYPE_DATE_AND_TIME);



tfend.setDate(new Date());

        

      Switch  tfdispo = new Switch();
        Label dispo=new Label("Dispo : ");
        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(dispo,tfdispo);
        Button btnValider = new Button("Add reservation");

 
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfstart.getText().length()==0)||(tfend.getText().length()==0)||(dispo.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        reservation t = new reservation(tfstart.getText().toString(), tfend.getText().toString(),tfdispo.isValue());
                        t.setUser_id(U.getId());
       if (t.isDispo())
        tfdispo.setOn();
else 
tfdispo.setOff();
                        if( ServiceCoach.getInstance().addReservation(t,c))
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
        
        addAll(tfstart,tfend,cnt2,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
