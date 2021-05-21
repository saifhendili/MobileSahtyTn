/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.gif.GifImage;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.CN.log;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Commentaire;
import com.esprit.entity.Publication;
import com.esprit.gui.BaseForm;
import com.esprit.services.CommentaireService;
import com.esprit.services.PublicationService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class displayPublicationForm extends BaseForm {
    
    Form current;
    public displayPublicationForm(Image img ,Resources res , Publication rec) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
  current=this;
    //    livraisonTheme = res;
        Toolbar tb = new Toolbar(true);

        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Affciher Publication ");
        getContentPane().setScrollVisible(false);

       tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListPublicationForm(res).show());
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
         TextField Publicationtext = new TextField("", "Publication");
         
         
          Button btnValider = new Button("Add");
          FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_TO_PHOTOS);
          ImageViewer ls = new ImageViewer();
    Label lbph = new Label();
        
          ShareButton sb = new ShareButton();
        sb.setText("Share Screenshot");
        Image screenshot = Image.createImage(getWidth(), getHeight());
super.revalidate();
super.setVisible(true);
super.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath()+ "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile+"sa.jpeg")) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_JPEG, 1);
} catch(IOException err) {
    Log.e(err);
}
sb.setImageToShare(imageFile, imageFile);
        
     int height =Display.getInstance().convertToPixels(11.5f);
       int width =Display.getInstance().convertToPixels(14f);
        Button image =new Button(img.fill(width,height));
       image.setUIID("Label");
       Container cnt=BorderLayout.west(image);
       Label publicationtext=new Label(rec.getPublication_text(),"NewsTopLine");
        
            Label user_name=new Label(rec.getNom_user(),"NewsTopLine");
            //  Label userlabelcomm=new Label("Commentaire","NewsTopLine3");
           publicationtext.setAlignment(33);
     
                   Label like=new Label(" ");
        like.setUIID("NewsTopLine3");
        Style likestyle =new Style(like.getUnselectedStyle());
        likestyle.setFgColor(0xf7ad02);
        String mytest2 ;
 mytest2 =PublicationService.getInstance().AfficherLike(rec);
