/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.SignatureComponent;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entity.FormAide;
import com.esprit.entity.RDV;
import com.esprit.entity.Utilisateur;
import com.esprit.services.ProduitAideService;

import com.esprit.services.ServiceRDV;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ANIS_LENOVO
 */
public class AddRDVForm extends BaseForm{
  Form current;  
    public AddRDVForm(Resources res) {
        current = this;
        this.addSideMenu(res);
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un nouveau RDV");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","Nom et Prénom");
        TextField tfnommed= new TextField("", "nommed");
        TextField tfdate= new TextField("", "date");
        
           Vector<String> type;
        type = new Vector();
         ArrayList<Utilisateur>list=ServiceRDV.getInstance().getAllUsers();
    for(Utilisateur rec:list){
        if(rec.getType().contains("Medecin"))
        type.add(rec.getNom()+rec.getPrenom());
        }
        ComboBox<String>roles = new ComboBox<>(type);
        
           SignatureComponent sig = new SignatureComponent();
        sig.addActionListener((evt) -> {
           
            Image img = sig.getSignatureImage();});
         System.out.println("The signature was changed"+sig+"nooo");
        
       Picker datePicker = new Picker();
       datePicker.setType(Display.PICKER_TYPE_DATE);

        Button btnValider = new Button("Ajouter RDV");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0))
                    Dialog.show("Alerte", "Veuillez remplir tous les champs svp!", new Command("OK"));
                else
                    
                {
                    try {
                        RDV r = new RDV(tfnom.getText(),roles.getSelectedItem().toString(),datePicker.getText());
                        if( ServiceRDV.getInstance().addRDV(r))
                            
                            
                            
                        {  Dialog.show("Success","RDV bien ajouté",new Command("OK"));
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        ToastBar.getInstance().setPosition(BOTTOM);
           ToastBar.Status status = ToastBar.getInstance().createStatus();
           status.setShowProgressIndicator(true);
           
       //    status.setIcon(res.getImage("tick.jpg").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
           status.setMessage("Votre rendez-vous est ajouté avec succés");
           status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear
           status.show();
           
           
           
                 
 
      
           
           
           
           
           
 
       
           
           
           
           
           
           
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
           new ListRDVForm(res).show();

                 refreshTheme();
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", " ", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
                  
                 
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
           
        addAll(tfnom,roles,datePicker,sig,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->new DisplayHomeForm(res).show() );
        // Revenir vers l'interface précédente     previous.showBack()
                
    }
    
    
}
