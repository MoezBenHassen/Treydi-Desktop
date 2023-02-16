package Entities;

import java.util.Date;

public class Livraison {
    private int id;
    private Date date_livraison;
    private String etat_livraison;
    private String adresse_livraison;
    private int id_livreur;
    private int id_echange;

    public Livraison() {
    }

    public Livraison(String etat_livraison, String adresse_livraison, int id_livreur, int id_echange) {
        this.etat_livraison = etat_livraison;
        this.adresse_livraison = adresse_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
    }

    public Livraison(int id, Date date_livraison, String etat_livraison, String adresse_livraison, int id_livreur, int id_echange) {
        this.id = id;
        this.date_livraison = date_livraison;
        this.etat_livraison = etat_livraison;
        this.adresse_livraison = adresse_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
    }
    public Livraison(Date date_livraison, String etat_livraison, String adresse_livraison, int id_livreur, int id_echange) {
        this.date_livraison = date_livraison;
        this.etat_livraison = etat_livraison;
        this.adresse_livraison = adresse_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    @Override
    public String toString() {
        return "\nLivraison{" +
                "date_livraison='" + date_livraison + '\'' +
                ", etat_livraison='" + etat_livraison + '\'' +
                ", adresse_livraison='" + adresse_livraison + '\'' +
                ", id_livreur=" + id_livreur +
                ", id_echange=" + id_echange +
                "}";
    }
}
