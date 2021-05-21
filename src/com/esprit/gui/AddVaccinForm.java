/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Vaccin;
import com.esprit.services.ServiceVaccin;


/**
 *
 * @author bhk
 */
public class AddVaccinForm extends BaseForm{
Form current;
    public AddVaccinForm(Resources res) {
        current = this ;
        this.addSideMenu(res);
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un nouveau vaccin");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","nom du vaccin");
        TextField tfIdPharmacie = new TextField("","id_pharmacie");
        TextField tfDescription = new TextField("","description");
        TextField tfPrix= new TextField("", "prix");
        TextField tfQuantite = new TextField("","quantité");
        TextField tfIMG = new TextField("","image");
        Button btnValider = new Button("Ajouter vaccin"); 
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfIdPharmacie.getText().length()==0) || (tfDescription.getText().length()==0)||(tfPrix.getText().length()==0) ||(tfQuantite.getText().length()==0) || (tfIMG.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       // Vaccin t = new Vaccin(Integer.parseInt(tfStatus.getText()), tfName.getText());
                         Vaccin t = new Vaccin(tfName.getText(),tfIdPharmacie.getText(),tfDescription.getText(),tfPrix.getText(),tfQuantite.getText(),tfIMG.getText() );
                        if( ServiceVaccin.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfIdPharmacie,tfDescription,tfPrix,tfQuantite,tfIMG,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e->new DisplayHomeForm(res).showBack() ); // Revenir vers l'interface précédente
                //previous.showBack()
    }
    
    
}