if(PublicationService.getInstance().AfficherLike(rec)=="true"){
  //  System.out.println("rani true");
   
        FontImage likeImage =FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, likestyle);
        like.setIcon(likeImage);
        like.setTextPosition(LEFT);     
}else{
  //         System.out.println("rani false:"+PublicationService.getInstance().AfficherLike(rec));
      
        FontImage likeImage =FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_OUTLINE, likestyle);
        like.setIcon(likeImage);
        like.setTextPosition(LEFT);   
}
like.addPointerPressedListener(l->{
                   InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking(); 
     PublicationService.getInstance().AjoutLike(rec);
  //   mytest2 =PublicationService.getInstance().AfficherLike(rec);
      
    new displayPublicationForm(img,res,rec).show();
        });
     

            cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                  BoxLayout.encloseX(user_name),
                BoxLayout.encloseX(publicationtext),
              BoxLayout.encloseX(   createLineSeparator(),sb,//ligne de séparation
                createLineSeparator(),like,
   createLineSeparator()
  //ligne de séparation
            ))
            
          
            );
            
     add(cnt);
        show();
         String url4 = "http://127.0.0.1:8000/uploads/sa.png";
   //  String url3="";
        Image placeHolder2=Image.createImage(120,90);
        EncodedImage enc2= EncodedImage.createFromImage(placeHolder2,false);
        URLImage urli3 =URLImage.createToStorage(enc2, url4, url4, URLImage.RESIZE_SCALE);
          addButton2(urli3,img,res,rec);
          Container cc=new Container();
          cc.add(createLineSeparator(00000));
         ArrayList<Commentaire>list=CommentaireService.getInstance().AfficherCommentaire();
              for(Commentaire rec2:list){
 
        
        
 String url3 = "http://127.0.0.1:8000/uploads/sa.png";
   //  String url3="";
        Image placeHolder=Image.createImage(120,90);
        EncodedImage enc= EncodedImage.createFromImage(placeHolder,false);
        URLImage urli =URLImage.createToStorage(enc, url3, url3, URLImage.RESIZE_SCALE);
      
    addButton(urli,rec2,res,rec);

    ScaleImageLabel images =new ScaleImageLabel(urli);
Container cont = new Container();
images.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

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

    private void addButton(  Image img ,Commentaire rec2, Resources res,Publication pub) {
       int height =Display.getInstance().convertToPixels(11.5f);
       int width =Display.getInstance().convertToPixels(5f);
     
        Button image =new Button(img.fill(width,height));
       image.setUIID("Label");
       Container cnt=BorderLayout.west(image);
       Label publicationtext=new Label(rec2.getCommentaire_text(),"NewsTopLine");
        Label nomuser=new Label(rec2.getNom_user(),"NewsTopLine");
         Label prenomuser=new Label(rec2.getPrenom_user(),"NewsTopLine");
       
//        TextArea ta=new TextArea(nom_user);
//        ta.setUIID("NewsTopLine");
//        ta.setEditable(false);
//        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));

        Label lSupp=new Label(" ");
        lSupp.setUIID("NewsTopLine");
        Style supprimerStyle =new Style(lSupp.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage =FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupp.setIcon(supprimerImage);
        lSupp.setTextPosition(RIGHT);
        
        lSupp.addPointerPressedListener(l->{
                           InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking(); 
        Dialog dig =new Dialog("Suppression");
        if(dig.show(",suppression","Vous voulez Supprimer ce reclamation ?","Annuler","Oui")){
        dig.dispose();
        }else {
        
        dig.dispose();
         
        if(CommentaireService.getInstance().deleteCommentaire(rec2.getId())){
          new displayPublicationForm(img,res,pub).show();
     
        }
        
        
        
        }        
        });
   
        
            Label lModi=new Label(" ");
        lModi.setUIID("NewsTopLine");
        Style modifStyle =new Style(lModi.getUnselectedStyle());
        modifStyle.setFgColor(0xf7ad02);
        FontImage modifieImage =FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifStyle);
        lModi.setIcon(modifieImage);
        lModi.setTextPosition(LEFT);
//        
//        lModi.addPointerPressedListener(l->{
//     new ModifiePublicationform(res,rec).show();
//        
//        });
//        
//        nomuser.addPointerPressedListener(l->{
//     new displayPublicationForm(img,res,rec).show();
//        
//        });
//        prenomuser.addPointerPressedListener(l->{
//     new displayPublicationForm(img,res,rec).show(); });
//        
//           image.addPointerPressedListener(l->{
   //  new displayPublicationForm(img,res,rec).show(); });
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                  BoxLayout.encloseX(nomuser,prenomuser),
                BoxLayout.encloseX(publicationtext,lModi,lSupp)));
        
        
        
        add(cnt);
    }

    private void addButton2(Image img,Image img2,Resources res,Publication rec) {
         int height =Display.getInstance().convertToPixels(11.5f);
       int width =Display.getInstance().convertToPixels(19f);
        Label prenomuser=new Label("Ajouter","NewsTopLine3");
        Button image =new Button(img.fill(width,height));
       image.setUIID("Label");
       Container cnt=BorderLayout.west(image);
     
       FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
     //   FloatingActionButton pub = fab.createSubFAB(FontImage.MATERIAL_ADD_TASK,"publication");
        fab.addActionListener(e->
        {
                  InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking(); 
                new AjouterCommentaireForm(img2,res,rec).show();});
        
  
//        
//        lModi.addPointerPressedListener(l->{
//     new ModifiePublicationform(res,rec).show();
//        
//        });
//        
//        nomuser.addPointerPressedListener(l->{
//     new displayPublicationForm(img,res,rec).show();
//        
//        });
//        prenomuser.addPointerPressedListener(l->{
//     new displayPublicationForm(img,res,rec).show(); });
//        
//           image.addPointerPressedListener(l->{
   //  new displayPublicationForm(img,res,rec).show(); });
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                  BoxLayout.encloseX(prenomuser),
       BoxLayout.encloseX(fab)
        ));
        
        
        
        add(cnt);
    }
    
}
