/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author admin
 */
public class modifiernewsForm extends Form {
     public modifiernewsForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
         Task g = null;
        setTitle("Add a new game");
        setLayout(BoxLayout.y());
         TextField Text = new TextField("","Text");
        TextField Titre = new TextField("","Titre");
        TextField Jeux= new TextField("", "Jeux");
        TextField Date = new TextField("","Date");
        TextField Categorie= new TextField("", "Categorie");
        Button btnValider = new Button("md ");
        Text.setText(g.getText());
        Titre.setText(g.getTitre());
        Jeux.setText(g.getJeux()+"");
        Date.setText(g.getDate());
        Categorie.setText(g.getCategorie());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Text.getText().length()==0)||(Titre.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         Task t = new Task(Text.getText(), Titre.getText()  , Jeux.getText(),Date.getText(),Categorie.getText());
                        t.setId(g.getId());
                        if( ServiceTask.getInstance().ModifyCoach(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(Text,Date,Titre,Jeux,Categorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }

    

    
    
}
