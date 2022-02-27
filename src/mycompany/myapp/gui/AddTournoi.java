/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import mycompany.myapp.entities.Tournoi;
import mycompany.myapp.sercice.serviceT;

/**
 *
 * @author Taha
 */


public class AddTournoi extends Form{
    
     public AddTournoi(Form previous) {
        setTitle("Add a new Tournoi");
        setLayout(BoxLayout.y());
        
        TextField Name = new TextField("","TaskName");
        TextField cathegories= new TextField("", "cathegories");
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Name.getText().length()==0)||(cathegories.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Tournoi t = new Tournoi((cathegories.getText().toString()), Name.getText().toString());
                        if( serviceT.getInstance().addTask(t))
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
        
        addAll(Name,cathegories,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
