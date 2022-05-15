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
import com.mycompany.myapp.entities.InscriptionT;
import com.mycompany.myapp.entities.Tournoi;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Tournoi> tasks;
    public ArrayList<InscriptionT> ins;
    public ArrayList<Tournoi> taskT;
        public ArrayList<Tournoi> taskse;
        public ArrayList<Tournoi> taskSS;
        String s;

    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addTask(Tournoi t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "createT1";
    
       req.setUrl(url);
       
       req.addArgument("nom", t.getName());
       req.addArgument("date", t.getDateT());
       req.addArgument("cathegorie", t.getCathegorie());
       req.addArgument("discription", t.getDiscription());
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

    public ArrayList<Tournoi> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Tournoi t = new Tournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setCathegorie(obj.get("cathegorie").toString());
                t.setName(obj.get("nom").toString());
                t.setDateT(obj.get("Date").toString());
                t.setDiscription(obj.get("Discription").toString());
                
                    
                   
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Tournoi> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"getalltournois/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
     public boolean ModifyUser(Tournoi t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updatemobile/";
        req.setUrl(url);
       System.out.println(url);
       req.addArgument("id", t.getId()+"");
       req.addArgument("nom", t.getName());
       req.addArgument("date", t.getDateT());
       req.addArgument("cathegorie", t.getCathegorie());
       req.addArgument("discription", t.getDiscription());
        System.out.println(Statics.BASE_URL + "updatemobile/");
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
     public boolean DeleteT(Tournoi U) { 
         System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "deletetour/";
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

  public boolean addIns(InscriptionT t,Tournoi tt,User current_user) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "createIns";
           req.addArgument("id", tt.getId()+"");
       req.setUrl(url);
        System.out.println(url);
       req.addArgument("user", current_user.getId()+"");
       req.addArgument("user_name", t.getUser_name());
       req.addArgument("email", t.getEmail());
       
       if(t.isEtat()==true) {req.addArgument("etat","true");}
       else if(t.isEtat()==false){req.addArgument("etat","false");}
       
       req.addArgument("Rank", t.getRank());
       t.setTournoi(tt.getId());
       req.addArgument("tournoi",t.getTournoi()+"");
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
  

    public ArrayList<InscriptionT> parseins(String jsonText){
        try {
            ins=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                InscriptionT t = new InscriptionT();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setUser_name((obj.get("user_name").toString()));
                t.setEmail(obj.get("email").toString());
                t.setRank(obj.get("Rank").toString());
                
                    
                   
                ins.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return ins;
    }
    public ArrayList<InscriptionT> getAllins(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"listINS/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ins = parseins(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ins;
    }

 public boolean Deleteins(InscriptionT U) { 
         System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "deleteins/";
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
  public boolean ModifyIns(InscriptionT t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updateins12/";
        req.setUrl(url);
       System.out.println(url);
       req.addArgument("id", t.getId()+"");
       req.addArgument("user_name", t.getUser_name());
       req.addArgument("email", t.getEmail());
       req.addArgument("Rank", t.getRank());

        System.out.println(Statics.BASE_URL + "updateins12/");
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
 
 
  
  
  
  
   public ArrayList<Tournoi> parseTasksT(String jsonText){
        try {
            taskT=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Tournoi t = new Tournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setCathegorie(obj.get("cathegorie").toString());
                t.setName(obj.get("nom").toString());
                t.setDateT(obj.get("Date").toString());
                t.setDiscription(obj.get("Discription").toString());
    
                            taskT.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        Collections.sort(taskT);
        return taskT;
    }
    
    public ArrayList<Tournoi> getAllTaskT(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"TreeM/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasksT(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return taskT;
    }
    
    
   

   public boolean search(Tournoi t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "searchM/";
    
       req.setUrl(url);
       
       req.addArgument("nom", t.getName());
       
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

 
  
    
    public ArrayList<Tournoi> getAllTaskse(String t){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"searchM/";
        req.setUrl(url);
        req.setPost(false);
               req.addArgument("nom", t);
               System.out.print(t);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                taskse = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return taskse;
    }
    
 
}

