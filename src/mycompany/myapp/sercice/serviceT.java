/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.myapp.sercice;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import mycompany.myapp.entities.Tournoi;
import mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Taha
 */
public class serviceT {
      public ArrayList<Tournoi> tasks;
    
    public static serviceT instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private serviceT() {
         req = new ConnectionRequest();
    }

    public static serviceT getInstance() {
        if (instance == null) {
            instance = new serviceT();
        }
        return instance;
    }

    public boolean addTask(Tournoi t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "create";
    
       req.setUrl(url);
       
       req.addArgument("name", t.getName());
       req.addArgument("cathegorie", t.getCathegorie()+"");
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
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Tournoi t = new Tournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                if ((obj.get("name")==null) && (obj.get("cathegorie")==null))
                   t.setName("null");
                else
                    t.setName(obj.get("name").toString());
                    t.setCathegorie(obj.get("cathegorie").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Tournoi> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"get/";
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
    
}
