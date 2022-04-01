/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cart;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.CommandeService;



/**
 *
 * @author mehdi
 */
public class AjouterCommande extends Form{
    

       public AjouterCommande(Form previous,Cart P) {
       
   
        setTitle("Ajouter un nouveau commande");
       
        
        TextField tfnom = new TextField("","Nom");
        TextField tfprenom = new TextField("","prenom");
        TextField tfemail = new TextField("","email");
        TextField tfadresse = new TextField("","adresse");
        TextField tfnumTelephone = new TextField("","numtelephone");
        TextField tfdate = new TextField("","date");         
        Label tftotalCost = new Label("totalcost"+P.total());
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0)||(tfemail.getText().length()==0)||(tfadresse.getText().length()==0)||(tfnumTelephone.getText().length()==0)||(tfdate.getText().length()==0)||(tftotalCost.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
                     try {
                         Commande s=new Commande();
                
                    s.setNom(tfnom.getText().toString());
                    s.setPrenom(tfprenom.getText().toString());
                    s.setEmail(tfemail.getText().toString());
                    s.setAdresse(tfadresse.getText().toString());
                    s.setNumTelephone(Integer.parseInt((tfnumTelephone.getText())));
                    s.setDate(tfdate.getText().toString());
                    s.setTotalCost((int)P.total());
                    s.setCommandeId(1);
                    System.err.println("data commande=="+s);   
                        if(CommandeService.instance.AjouterCommande(s))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", " must be a number", new Command("OK"));
                    }
            
                   
            }
        }
        });
        addAll(tfnom,tfprenom,tfemail,tfadresse,tfnumTelephone,tfdate,tftotalCost,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack()); 
}
}