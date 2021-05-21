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
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.esprit.entity.Categorie;
import com.esprit.entity.FormAide;

import com.esprit.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saif
 */
public class ProduitAideService {
    
    public static ProduitAideService instance=null;
    public static boolean resultOK;
    
    
   private ConnectionRequest req;
    public static ProduitAideService getInstance(){
        if(instance==null)
    instance =new ProduitAideService();
        return instance;
}
    
    public ProduitAideService(){
    
    req =new ConnectionRequest();
            }
    
    public void AjoutProduit(FormAide publication, ComboBox<String>roles){
        String url=Statics.BASE_URL+"/form/aide/new/"+publication.getIdUser()+"/"+publication.getNom()+"/"+publication.getPrenom()+"/"+publication.getImg()+"/"+publication.getTextpub()+"/"+publication.getQuantit()+"/"+roles.getSelectedItem().toString();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
    }
    
    
    
   
    
    
    
    
    
        public void AjoutRendezvous(FormAide publication){
        String url=Statics.BASE_URL+"/reservaide/new/1/"+publication.getId()+"/"+publication.getVille()+"/"+publication.getImg();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
      String str =new String(req.getResponseData());// hedhy json li tjina f nav 
            System.out.println("data=="+str);
            
        });
                NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
    }
    
    
    
    
    //affichage
    
    public ArrayList<FormAide>AfficherProduit(){
        
        ArrayList<FormAide> result =new ArrayList();
        req.setUrl(Statics.BASE_URL+"/form/aide/");
            req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp =new JSONParser();
                try{
                    Map<String,Object>mapPublication=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapPublication.get("root");
             
                    for(Map<String,Object> obj : listOfMaps){
                    FormAide pub =new FormAide();
                    float id =Float.parseFloat(obj.get("id").toString());
                     
                    String idUser=obj.get("idUser").toString();
                     String nom_user=obj.get("nom").toString();
                     String prenom_user=obj.get("prenom").toString();
                        String img=obj.get("img").toString();
                     String textpub=obj.get("textpub").toString();
             float quantit =Float.parseFloat(obj.get("quantit").toString());
                String ville=obj.get("ville").toString();
                // 
                  pub.setId((int)id);
                  pub.setIdUser(idUser);
                   
              pub.setTextpub(textpub);
                  pub.setImg(img);
                  pub.setNom(nom_user);
                  pub.setPrenom(prenom_user);
                   pub.setQuantit((int)quantit);
                   pub.setVille(ville);
result.add(pub);
                       
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
 
    public boolean deleteProduit(int id){

        String url=Statics.BASE_URL+"/form/aide/delete/"+id;
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
    public boolean EditProduit(FormAide publication){

        String url=Statics.BASE_URL+"/form/aide/"+publication.getId()+"/edit/"+publication.getIdUser()+"/"+publication.getNom()+"/"+publication.getPrenom()+"/"+publication.getImg()+"/"+publication.getTextpub()+"/"+publication.getQuantit()+"/"+publication.getVille();
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
    
    
    
        public ArrayList<Categorie>getCate(){
        
        ArrayList<Categorie> result =new ArrayList();
        req.setUrl(Statics.BASE_URL+"/categ/");
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
                    Categorie pub =new Categorie();
                    float id =Float.parseFloat(obj.get("id").toString());
                     String nom_user=obj.get("nom").toString();
                 
                  pub.setId((int)id);
                  pub.setName(nom_user);
                   
          
result.add(pub);
                       
                    }
                }catch(Exception ex){
                ex.printStackTrace();
                 
                }
       
            
            }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
      
         return result;
    }
    
    
    
    
    
}
