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
import com.mycompany.myapp.entities.Cart;
import com.mycompany.myapp.entities.Gamescat;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceGamescat;

import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class Listcat extends Form{
    User Cu;
    Cart Panier;
    public Listcat(Form previous,User U,Cart P) {
        Cu=U;
        Panier=P;
        setTitle("Games Categories");
        SpanLabel sp = new SpanLabel();
        ArrayList<Gamescat> list=ServiceGamescat.getInstance().getAllGamescat();

  
        for (Gamescat user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
     public  Container  addItem(Gamescat u)
    {
       Form current= this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.getId()+"");
        Label lbnom=new Label(u.getNom());
        Label lbdesc=new Label(u.getDescription());
       
        Button btdelete=new Button("Delete");
        Button btaddgame=new Button("addgame");
        Button btviewgame=new Button("viewgame");
        if (Cu.getStatus().compareTo("admin")==0)
        cnt.addAll(lbid,lbnom,lbdesc,btdelete,btaddgame,btviewgame);
        else 
        cnt.addAll(lbid,lbnom,lbdesc,btviewgame);    
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
        
        btaddgame.addActionListener(e-> new Addgames(current,u).show());
        btviewgame.addActionListener(e-> new Listgamescat(current,u,Cu,Panier).show());
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceGamescat.getInstance().deletegamescat(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Gamescat> list=ServiceGamescat.getInstance().getAllGamescat();
                           
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
