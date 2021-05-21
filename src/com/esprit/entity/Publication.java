/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;


import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author saif
 */

public class Publication {
 public int id;
 public String id_user ;
 public String publication_text; 
 public String img ;
 public String nom_user;
 public String prenom_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication(int id, String id_user, String publication_text, String img, String nom_user, String prenom_user) {
        this.id = id;
        this.id_user = id_user;
        this.publication_text = publication_text;
        this.img = img;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
    }

    public Publication(String id_user, String publication_text, String img, String nom_user, String prenom_user) {
        this.id_user = id_user;
        this.publication_text = publication_text;
        this.img = img;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
    }

    public Publication() {
    }


  

    

    public String getId_user() {
        return id_user;
    }

    public String getPublication_text() {
        return publication_text;
    }

    public String getImg() {
        return img;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

   

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setPublication_text(String publication_text) {
       
               this.publication_text = publication_text;

     //   this.publication_text = publication_text.replaceAll("bb", "**").replace("xx", "**");
        
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publication other = (Publication) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", id_user=" + id_user + ", publication_text=" + publication_text + ", img=" + img + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user  + '}';
    }


   


 
}

