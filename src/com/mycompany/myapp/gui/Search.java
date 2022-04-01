/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Taha
 */
public class Search extends Form{
    

    public Search(Form previous) {
        
        
        
        
        setTitle("Add a new Tournoi");
        TableLayout tl = new TableLayout(3, 2);

        setLayout(BoxLayout.y());
        
        TextField Name = new TextField("","Name");
       
        Button btnValider = new Button("Add task");
       
        
        btnValider.getAllStyles().setBorder(Border.createEmpty());
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        Validator val = new Validator();
        
val.addConstraint(Name, new LengthConstraint(3));
Tournoi t = new Tournoi(Name.getText().toString());


btnValider.addActionListener(e-> new ListST(previous,Name.getText().toString()).show() );
        
add(new FloatingHint(Name));


add(btnValider);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
