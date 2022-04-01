///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.services;
//
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import com.mycompany.myapp.entities.Task;
//import com.mycompany.myapp.entities.categorie;
//import com.mycompany.myapp.utils.Statics;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * @author bhk
// */
//public class ServiceTask {
//
////    public ArrayList<Task> tasks;
////    
////    public static ServiceTask instance=null;
////    public boolean resultOK;
////    private ConnectionRequest req;
////
////    private ServiceTask() {
////         req = new ConnectionRequest();
////    }
////
////    public static ServiceTask getInstance() {
////        if (instance == null) {
////            instance = new ServiceTask();
////        }
////        return instance;
////    }
////
////    public boolean addTask(Task t) {
////        System.out.println(t);
////        System.out.println("********");
////       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
////       String url = Statics.BASE_URL + "create";
////    
////       req.setUrl(url);
////       
////       req.addArgument("name", t.getName());
////       req.addArgument("name", t.getTitre());
////       req.addArgument("name", t.getText());
////       req.addArgument("name", t.getJeux());
////       
////       req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
////                req.removeResponseListener(this);
////            }
////        });
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        return resultOK;
////    }
////
////    public ArrayList<Task> parseTasks(String jsonText){
////        try {
////            tasks=new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String,Object> tasksListJson = 
////               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
////            
////            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
////            for(Map<String,Object> obj : list){
////                Task t = new Task();
////                float id = Float.parseFloat(obj.get("id").toString());
////                t.setId((int)id);
////                
////                if (obj.get("name")==null)
////              t.setName("null");
////                else
////                    t.setName(obj.get("name").toString());
////                t.setTitre(obj.get("titre").toString());
////                t.setText(obj.get("text").toString());
////                t.setJeux(obj.get("jeux").toString());
////                tasks.add(t);
////            }
////            
////            
////        } catch (IOException ex) {
////            
////        }
////        return tasks;
////    }
////    
////    public ArrayList<Task> getAllTasks(){
////        //String url = Statics.BASE_URL+"/tasks/";
////        String url = Statics.BASE_URL+"get";
////        req.setUrl(url);
////        req.setPost(false);
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                tasks = parseTasks(new String(req.getResponseData()));
////                req.removeResponseListener(this);
////            }
////        });
////        NetworkManager.getInstance().addToQueueAndWait(req);
////        return tasks;
////    }
//    
//    
//    public static ServiceTask instance = null;
//    private final ConnectionRequest req;
//    public ArrayList<Task> commande;
//    public static boolean resultOK;
//  public ArrayList<Task> task;
//    
//    private ServiceTask() {
//         req = new ConnectionRequest();
//    }
//    
//    public static ServiceTask getInstance(){
//        if(instance==null)
//            instance = new ServiceTask();
//         return instance;
//         
//        
//    }
//    private Object date;
//      public boolean addCategorie(Task p) {
//        String url = Statics.BASE_URL + "/addnewsmobile/?Text=" +p.getText()+ "&Titre=" +p.getTitre()+ "&Jeux=" +p.getJeux()+ "&Date=" +p.getDate();
//      req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//   
//   
//    public boolean addnews(Task commande){
//        String url = Statics.BASE_URL+"/addcostmobile";
//        req.setUrl(url);public ServiceTask(){
//        req.addArgument("Titre", commande.getTitre());
//        req.addArgument("Text", commande.getText());
//        req.addArgument("Jeux", commande.getJeux());
//        req.addArgument("Jeux", commande.getDate()+"");
//         req.addArgument("categorie", commande.getCategorie()+"");
//       
//        System.out.println(url);
//        req.setPost(true);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//        public ArrayList<Task> parseTask(String jsonText){
//        try {
//            commande =new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){
//                
//                
//           /*     Map<String,Object> objType =(Map<String,Object>) obj.get("typequipement_e");
//                
//                float idType=Float.parseFloat(objType.get("idTp").toString());
//                
//                int idTypeI=(int) idType;
//                p.setTypequipement_e(idTypeI); */
//                
//               Task u = new Task();
//               
//                u.setId((int) Float.parseFloat(obj.get("id").toString()));
//                u.setTitre(obj.get("nom").toString());
//                u.setText(obj.get("Text").toString());
//                u.setJeux(obj.get("jeu").toString());
//                u.setDate(obj.get("date").toString());
//                u.setCategorie(obj.get("categorie").toString());
//             
////             
////                 Map<String,Object> objuser =(Map<String,Object>) obj.get("userid");
////                float idUser=Float.parseFloat(objuser.get("id").toString());
////               int idUserI=(int) idUser;
////               p.setUserId(idUserI);
////                
//                Task.add(u);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return commande;
//    }
//        
//        
////    public ArrayList<Task> parseCours(String jsonText){
////        try {
////            commande=new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
////            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
////            for(Map<String,Object> obj : list){
////                Task u = new Task();
////               
////                u.setId((int) Float.parseFloat(obj.get("id").toString()));
////                u.setTitre(obj.get("nom").toString());
////                u.setText(obj.get("Text").toString());
////                u.setJeux(obj.get("jeu").toString());
////                u.setDate(obj.get("date").toString());
////                u.setCategorie(obj.get("categorie").toString());
////              
////
////               
////       
////                commande.add(u);
////            }
////                     
////        } catch (IOException ex) {
////            System.out.println(ex.getMessage());
////        }
////        return commande;
////    }
////        
//        
//    public ArrayList<Task> getAllTAASK(){
//        String url = Statics.BASE_URL+"/getcommandesmobile";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                commande = parseCours(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return commande;
//    }
//    public boolean deleteCommande(int commandeId){
//        String url = Statics.BASE_URL+"/deletecommande?commandeId="+commandeId;
//        req.setUrl(url);
//         req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               req.removeResponseCodeListener(this);
//            }
//        });
//         NetworkManager.getInstance().addToQueueAndWait(req);
//         return resultOK;
//    }
//
//    public Object getAllTasks() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//
//    
//    
//}




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


