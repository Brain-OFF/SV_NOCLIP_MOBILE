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
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.reservation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCoach {

    public ArrayList<Coach> Coachs;
public ArrayList<reservation> reservations;
    
    public static ServiceCoach instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCoach() {
         req = new ConnectionRequest();
    }

    public static ServiceCoach getInstance() {
        if (instance == null) {
            instance = new ServiceCoach();
        }
        return instance;
    }

    public boolean addCoach(Coach t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "addcoachmobile";
    
       req.setUrl(url);
       
       req.addArgument("name", t.getName());
        req.addArgument("lastname", t.getLastname());
        req.addArgument("categorie", t.getName());
       req.addArgument("rank", t.getRank()+"");
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

public boolean addReservation(reservation t,Coach c) {
getAllCoachs();
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "addreservationmobile";
               req.addArgument("id", c.getId()+"");
        req.addArgument("id_user", t.getUser_id()+"");
       req.setUrl(url);
       
       req.addArgument("start", t.getTempsstart());
        req.addArgument("end", t.getTempsend());
        if(t.isDispo()==true) 
{
req.addArgument("dispo","1");
}
else if(t.isDispo()==false) 
{
req.addArgument("dispo","0");
}
        t.setCoach(c.getId());
       req.addArgument("coach", t.getCoach()+"");
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




    public ArrayList<Coach> parseCoachs(String jsonText){
        try {
            Coachs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Coach t = new Coach();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);

                t.setRank(((int)Float.parseFloat(obj.get("rank").toString())));

                    t.setName(obj.get("name").toString());

                    t.setLastname(obj.get("lastname").toString());

                    t.setCategorie(obj.get("categorie").toString());
                Coachs.add(t);
        System.out.println(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return Coachs;
    }



 public ArrayList<reservation> parseReservations(String jsonText){
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                reservation t = new reservation();
                Map<String,Object> coachl = (Map<String,Object>) obj.get("coach");
             
                
                   float id_coach = Float.parseFloat(coachl.get("id").toString());
                       t.setCoach((int)id_coach);
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTempsstart((obj.get("tempsstart").toString()));
                t.setTempsend(obj.get("tempsend").toString());

               
                t.setDispo(((Boolean)Boolean.parseBoolean(obj.get("dispo").toString())));
                
                    
                   
                reservations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }




   
   public ArrayList<Coach> getAllCoachs(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"listcoachmobile/";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoachs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }



    public ArrayList<reservation> getAllReservations(){
req=new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"listreservationmobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }



 public boolean deleteCoach(Coach U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "deletecoachmobile/"+U.getId();
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



 public boolean deleteReservation(reservation U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "deletereservationmobile/"+U.getId();
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

public boolean ModifyCoach(Coach t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updatecoachmobile/";
        req.setUrl(url);
       System.out.println(url);
    req.addArgument("id", t.getId()+"");
       req.addArgument("name", t.getName());
       req.addArgument("lastname", t.getLastname());
       req.addArgument("categorie", t.getCategorie());
       req.addArgument("rank", t.getRank()+"");
        System.out.println(Statics.BASE_URL + "updatecoachmobile/");
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
public boolean ModifyReservation(reservation t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updatereservationmobile/";
        req.setUrl(url);
       System.out.println(url);
    req.addArgument("id", t.getId()+"");
       req.addArgument("start", t.getTempsstart());
       req.addArgument("end", t.getTempsend());
 if(t.isDispo()==true) 
{
req.addArgument("dispo","1");
}
else if(t.isDispo()==false) 
{
req.addArgument("dispo","0");
}
       req.addArgument("coach", t.getCoach()+"");
       
        System.out.println(Statics.BASE_URL + "updatereservationmobile/");
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
