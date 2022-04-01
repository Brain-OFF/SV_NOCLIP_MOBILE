/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.services.ServiceCoach;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author xDrais
 */
public class ListReservationForm extends Form{
Form current;

    public  Container  addItem(reservation u)
    {
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.idtoString());
        Label lbstart=new Label(u.getTempsstart());
        Label lbend=new Label(u.getTempsend());
        Label lbdispo=new Label(u.isDispo()+"");

        Button btdelete=new Button("Delete");
        Button btnmodify=new Button("Modify"); 
        
        cnt.addAll(lbid,lbstart,lbend,lbdispo,btdelete,btnmodify);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
     btnmodify.addActionListener(e-> new ModifyReservation(current,u).show()); 
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceCoach.getInstance().deleteReservation(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<reservation> list=ServiceCoach.getInstance().getAllReservations();
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );
    return cnt2;
    }
    public ListReservationForm(Form previous) {
        setTitle("List Reservation");
       setLayout(BoxLayout.y());
        ArrayList<reservation> list=ServiceCoach.getInstance().getAllReservations();
        SpanLabel sp = new SpanLabel();
     
        for (reservation user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
