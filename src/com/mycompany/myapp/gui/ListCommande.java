/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.CommandeService;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Commande;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mehdi
 */
public class ListCommande extends Form{
Form current;
ArrayList<Commande> data = new ArrayList<>();

public  Container  addItem(Commande u)
    {
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.getCommandeId()+"");
        Label lbnom=new Label(u.getNom());
        Label lbprenom=new Label(u.getPrenom());
        Label lbadr=new Label(u.getAdresse());
        Label lbtlp=new Label(u.getNumTelephone()+"");
        Label lbdate=new Label(u.getDate()+"");        
        Label btnmail=new Label(u.getEmail());

        
        cnt.addAll(lbid,lbnom,lbprenom,btnmail,lbadr,lbtlp,lbdate);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
            
       
    return cnt2;
    }


public ListCommande(Form previous) {
    setTitle("List Commandes");
        ArrayList<Commande> list=CommandeService.getInstance().getAllCours();
        SpanLabel sp = new SpanLabel();
        for (Commande com : list) {
           add(addItem(com));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
