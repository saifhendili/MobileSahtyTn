/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author pc doctor
 */
public class Paniervac {
    private int id;
    private String nom;
  private String iduser;
    private String prix;

    private String img; 

    public Paniervac( String nom, String iduser, String prix, String img) {
       
        this.nom = nom;
        this.iduser = iduser;
        this.prix = prix;
        this.img = img;
    }

    public Paniervac() {
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

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
    
    
}
