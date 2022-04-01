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
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.services.ServiceCoach;
import com.mycompany.myapp.services.ServiceTask;
import java.util.Date;

/**
 *
 * @author xDrais
 */
public class ModifyReservation extends Form{
    

public ModifyReservation(Form previous,reservation reservation1 ) {
        setTitle("Modify a reservation");
        setLayout(BoxLayout.y());
        Label lbid=new Label(reservation1.idtoString());
Picker tfstart = new Picker();
tfstart.setType(Display.PICKER_TYPE_DATE_AND_TIME);



tfstart.setDate(new Date());

Picker tfend = new Picker();
tfend.setType(Display.PICKER_TYPE_DATE_AND_TIME);



tfend.setDate(new Date());
        TextField tfcoach = new TextField("","coach");
        Switch  tfdispo = new Switch();
        Label dispo=new Label("Dispo : ");
        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(dispo,tfdispo);
        tfstart.setText(reservation1.getTempsstart());
        tfend.setText(reservation1.getTempsend());
       tfcoach.setText(reservation1.getCoach()+"");
if (reservation1.isDispo())
        tfdispo.setOn();
else 
tfdispo.setOff();
        Button btnValider = new Button("Update");

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfstart.getText().length()==0) || (tfend.getText().length()==0)|| (tfcoach.getText().length()==0)  )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {


                        reservation U = new reservation(Integer.parseInt(tfcoach.getText()),tfstart.getText().toString(), tfend.getText().toString(),tfdispo.isValue());     
                        U.setTempsstart(tfstart.getText().toString());
                        U.setTempsend(tfend.getText().toString());
                        U.setCoach(Integer.parseInt(tfcoach.getText()));
                        
                        U.setId(reservation1.getId());
                        if( ServiceCoach.getInstance().ModifyReservation(U))
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
        
        addAll(lbid,tfstart,tfend,cnt2,tfcoach,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }   
}
