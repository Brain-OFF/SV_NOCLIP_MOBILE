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
import com.mycompany.myapp.entities.Games;
import com.mycompany.myapp.entities.Gamescat;
import com.mycompany.myapp.services.ServiceGames;

import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class Listgamescat extends Form{
int gamecat;
    public Listgamescat(Form previous,Gamescat c) {
    gamecat=c.getId();
        setTitle("List tasks");
        SpanLabel sp = new SpanLabel();
        ArrayList<Games> list=ServiceGames.getInstance().getAllGamescat(gamecat);

  
        for (Games user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
     public  Container  addItem(Games u)
    {
       Form current= this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.getId()+"");
        Label lbnom=new Label(u.getName());
        Label lbdesc=new Label(u.getDescreption());
        Label btnprix=new Label(u.getPrix()+"");
        Button btdelete=new Button("Delete");
        Button btnmodify=new Button("Modify");
        
        cnt.addAll(lbid,lbnom,lbdesc,btnprix,btnmodify,btdelete);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);

       btnmodify.addActionListener(e-> new modifygames(current,u).show()); 
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceGames.getInstance().deletegames(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Games> list=ServiceGames.getInstance().getAllGamescat(gamecat);
                           
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
}
