/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
        
        cnt.addAll(lbid,lbnom,lblastname,lbcategorie,lbrank,btdelete,btnmodify);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
    /* btnmodify.addActionListener(e-> new ModifyUser(current,u).show()); 
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceUser.getInstance().DeleteUser(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<User> list=ServiceUser.getInstance().getAllUsers();
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );*/
    return cnt2;
    }
    public ListCoachsForm(Form previous) {
        setTitle("List Users");
       
        ArrayList<Coach> list=ServiceTask.getInstance().getAllCoachs();
        SpanLabel sp = new SpanLabel();
     
        for (Coach user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}

