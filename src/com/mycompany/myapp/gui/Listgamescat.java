/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import com.mycompany.myapp.entities.Games;
import com.mycompany.myapp.entities.Gamescat;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceGames;

import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class Listgamescat extends Form{
int gamecat;
Cart P;
User Cu;
    public Listgamescat(Form previous,Gamescat c,User U,Cart Panier ) {
        P=Panier;
        Cu=U;
    gamecat=c.getId();
        setTitle("List Games");
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
        Button favorite=new Button("Favorite");
        Button tfPanier = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
        if (Cu.getStatus().compareTo("admin")==0)
        cnt.addAll(lbid,lbnom,lbdesc,btnprix,btnmodify,btdelete,favorite,tfPanier);
        else 
        cnt.addAll(lbid,lbnom,lbdesc,btnprix,favorite,tfPanier);    
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);

       btnmodify.addActionListener(e-> new modifygames(current,u).show()); 
       tfPanier.addActionListener((e) -> {
                P.AddProduct(u);
              ToastBar.showMessage("Le produit a été ajouté à votre panier ", FontImage.MATERIAL_INFO);
            });
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
        
        favorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceGames.getInstance().favorite(u,Cu))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Games> list=ServiceGames.getInstance().getAllGames();
                           
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
