/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParseCallback;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entity.FormAide;
import com.esprit.entity.Likes;
import com.esprit.entity.Publication;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saif
 */
public class PublicationService {
    
    public static PublicationService instance=null;
    public static boolean resultOK;
        public static String tesst;

    
   private ConnectionRequest req;
    public static PublicationService getInstance(){
        if(instance==null)
    instance =new PublicationService();
        return instance;
}
    
    public PublicationService(){
    
    req =new ConnectionRequest();
            }
    
    public void AjoutPublication(Publication publication){
        String url=Statics.BASE_URL+"/publication/new/"+publication.getId_user()+"/"+publication.publication_text+"/"+publication.getImg()+"/"+publication.getNom_user()+"/"+publication.getPrenom_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
    }
    
      public String AjoutLike(Publication rec){
        String url=Statics.BASE_URL+"/publication/likesett/1/"+rec.getId();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
                 String s =new String(req.getResponseData());
                return s;
    }
    
    
    
    //affichage
    
    public ArrayList<Publication>AfficherPublication(){
        
        ArrayList<Publication> result =new ArrayList();
        req.setUrl(Statics.BASE_URL+"/publication/");
            req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp;
            jsonp =new JSONParser();
            try{
                Map<String,Object>mapPublication=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapPublication.get("root");
                
                for(Map<String,Object>obj:listOfMaps){
                    Publication pub =new Publication();
                    float id =Float.parseFloat(obj.get("id").toString());
                    
                    String idUser=obj.get("id_user").toString();
                    String publication_text=obj.get("publication_text").toString();
                    String img=obj.get("img").toString();
                    String nom_user=obj.get("nom_user").toString();
                    String prenom_user=obj.get("prenom_user").toString();
                    
                    pub.setId((int)id);
                    pub.setId_user(idUser);
                    pub.setPublication_text(publication_text);
                    pub.setImg(img);
                    pub.setNom_user(nom_user);
                    pub.setPrenom_user(prenom_user);
                    result.add(pub);
                    System.err.println("TEST4");
                }
            }catch(Exception ex){
                ex.printStackTrace();
                System.err.println("TEST5"+ex);
            }
            System.err.println("TEST6");
        });
         NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
         System.err.println("TEST8");
         return result;
    }
    
    //show by id
    public Publication DetaisPublication(int id ,Publication publication){
        String url=Statics.BASE_URL+"/show/"+id;
        req.setUrl(url);
        String str=new String(req.getResponseData());
        req.addResponseListener(e->{
            JSONParser jsonp;
                jsonp =new JSONParser();
                try{
                    Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                  
                 
                
                    
                    String idUser=obj.get("id_user").toString();
                     String publication_text=obj.get("publication_text").toString();
                        String img=obj.get("img").toString();
                     String nom_user=obj.get("nom_user").toString();
                     String prenom_user=obj.get("prenom_user").toString();
             //     publication.setId((int)id);
                  publication.setId_user(idUser);
                  publication.setPublication_text(obj.get("publication_text").toString());
                  publication.setImg(img);
                  publication.setNom_user(nom_user);
                  publication.setPrenom_user(prenom_user);

                     
                    
                }catch(Exception ex){
                ex.printStackTrace();
                }
                System.out.println("data=="+str);
        });
           NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
         return publication;
    }
    
    
    public boolean deletePublication(int id){

        String url=Statics.BASE_URL+"/publication/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         req.removeResponseCodeListener(this);
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
    public boolean EditPublication(Publication publication){

        String url=Statics.BASE_URL+"/publication/"+publication.getId()+"/edit/"+publication.getId_user()+"/"+publication.publication_text+"/"+publication.getImg()+"/"+publication.getNom_user()+"/"+publication.getPrenom_user();
        req.setUrl(url);
        req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         resultOK=req.getResponseCode()==200;
         req.removeResponseCodeListener(this);
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }    
    
    
    
    
    
        public String AfficherLike(Publication rec){
        
            String url=Statics.BASE_URL+"/publication/like/1/"+rec.getId();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            //  String tesst =new String(req.getResponseData());
                  if(str.contains("true")){
                  tesst="true";}
                  else {
                   tesst="false";
                  }
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
     
        return tesst;
        }
     
}
