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
import com.mycompany.myapp.entities.Games;
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
public class ServiceGames {

    public ArrayList<Games> Games;
    
    public static ServiceGames instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGames() {
         req = new ConnectionRequest();
    }

    public static ServiceGames getInstance() {
        if (instance == null) {
            instance = new ServiceGames();
        }
        return instance;
    }

    public boolean addGames(Games t) {
 
        String url = Statics.BASE_URL + "addgamesm" ; //création de l'URL
 System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
 req.addArgument("name", t.getName());
        req.addArgument("desc", t.getDescreption());
        req.addArgument("prix", t.getPrix()+"");
       req.addArgument("img", t.getImg());
        req.addArgument("cat", t.getCat()+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service Game, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Games> parseGames(String jsonText){
        try {
            Games=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> GamesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));  
            List<Map<String,Object>> list = (List<Map<String,Object>>)GamesListJson.get("root");
            for(Map<String,Object> obj : list){
                Games t = new Games();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setName(obj.get("name").toString());
                t.setDescreption(obj.get("descreption").toString());
                t.setPrix((int)Float.parseFloat(obj.get("prix").toString())); 
                t.setImg(obj.get("img").toString());               
                Games.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return Games;
    }
    public boolean deletegames(Games U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "removegamesmob/"+U.getId();
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
    public boolean favorite(Games U,User Us) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "addtofavmboile";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("game", U.getId()+"");
       req.addArgument("user", Us.getId()+"");
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
    public ArrayList<Games> getAllGames(){
        String url = Statics.BASE_URL+"getgamessmobile";
          System.out.println(url);

        req.setUrl(url);
//        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Games = parseGames(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Games;
    }
    public ArrayList<Games> getAllGamesFav(User Us){
        String url = Statics.BASE_URL+"getgamessmobilefav";
          System.out.println(url);
       req.addArgument("user", Us.getId()+"");
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Games = parseGames(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Games;
    }
public ArrayList<Games> getAllGamescat(int cat){
        String url = Statics.BASE_URL+"getgamessmobilecat";
          System.out.println(url);

        req.setUrl(url);
        req.addArgument("cat",cat+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Games = parseGames(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Games;
    }
public boolean ModifyGames(Games U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updategamesmobile";
       System.out.println(url);
       req.setUrl(url);
       req.addArgument("id", U.getId()+"");
       req.addArgument("name", U.getName());
       req.addArgument("desc", U.getDescreption());
       req.addArgument("prix", U.getPrix()+"");
       req.addArgument("img", U.getImg());

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
public boolean AddPanierProduit(Games p) {
        String url = Statics.BASE_URL + "/panier/add_jason/" + p.getId();
               
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
 
         public ArrayList<Games> getProduitsPanierMobile(){
       
         
         String url = Statics.BASE_URL+"/panierf_jason";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Games = parseGames(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        return Games;
    }
}
