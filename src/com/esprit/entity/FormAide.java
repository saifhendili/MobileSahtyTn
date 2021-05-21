/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author saif
 */
public class FormAide {
 private int id;
    private String IdUser;
    private String nom;
    private String prenom;
    private String img;
     private String textpub;
    private String description;
    private int quantit;
    private String categories;
    private String ville;

    public FormAide(String IdUser, String nom, String prenom, String img, String textpub, int quantit, String categories, String ville) {
        this.IdUser = IdUser;
        this.nom = nom;
        this.prenom = prenom;
        this.img = img;
        this.textpub = textpub;
      
        this.quantit = quantit;
        this.categories = categories;
        this.ville = ville;
    }

    public FormAide(int id, String IdUser, String nom, String prenom, String img, String textpub, String description, int quantit, String categories, String ville) {
        this.id = id;
        this.IdUser = IdUser;
        this.nom = nom;
        this.prenom = prenom;
        this.img = img;
        this.textpub = textpub;
        this.description = description;
        this.quantit = quantit;
        this.categories = categories;
        this.ville = ville;
    }

    public FormAide() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String IdUser) {
        this.IdUser = IdUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTextpub() {
        return textpub;
    }

    public void setTextpub(String textpub) {
        this.textpub = textpub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantit() {
        return quantit;
    }

    public void setQuantit(int quantit) {
        this.quantit = quantit;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "FormAide{" + "id=" + id + ", IdUser=" + IdUser + ", nom=" + nom + ", prenom=" + prenom + ", img=" + img + ", textpub=" + textpub + ", description=" + description + ", quantit=" + quantit + ", categories=" + categories + ", ville=" + ville + '}';
    }

   
   
}
