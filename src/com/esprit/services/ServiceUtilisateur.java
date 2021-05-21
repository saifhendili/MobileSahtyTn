/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.esprit.utils.Statics;

import com.esprit.gui.ProfileForm;
import com.esprit.gui.SessionManager;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

   
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }

    public void signup(TextField email,TextField nom,TextField prenom,TextField password,TextField adress, ComboBox<String>type ,TextField speciality, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup/"+email.getText().toString()+"/"+nom.getText().toString()+
                "/"+prenom.getText().toString()+"/"+password.getText().toString()+"/"+adress.getText().toString()+"/"+type.getSelectedItem().toString()+"/"+speciality.getText().toString();
        
        req.setUrl(url);
        req.setPost(false);
      //contrôle de saisie  
        if(nom.getText().equals(" ") && prenom.getText().equals(" ")&& password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
       
        req.addResponseListener((e)-> {
         
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data); 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
      
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/signin/"+email.getText().toString()+"/"+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPassword(user.get("prenom").toString());
                SessionManager.setEmail(user.get("email").toString());
                
       
                
                if(user.size() >0 ) 
               //   
                    new ProfileForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
                     Dialog.show("Veuillez vérifie les données","", "Annuler", "ok");
            }
            
            
            
        });
    
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

 
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail/"+email;
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

    public static void EditUser(String nom, String password, String email
    ){
        
    String url = Statics.BASE_URL+"/user/editUser?email="+email+ "&nom="+nom+
                "\"&password=\""+password+"\"";
               MultipartRequest req = new MultipartRequest();
                
               req.setUrl(url);
               req.setPost(true);
               req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("email", email);
             
               req.addArgument("nom", nom);
//              req.addArgument("prenom", prenom);
               req.addArgument("password", password);
//                   req.addArgument("adress", adress);
//                        req.addArgument("type", type);
                            
               System.out.println(email);
               req.addResponseListener((response)-> {
                   
                   byte[] data = (byte[]) response.getMetaData();
                   String s = new String(data);
                   System.out.println(s);
                  
               });
               NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    
}
