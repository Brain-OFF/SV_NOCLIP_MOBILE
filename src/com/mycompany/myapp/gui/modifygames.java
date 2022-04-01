/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Games;
import com.mycompany.myapp.services.ServiceGames;

/**
 *
 * @author bhk
 */
public class modifygames extends Form{

    public modifygames(Form previous, Games g) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new game");
        setLayout(BoxLayout.y());
        
        TextField Name = new TextField("","Name");
        TextField desc= new TextField("", "desc");
        TextField prix = new TextField("","prix");
        TextField img= new TextField("", "img");
        Button btnValider = new Button("md ");
        Name.setText(g.getName());
        desc.setText(g.getDescreption());
        prix.setText(g.getPrix()+"");
        img.setText(g.getImg());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Name.getText().length()==0)||(desc.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Games t = new Games(Name.getText(), desc.getText(), Integer.parseInt(prix.getText()) , img.getText());
                        t.setId(g.getId());
                        if( ServiceGames.getInstance().ModifyGames(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(Name,desc,prix,img,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
