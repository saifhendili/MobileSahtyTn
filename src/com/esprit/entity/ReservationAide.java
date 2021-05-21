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
public class ReservationAide {
    public String id;
    public String idPatient;
    public String idProduit ;   
    public String produit ;
    public String desc ;

    public ReservationAide(String id, String idPatient, String idProduit, String produit, String desc) {
        this.id = id;
        this.idPatient = idPatient;
        this.idProduit = idProduit;
        this.produit = produit;
        this.desc = desc;
    }

    public ReservationAide(String idPatient, String idProduit, String produit, String desc) {
        this.idPatient = idPatient;
        this.idProduit = idProduit;
        this.produit = produit;
        this.desc = desc;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    
    public ReservationAide(String idPatient, String idProduit) {
        this.idPatient = idPatient;
        this.idProduit = idProduit;
    }

    public ReservationAide(String id, String idPatient, String idProduit) {
        this.id = id;
        this.idPatient = idPatient;
        this.idProduit = idProduit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
  

    
    
}
