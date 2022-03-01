/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTask;
import java.util.*;
import static java.util.Collections.list;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    
    Form current;

    public  Container  addItem(Tournoi u)
    {
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.getId()+"");
        Label lbnom=new Label(u.getName());
        Label lbbio=new Label(u.getDiscription());
        Label btnmail=new Label(u.getCathegorie());
        btnmail = new Label(u.getDateT());
        Button btnmodify=new Button("Modify");
        Button btdelete=new Button("Delete");

        cnt.addAll(lbid,lbnom,lbbio,btnmail,btnmodify,btdelete);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
        btnmodify.addActionListener(e-> new ModifierT(current,u).show()); 
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceTask.getInstance().DeleteT(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Tournoi> list=ServiceTask.getInstance().getAllTasks();
                           
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
      public ListTasksForm(Form previous) {
        setTitle("List tasks");
        ArrayList<Tournoi> list=ServiceTask.getInstance().getAllTasks();
        SpanLabel sp = new SpanLabel();
        add(sp);
        for (Tournoi T : list) {add(addItem(T));}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
