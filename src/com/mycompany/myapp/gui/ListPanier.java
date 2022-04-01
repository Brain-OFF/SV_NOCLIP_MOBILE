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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceGames;

import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class ListPanier extends Form{
    User Cu;
    Cart Pa;
    public ListPanier(Form previous,User U,Cart Panier) {
        Pa=Panier;
        Cu=U;
        setTitle("List Games");
        SpanLabel sp = new SpanLabel();
        ArrayList<Games> list=ServiceGames.getInstance().getAllGames();
        
  
        for (Games user : Pa.getCartList()) {
           add(addItem(user));
        }
        Label total=new Label("Total : "+Pa.total()+"");
        Button commande=new Button("Commander");
        commande.addActionListener(e -> new AjouterCommande(this,Pa).show());
        add(total);
        add(commande);
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
        cnt.addAll(lbid,lbnom,lbdesc,btnprix,btdelete);
        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(cnt);
        
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                   Pa.RemoveProduct(u);
                    ToastBar.showMessage("Le produit a été retiré à votre panier ", FontImage.MATERIAL_INFO);
                }
            }
        );
        
    return cnt2;
    }
}
