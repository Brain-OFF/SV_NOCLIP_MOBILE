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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> Users;
    public User CurrentUser;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(User U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "addusermobile/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("username", U.getUsername());
       req.addArgument("password", U.getPassword());
       req.addArgument("email", U.getEmail());
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
    
    public boolean deleteUser(User U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "delusermobile/"+U.getId();
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.idtoString());
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
    
     public User Login(User U) {
        getAllUsers();
        System.out.println(U);
        
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "loginmobile/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("username", U.getUsername());
       req.addArgument("password", U.getPassword());
         User Us = null;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String rep=new String(req.getResponseData());
                System.out.println(rep);
                if(rep.trim().equalsIgnoreCase("error"))
                {
                    Dialog.show("error", "login ou pwd invalid", "ok","cancel");
                }
                else
                {
                    ArrayList<User> Userss;
                
                Userss = parseUser1(new String(req.getResponseData()));
                System.out.println("user :"+Userss);
                req.removeResponseListener(this);
                
                 for(User u:Userss)
                {
                     CurrentUser=u;
                }
            
                }
            }

            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CurrentUser;
    }
    public ArrayList<User> parseUser1(String jsonText){
        try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
                User U = new User();
              // int id = Integer.parseInt(obj.get("id").toString());
                //U.setId((int)id);
                U.setId(((int)Float.parseFloat(tasksListJson.get("id").toString())));
                U.setUsername(tasksListJson.get("username").toString());
                U.setEmail(tasksListJson.get("email").toString());
                if (tasksListJson.get("Bio")==null)
                     U.setBio("Null");
               else
                     U.setBio(tasksListJson.get("Bio").toString());
                U.setPassword(tasksListJson.get("password").toString());
                int points=(((int)Float.parseFloat(tasksListJson.get("points").toString())));
                if (tasksListJson.get("points").toString()=="null")
                    U.setPoints(0);
                else
                U.setPoints(((int)Float.parseFloat(tasksListJson.get("points").toString())));
                U.setStatus(tasksListJson.get("Status").toString());
                Users.add(U);
                return Users;
            
            
            
        } catch (IOException ex) {
            
        }
        return Users;
    }

    public ArrayList<User> parseUsers(String jsonText){
        try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                User U = new User();
              // int id = Integer.parseInt(obj.get("id").toString());
                //U.setId((int)id);
                U.setId(((int)Float.parseFloat(obj.get("id").toString())));
                U.setUsername(obj.get("username").toString());
                U.setEmail(obj.get("email").toString());
                if (obj.get("Bio")==null)
                     U.setBio("Null");
               else
                     U.setBio(obj.get("Bio").toString());
                //U.setPassword(obj.get("password").toString());
               // int points=(((int)Float.parseFloat(obj.get("points").toString())));
                //if (points==null)
                  //  U.setPoints(0);
                //else
                U.setPoints(((int)Float.parseFloat(obj.get("points").toString())));
                U.setStatus(obj.get("Status").toString());
                Users.add(U);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Users;
    }
    
    public ArrayList<User> getAllUsers(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"getusersmobile/";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    public boolean ModifyUser(User U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updateusermobile/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.getId()+"");
       req.addArgument("username", U.getUsername());
       req.addArgument("password", U.getPassword());
       req.addArgument("email", U.getEmail());
       req.addArgument("Bio", U.getBio());
       req.addArgument("points", U.getPoints()+"");
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
    public boolean DeleteUser(User U) {
        System.out.println(U);
        System.out.println("********");
       String url = Statics.BASE_URL + "deusersmobile/";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.getId()+"");
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
}
