/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Publication;
import com.esprit.gui.BaseForm;
import com.esprit.services.PublicationService;

/**
 *
 * @author Lenovo
 */
public class ModifiePublicationform extends BaseForm {
    
    Form current;
    public ModifiePublicationform(Resources res , Publication r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Publicationte = new TextField(r.getPublication_text(), "Publication" , 20 , TextField.ANY);
//        TextField description = new TextField(r.getObjet() , "Description" , 20 , TextField.ANY);
//               TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
//        ComboBox etatCombo = new ComboBox();
//        
//        etatCombo.addItem("Non Traiter");
//        
//        etatCombo.addItem("Traiter");
//        
//   
//        
        
        
        
        
     
        Publicationte.setUIID("NewsTopLine");
       
        
     
        Publicationte.setSingleLineTextArea(true);
     
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
         
           r.setPublication_text(Publicationte.getText());
           
          
      
       
       //appel fonction modfier reclamation men service
       
       if(PublicationService.getInstance().EditPublication(r)) { // if true
           new ListPublicationForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListPublicationForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Publicationte),
              
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
    }
}
