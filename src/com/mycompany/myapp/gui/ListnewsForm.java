/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
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
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceNews;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class ListnewsForm extends Form {
//ArrayList<Task> data = new ArrayList<>();
//    private ListTasksForm current;
    Form current;
//    public  Container  addItem(Task u)
//    {
//        current=this;
//        Container cnt=new Container(BoxLayout.y());
//        Label ltitre=new Label(u.getId()+"");
//        Label ltext=new Label(u.getText()+"");
//        Label ldate=new Label(u.getDate()+"");
//        Label lcate=new Label(u.getCategorie()+"");
//        Label lbtlp=new Label(u.getJeux()+"");
//      
//        
//        cnt.addAll(ltitre,ltext,ldate,lcate,lbtlp);
//        Container cnt2=new Container(BoxLayout.x());
//        
//        cnt2.addAll(cnt);
//            
//       
//    return cnt2;
//    }
   
    public  Container  addItem(News u)
    {
        current=this;
          Container cnt=new Container(BoxLayout.y());
        Label ltitre=new Label(u.getId()+"");
        Label ltext=new Label(u.getText()+"");
        Label ldate=new Label(u.getDate()+"");
        Label lbcategorie=new Label(u.getCategorie()+"");
        Label lbtlp=new Label(u.getJeux()+"");
        Button btdelete=new Button("Delete");
        Button btnmodify=new Button("Modify");
       

        cnt.addAll(ltitre,ltext,ldate,lbcategorie,lbtlp,btdelete,btnmodify);
        Container cnt2=new Container(BoxLayout.x());
        
        cnt2.addAll(cnt);
    // btnmodify.addActionListener(e-> new ModifyCoach(current,u).show()); 
    

        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
                {
                    try {
                        if( ServiceNews.getInstance().deleteNews(u))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           ArrayList<News> list=ServiceNews.getInstance().getAllTask();
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        );
    return cnt2;
    }

    public ListnewsForm(Form previous) {
        setTitle("List tasks");

        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceNews.getInstance().getAllTask().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }





//public ListTasksForm(Form previous) {
//    setTitle("List Commandes");
//        ArrayList<Task> list=(ArrayList<Task>) ServiceTask.getInstance().getAllTasks();
//        SpanLabel sp = new SpanLabel();
//        for (Task com : list) {
//           add(addItem(com));
//        }
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//    }
}
