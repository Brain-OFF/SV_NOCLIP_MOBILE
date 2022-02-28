/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListUsersForm extends Form {

    public  Container  addItem(User u)
    {
        Container cnt=new Container(BoxLayout.y());
        Label lbid=new Label(u.idtoString());
        Label lbnom=new Label(u.getUsername());
        Label lbbio=new Label(u.getBio());
        Button btnmail=new Button(u.getEmail());
        cnt.addAll(lbid,lbnom,lbbio,btnmail);
        Container cnt2=new Container(BoxLayout.x());
        cnt2.addAll(cnt);
      
        btnmail.addActionListener(e->{
            System.out.println(u);
        });
        cnt2.setLeadComponent(btnmail);
    return cnt2;
    }
    public ListUsersForm(Form previous) {
        setTitle("List Users");
        ArrayList<User> list=ServiceUser.getInstance().getAllUsers();
        SpanLabel sp = new SpanLabel();
        for (User user : list) {
           add(addItem(user));
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
