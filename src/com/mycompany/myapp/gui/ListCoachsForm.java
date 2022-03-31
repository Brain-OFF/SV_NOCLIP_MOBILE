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
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author xDrais
 */
public class ListCoachsForm extends Form{
    
  Form current;

    public  Container  addItem(Coach u)
    {
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.idtoString());
        Label lbnom=new Label(u.getName());
        Label lblastname=new Label(u.getLastname());
        Label lbcategorie=new Label(u.getCategorie());
        Label lbrank=new Label(u.getRank()+"");
        Button btdelete=new Button("Delete");
        Button btnmodify=new Button("Modify");
        Button btnR=new Button("Reserver");

        cnt.addAll(lbid,lbnom,lblastname,lbcategorie,lbrank,btdelete,btnmodify,btnR);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
     btnmodify.addActionListener(e-> new ModifyCoach(current,u).show()); 
     btnR.addActionListener(e-> new AddReservation(current,u).show()); 

        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceTask.getInstance().deleteCoach(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Coach> list=ServiceTask.getInstance().getAllCoachs();
                           
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
    public ListCoachsForm(Form previous) {
        setTitle("List Users");
       setLayout(BoxLayout.y());
        ArrayList<Coach> list=ServiceTask.getInstance().getAllCoachs();
        SpanLabel sp = new SpanLabel();
     
        for (Coach user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}

