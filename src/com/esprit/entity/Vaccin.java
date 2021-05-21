/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author bhk
 */
public class Vaccin {
    private int id;
  
    private String nom;
    private String id_pharmacie;
    private String description;
    private String prix;
    private String quantity;
    private String img;

    public Vaccin(int id, String nom, String id_pharmacie, String description, String prix, String quantity, String img) {
        this.id = id;
        this.nom = nom;
        this.id_pharmacie = id_pharmacie;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.img = img;
    }

    public Vaccin(String nom, String id_pharmacie, String description, String prix, String quantity, String img) {
        this.nom = nom;
        this.id_pharmacie = id_pharmacie;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.img = img;
    }
    
    
   

  
    public Vaccin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(String id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }




    @Override
    public String toString() {
        return "Vaccin{" + "id=" + id + ", nom=" + nom + ", id_pharmacie=" + id_pharmacie + ", description=" + description + ", prix=" + prix + ", quantity=" + quantity + ", img=" + img + '}';
    }


    
    
    
}
