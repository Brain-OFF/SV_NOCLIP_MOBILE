/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.myapp.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import mycompany.myapp.sercice.serviceT;
/**
 *
 * @author Taha
 */
public class ListTournoi  extends Form {

    public ListTournoi(Form previous) {
        setTitle("List tournoi");

        SpanLabel sp = new SpanLabel();
        sp.setText(serviceT.getInstance().getAllTasks().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
}
