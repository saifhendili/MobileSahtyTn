/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SignatureComponent;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.gif.GifImage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.CN.log;
import static com.codename1.ui.CN.log;
import static com.codename1.ui.CN.log;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.codename1.ui.CN.log;
import static com.codename1.ui.CN.log;
import static com.codename1.ui.CN.log;
import com.codename1.ui.ComboBox;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.plaf.UIManager;
import com.esprit.entity.Publication;
import com.esprit.services.PublicationService;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Letaief Sofiene
 */
public class AjoutPublicationForm extends BaseForm {
    private Resources livraisonTheme;
    private final String stat = "En attente";
    private final String naturerec = "Article";
    String pathImage="";
    String des ;
    int idart;
    boolean result;
    public AjoutPublicationForm(Resources res ) {

//        super("Livraison", BoxLayout.y());
//        livraisonTheme = res;
//        Toolbar tb = new Toolbar(true);
//
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        getContentPane().setScrollVisible(false);
        super("Ajouter Publucation", BoxLayout.y());
        livraisonTheme = res;
        Toolbar tb = new Toolbar(true);

        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("article reclamation ");
        getContentPane().setScrollVisible(false);
      super.addSideMenu(res);
        //     tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new AjoutPublicationForm(res).show());
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("delivery.png"), spacer1, " ", " ", "All Deliveries ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        radioContainer.setScrollableY(true);
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
         Container c = new Container(new FlowLayout(Component.CENTER));
         TextField Publicationtext = new TextField("", "Ajouter Publication");
         
         
          Button btnValider = new Button("Add");
          FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_TO_PHOTOS);
          ImageViewer ls = new ImageViewer();
    Label lbph = new Label();
        
        fab.addActionListener(l-> {
             pathImage = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
            System.out.println(pathImage);
//                  FileSystemStorage fs = FileSystemStorage.getInstance();
//                        String recordingsDir = "C:\\xampp\\htdocs\\open-quantum-web\\web\\uploadMobile\\" + "recordings\\";
//                         fs.mkdir(recordingsDir);
//  
            if(pathImage == null) {
                showToast("User canceled Camera");
                return;
            }
            setImage(pathImage, ls);
        });
          ComboBox cb = new ComboBox();
//           ArticleService rec1 = ArticleService.getInstance(); 
//           for (Article ar : rec1.getallArticles()){
//          cb.addItem(ar.getIdArticle()+"-"+ar.getDesignation());}
           
            cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 des = cb.getSelectedItem().toString();
            }
        });

            
            
            

        
        
        
        
        
        
        
          c.add(Publicationtext);
          addAll(c,fab,ls,cb,btnValider);
            btnValider.addActionListener(e->{
       try{
            
            if(Publicationtext.getText().equals("")){
                Dialog.show("Veuillez vérifie les données","", "Annuler", "ok");
            }else{
                InfiniteProgress ip =new InfiniteProgress();
                final Dialog iDialog=ip.showInfiniteBlocking();
                Publication p =new Publication("1",String.valueOf(Publicationtext.getText()).toString(),"img","saif","hendili");
                PublicationService.getInstance().AjoutPublication(p);
                iDialog.dispose();
                new ListPublicationForm(res).show();
                refreshTheme();
                    
         }
    }catch(Exception ex){
       ex.printStackTrace();
               }
        
        });
}
    
    private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (Exception ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
    }

    private void addTab(Tabs swipe, Image image, Label spacer1, String likesStr, String commentsStr, String all_Deliveries_) {
         int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        if(img.getHeight() < size) {
//            img = img.scaledHeight(size);
//        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
//        ScaleImageLabel image = new ScaleImageLabel(img);
//        image.setUIID("Container");
//        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        ScaleImageLabel imgg = new ScaleImageLabel();
        try {
            imgg = new ScaleImageLabel(GifImage.decode(getResourceAsStream("/publication.gif"), 5156565));
        } catch (IOException err) {
            log(err);
        }
        imgg.setUIID("Container");
        imgg.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Container page1
                = LayeredLayout.encloseIn(
                        imgg,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY( //                            new SpanLabel(text, "LargeWhiteText"),
                                //                            FlowLayout.encloseIn(likes, comments),
                                //                            spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    
    }
     private void showToast(String user_canceled_Camera) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(user_canceled_Camera);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }

}
