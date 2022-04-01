/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Task;

import com.mycompany.myapp.entities.categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class categorieliste {
     public ArrayList<categorie> task;
    
    public static categorieliste instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private categorieliste() {
         req = new ConnectionRequest();
    }

    public static categorieliste getInstance() {
        if (instance == null) {
            instance = new categorieliste();
        }
        return instance;
    }

    public boolean addcategorie(categorie p) {
        String url = Statics.BASE_URL + "addcategorie/?nom=" +p.getName();
      req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<categorie> parseTasks(String jsonText){
        try {
            task=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                categorie t = new categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("name")==null)
              t.setName("null");
                else
                    t.setName(obj.get("name").toString());
               
                task.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return task;
    }
       public boolean ModifyCategorie(categorie t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updatemobile/";
        req.setUrl(url);
       System.out.println(url);
    
       req.addArgument("name", t.getName());
   
        System.out.println(Statics.BASE_URL + "categorie/updatemobilecategori");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
       NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        }
     
    public ArrayList<categorie> getAllcategorie(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"categorie/listcategoriemobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                task = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return task;
    }
    
}
