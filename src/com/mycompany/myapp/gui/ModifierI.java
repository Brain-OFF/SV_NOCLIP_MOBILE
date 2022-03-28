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
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.InscriptionT;
import com.mycompany.myapp.services.ServiceTask;
/**
 *
 * @author Taha
 */
public class ModifierI  extends Form{
     public ModifierI(Form previous,InscriptionT user1){
        setTitle("Modify");        
        setLayout(BoxLayout.y());
                TableLayout tl = new TableLayout(3, 2);

        TextField Name = new TextField("","user name");
        TextField email= new TextField("", "email");
 RadioButton etat= new RadioButton("Accept all terms");
         ComboBox Rank = new ComboBox();
                    Rank.addItem("bronze");
                    Rank.addItem("silver");
                    Rank.addItem("gold");
                    Rank.addItem("platinum");
                    Rank.addItem("diamond");
                    Rank.addItem("Master");
                    Rank.addItem("grand");       

                    
                    
                   
        Button btnValider = new Button("Add inscription");
          btnValider.getAllStyles().setBorder(Border.createEmpty());
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        Validator val = new Validator();
        
val.addConstraint(Name, new LengthConstraint(3));
val.addConstraint(email, RegexConstraint.validEmail());        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Name.getText().length()==0)||(email.getText().length()==0)||(etat.animate()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        InscriptionT t = new InscriptionT(Name.getText().toString(),(email.getText().toString()),Rank.getSelectedItem().toString());
                        t.setUser_name(Name.getText().toString());
                        t.setId(user1.getId());
                        t.setEmail(email.getText().toString());
                        t.setRank(Rank.toString());
                        if( ServiceTask.getInstance().ModifyIns(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
add(new FloatingHint(Name));
add(new FloatingHint(email));
add(etat);
add(Rank);    
add(btnValider);        
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
