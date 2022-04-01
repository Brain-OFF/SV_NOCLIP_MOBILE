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
import com.mycompany.myapp.entities.Gamescat;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceGamescat {

    public ArrayList<Gamescat> Gamescat;
    
    public static ServiceGamescat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGamescat() {
         req = new ConnectionRequest();
    }

    public static ServiceGamescat getInstance() {
        if (instance == null) {
            instance = new ServiceGamescat();
        }
        return instance;
    }

    public boolean addcat(Gamescat t) {
 
        String url = Statics.BASE_URL + "addgamescatm" ; //création de l'URL
 System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
 req.addArgument("nom", t.getNom());
        req.addArgument("description", t.getDescription());
   
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

    public ArrayList<Gamescat> parseGamescat(String jsonText){
        try {
            Gamescat=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> GamesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));  
            List<Map<String,Object>> list = (List<Map<String,Object>>)GamesListJson.get("root");
            for(Map<String,Object> obj : list){
                Gamescat t = new Gamescat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                            
                Gamescat.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
    
        return Gamescat;
    }
    public boolean deletegamescat(Gamescat U) {
        System.out.println(U);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "removegamescat/"+U.getId();
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
    public ArrayList<Gamescat> getAllGamescat(){
        String url = Statics.BASE_URL+"getgamesscatmobile";
          System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Gamescat = parseGamescat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Gamescat;
    }

}
