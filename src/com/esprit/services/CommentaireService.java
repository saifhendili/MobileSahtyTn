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
import com.esprit.entity.Commentaire;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saif
 */
public class CommentaireService {
    
    public static CommentaireService instance=null;
    public static boolean resultOK;
    
    
   private ConnectionRequest req;
    public static CommentaireService getInstance(){
        if(instance==null)
    instance =new CommentaireService();
        return instance;
}
    
    public CommentaireService(){
    
    req =new ConnectionRequest();
            }
    
    public void AjoutCommentaire(Commentaire publication){
        String url=Statics.BASE_URL+"/commentairenew/"+publication.getId_user()+"/"+publication.getId_publication()+"/"+publication.getNom_user()+"/"+publication.getPrenom_user()+"/"+publication.getCommentaire_text();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
    }
    
    //affichage
    
    public ArrayList<Commentaire>AfficherCommentaire(){
        
        ArrayList<Commentaire> result =new ArrayList();
        req.setUrl(Statics.BASE_URL+"/commentaire/");
            req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp =new JSONParser();
                try{
                    Map<String,Object>mapPublication=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapPublication.get("root");
     
                    for(Map<String,Object>obj:listOfMaps){
                    Commentaire pub =new Commentaire();
                    float id =Float.parseFloat(obj.get("id").toString());
                    
                    String idUser=obj.get("idUser").toString();
                    String idPub=obj.get("IdPublication").toString();

                     String commenttext=obj.get("Content").toString();
                        String nom_user=obj.get("NomUser").toString();
                     String prenom_user=obj.get("PrenomUser").toString();
                 
                          System.err.println("TEST3");
                  pub.setId((int)id);
                  pub.setId_user(idUser);
                  pub.setId_publication(idPub);
                  pub.setNom_user(nom_user);
                  pub.setPrenom_user(prenom_user);
                  pub.setCommentaire_text(commenttext);
result.add(pub);
                          System.err.println("TEST4");
                    }
                }catch(Exception ex){
                ex.printStackTrace();
                   System.err.println("TEST5"+ex);
                }
            System.err.println("TEST6");
            
            }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
         System.err.println("TEST8");
         return result;
    }
    
    //show by id

    public boolean deleteCommentaire(int id){

        String url=Statics.BASE_URL+"/commentairedelete/"+id;
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
    public boolean EditPublication(Commentaire publication){

        String url=Statics.BASE_URL+"/commentaire/"+publication.getId()+"/edit/"+publication.getId_user()+"/"+publication.getId_publication()+"/"+publication.getNom_user()+"/"+publication.getPrenom_user()+"/"+publication.getCommentaire_text();
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
}
