/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author Taha
 */
public class ModifierT extends Form{
    public ModifierT(Form previous,Tournoi user1){
        setTitle("Modify");        
        setLayout(BoxLayout.y());
        
       TableLayout tl = new TableLayout(3, 2);

        setLayout(BoxLayout.y());
        
        TextField Name = new TextField("","Name");
        TextField Date= new TextField("", "date");
        ComboBox cathegorie=new ComboBox();
                    cathegorie.addItem("RPG");
                    cathegorie.addItem("MMORPG");
                    cathegorie.addItem("MOBA");
                    cathegorie.addItem("RTS");
                    cathegorie.addItem("Battle Royale");
                    cathegorie.addItem("Beat Them All");  
                    cathegorie.addItem("survival Horror");   

        TextField discription= new TextField("", "disc");
        Button btnValider = new Button("modifier");
        Name.setText(user1.getName());
        Date.setText(user1.getDateT());
        discription.setText(user1.getDiscription());
        btnValider.getAllStyles().setBorder(Border.createEmpty());
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        Validator val = new Validator();
        
val.addConstraint(Name, new LengthConstraint(3));
val.addConstraint(discription, new LengthConstraint(15));
val.addConstraint(Date, new LengthConstraint(6));

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Name.getText().length()==0)||(Date.getText().length()==0)||(discription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
                    try {
                        Tournoi t = new Tournoi(Name.getText().toString(),(Date.getText().toString()),cathegorie.getSelectedItem().toString(),discription.getText().toString());
                        t.setName(Name.getText().toString());
                        t.setId(user1.getId());
                        t.setCathegorie(cathegorie.toString());
                        t.setDateT(Date.getText().toString());
                        t.setDiscription(discription.getText().toString());
                        if( ServiceTask.getInstance().ModifyUser(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
                
                
            }
        });
        
        
add(new FloatingHint(Name));
add(new FloatingHint(Date));
add( new FloatingHint(discription));
add(cathegorie);

add(btnValider);        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    }