import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.entities.categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
;

/**
 *
 * @author Raef
 */
public class ServiceNews {
    public ArrayList<News> types;
    
    public static ServiceNews instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceNews() {
         req = new ConnectionRequest();
    }

    public static ServiceNews getInstance() {
        if (instance == null) {
            instance = new ServiceNews();
        }
        return instance;
    }
    
    
   
    public ArrayList<News> parseTypes(String jsonText){
        try {
            types =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
               News p = new News();
                
              int id_et=(int) Float.parseFloat(obj.get("id").toString());

                p.setId((int)id_et);
                p.setTitre(obj.get("Titre").toString());          
                p.setText(obj.get("Text").toString());
                p.setJeux(obj.get("jeu").toString());
                Map<String,Object> Cat = (Map<String,Object>) obj.get("Categorie");
                p.setCategorie(Cat.get("nom").toString());
                p.setDate(obj.get("Date").toString());
                
            
                
                 
                types.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return types;
    }
     
     
     public ArrayList<News> getAllTask(){
       
         
         String url = Statics.BASE_URL+"news/listnewsmobile";      
        req.setUrl(url);
        req.setPost(false);
        
   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                types = parseTypes(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return types;
    }
    
     
     public boolean deleteNews(News p) {
        String url = Statics.BASE_URL + "News/deleteMobiled/" + p.getId();
               
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
     
     
        public boolean addnews(News p) {
            String url = Statics.BASE_URL + "news/add_jason?Text=" +p.getText()+ "&Titre=" +p.getTitre()+ "&Jeux=" +p.getJeux()+ "&Date=" +p.getDate();
       
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
//        public boolean addcategorie(categorie p) {
//        String url = Statics.BASE_URL + "addcategorie/?nom=" +p.getName();
//      req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;}
        
//           public boolean updatenews(Task p) {
//        String url = Statics.BASE_URL + "news/updatemobile/?Text=" +p.getText()+ "&Titre=" +p.getTitre()+ "&Jeux=" +p.getJeux()+ "&Date=" +p.getDate();
//      req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
           
           
           public boolean ModifyCoach(News t) {
        System.out.println(t);
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "updatemobile/";
        req.setUrl(url);
       System.out.println(url);
    req.addArgument("id", t.getId()+"");
       req.addArgument("Titre", t.getTitre());
       req.addArgument("Text", t.getText());
       req.addArgument("jeu", t.getJeux());
       req.addArgument("Date", t.getDate());
       req.addArgument("rank", t.getCategorie()+"");
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
     
    
    
    
    
    
}
