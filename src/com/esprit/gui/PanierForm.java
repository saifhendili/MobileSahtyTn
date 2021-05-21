/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author ikram
 */
public class PanierForm extends BaseForm {

    Resources theme = UIManager.initFirstTheme("/theme");
    public PanierForm(Resources res)
    {
//         //  super("Total : "+new Panier_Service().total(9),BoxLayout.y());
//            this.add(new InfiniteProgress());
//        Display.getInstance().scheduleBackgroundTask(() -> {
//            // this will take a while...
//
//            Display.getInstance().callSerially(() -> {
//                this.removeAll();
//             for (Panier c : new Panier_Service().findAll(9)) {
//
//            this.add(addItem_Produit_Panier(c));
//
//        }
//               this.revalidate();
//            });
//        });
//
//        this.getToolbar().addSearchCommand(e -> {
//            String text = (String) e.getSource();
//            if (text == null || text.length() == 0) {
//                // clear search
//                for (Component cmp : this.getContentPane()) {
//                    cmp.setHidden(false);
//                    cmp.setVisible(true);
//                }
//                this.getContentPane().animateLayout(150);
//            } else {
//                text = text.toLowerCase();
//                for (Component cmp : this.getContentPane()) {
//                    MultiButton mb = (MultiButton) cmp;
//                    String line1 = mb.getTextLine1();
//                    String line2 = mb.getTextLine2();
//                    mb.setUIIDLine1("libC");
//                    mb.setUIIDLine2("btn");
//                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
//                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
//                    mb.setHidden(!show);
//                    mb.setVisible(show);
//                }
//                this.getContentPane().animateLayout(150);
//            }
//        }, 4);
//        
//               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
//           new MyApplication().start();
//        });
//    }
//    
//       public MultiButton  addItem_Produit_Panier(Panier c) {
//
//      MultiButton m = new MultiButton();
//     m.setTextLine1(c.getDespription());
//        m.setTextLine2(String.valueOf(c.getPrix()));
//        String  url = "http://127.0.0.1:8000/uploads/"+c.getImg();
//         m.setEmblem(theme.getImage("arrow.png"));
//        Image imge;
//        EncodedImage enc;
//        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
//        imge = URLImage.createToStorage(enc, url, url);
//        m.setIcon(imge);
//        m.addActionListener(l
//                -> {
//                    Form f2 = new Form("Detail",BoxLayout.y());
//           
//         
//        
//           
//      
//
//          
//
//                    Button btn= new Button("Remove");
//
//              btn.addActionListener(xxx
//                    -> {
//               
//                  Panier_Service pn = new Panier_Service();
//                  pn.Removeproduit(c.getId());
//
//                  Dialog.show("Remve", "Remve", "OK", null);
//                   new MyApplication().start();
//                
//            }
//            );
//                
//            f2.add("Description: ").add(c.getDespription()).add("Quantite : ").add(String.valueOf(c.getQuantity())).add("Prix : ").add(String.valueOf(c.getPrix())).add(btn);
//            
//            
//            
//            f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
//           new MyApplication().start();
//        });     
// f2.show();
//        
//        });
//        return m;

    }  
}