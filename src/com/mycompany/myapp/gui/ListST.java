package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author Taha
 */
public class ListST extends Form {
     Form current;

    public  Container  addItem(Tournoi u)
    {
      
                
        current=this;
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label("            Id:    "+u.getId()+"");
        Label lbnom=new Label("            Name:   "+u.getName());

        Label lbbio=new Label("            Description:    "+u.getDiscription());

        Label btnmail=new Label("            Cathegorie:    "+u.getCathegorie());

         Label btcath = new Label("date:   "+u.getDateT());

        Button btnmodify=new Button("Modify");
        Button btdelete=new Button("Delete");


         btdelete.getAllStyles().setBorder(Border.createEmpty());
         btdelete.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        
        btnmodify.getAllStyles().setBorder(Border.createEmpty());
        btnmodify.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        
        

        
        cnt.addAll(lbid,lbnom,lbbio,btnmail,btdelete,btcath,btnmodify);
        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(cnt);
        btnmodify.addActionListener(e-> new ModifierT(current,u).show()); 


        
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceTask.getInstance().DeleteT(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<Tournoi> list=ServiceTask.getInstance().getAllTasks();

                          revalidate();
                            
                           
                        }else
                        { Dialog.show("ERROR", "Server error", new Command("OK"));}
                       
                        
                         } 
                        catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );
    return cnt2;
    }
    
      public ListST(Form previous,String t) {
        setTitle("List tournois");
        ArrayList<Tournoi> list=ServiceTask.getInstance().getAllTaskse(t);
        System.out.println("Name:" +t);
     
        SpanLabel sp = new SpanLabel();
        sp.setTextPosition(Component.CENTER);
        add(sp);
        for (Tournoi T : list) {add(addItem(T));}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}