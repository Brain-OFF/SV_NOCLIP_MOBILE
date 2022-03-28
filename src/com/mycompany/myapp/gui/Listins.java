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
import com.mycompany.myapp.entities.InscriptionT;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTask;
import java.util.*;
import static java.util.Collections.list;

/**
 *
 * @author Taha
 */
public class Listins extends Form{
        Form current;

     public  Container  addItem(InscriptionT u)
    {
    current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.getId()+"");
        Label lbnom=new Label(u.getUser_name());
        Label lbbio=new Label(u.getEmail());
        Label btnmail = new Label(u.getRank());
        Button btdelete=new Button("Delete");
                Button btM=new Button("Modifier");
        cnt.addAll(lbid,lbnom,lbbio,btnmail,btdelete,btM);
        btM.addActionListener(e-> new ModifierI(current,u).show()); 

        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(cnt);

          btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceTask.getInstance().Deleteins(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Tournoi> list=ServiceTask.getInstance().getAllTasks();

                          revalidate();
                            
                           
                        }else
                        { Dialog.show("ERROR", "Server error", new Command("OK"));}
                       
                        
                         } 
                        catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );
    return cnt2;
    }  
     public Listins(Form previous) {
        setTitle("List tournois");
        ArrayList<InscriptionT> list=ServiceTask.getInstance().getAllins();
        SpanLabel sp = new SpanLabel();
        add(sp);
        for (InscriptionT T : list) {add(addItem(T));}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
