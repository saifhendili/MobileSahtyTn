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
import com.esprit.entity.Home;

import com.esprit.services.ServiceHome;

/**
 *
 * @author Zeineb Haffar
 */
public class AddHomeForm extends BaseForm{
    Form current;  
    public AddHomeForm(Resources res) {
        current = this;
        this.addSideMenu(res);
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Page d'acceuil");
        setLayout(BoxLayout.y());
        TextField tftitle = new TextField("","title");
        TextField tfdescription = new TextField("","description");
        TextField tfimg= new TextField("", "img1");
        TextField tfimg2= new TextField("", "img2");

        Button btnValider = new Button("Ajouter ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitle.getText().length()==0)||(tfdescription.getText().length()==0)||(tfimg.getText().length()==0)||(tfimg2.getText().length()==0))
                    Dialog.show("Alerte", "Veuillez remplir tous les champs svp!", new Command("OK"));
                else
                {
                    try {
                        Home r = new Home(tftitle.getText(),tfdescription.getText(),tfimg.getText(),tfimg2.getText());
                        if( ServiceHome.getInstance().addHome(r))
                            Dialog.show("Success","RDV bien ajouté",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", " ", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tftitle,tfdescription,tfimg,tfimg2,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->{} );
        // Revenir vers l'interface précédente     previous.showBack()
                
    }
}
