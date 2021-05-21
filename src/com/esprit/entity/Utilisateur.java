    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
  //  private String username;
    private String email;
//    private String photo;
    private String nom;
    private String prenom;
    private String password;
        private String adresse;
            private String type;
                        private String speciality;

                        private String reset_token;

    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String nom, String prenom, String password, String adresse, String type, String speciality, String reset_token) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.adresse = adresse;
        this.type = type;
        this.speciality = speciality;
        this.reset_token = reset_token;
    }

    public Utilisateur(String email, String nom, String prenom, String password, String adresse, String type, String speciality, String reset_token) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.adresse = adresse;
        this.type = type;
        this.speciality = speciality;
        this.reset_token = reset_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", adresse=" + adresse + ", type=" + type + ", speciality=" + speciality + ", reset_token=" + reset_token + '}';
    }

            
    
    
}