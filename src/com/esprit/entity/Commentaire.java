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
public class Commentaire {
    private int id;
    private String id_user;
    private String id_publication;
    private String commentaire_text;
    private String nom_user ;
    private String prenom_user ;

    public Commentaire() {
    }

    
    
    public Commentaire(int id, String id_user, String id_publication, String commentaire_text, String nom_user, String prenom_user) {
        this.id = id;
        this.id_user = id_user;
        this.id_publication = id_publication;
        this.commentaire_text = commentaire_text;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
    }

    public Commentaire(String id_user, String id_publication, String commentaire_text, String nom_user, String prenom_user) {
        this.id_user = id_user;
        this.id_publication = id_publication;
        this.commentaire_text = commentaire_text;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_publication() {
        return id_publication;
    }

    public void setId_publication(String id_publication) {
        this.id_publication = id_publication;
    }

    public String getCommentaire_text() {
        return commentaire_text;
    }

    public void setCommentaire_text(String commentaire_text) {
        this.commentaire_text = commentaire_text;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }
    
    
    
    
}
