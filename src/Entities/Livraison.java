package Entities;

import java.util.Date;

public class Livraison {
    int id_livraison, id_livreur, id_echange;
    String adresse_livraison;
    public enum ETAT {Encours, Termine, Annule};
    private ETAT etat;
    Date date_livraison;


    Livraison() {}

    public Livraison(int id_livreur, int id_echange, String adresse_livraison, ETAT etat) {
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison = adresse_livraison;
        this.etat = etat;
    }

    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison, ETAT etat) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison = adresse_livraison;
        this.etat = etat;
    }

    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison, ETAT etat, Date date_livraison) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison = adresse_livraison;
        this.etat = etat;
        this.date_livraison = date_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
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

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public ETAT getEtat() {
        return etat;
    }

    public void setEtat(ETAT etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "id_livraison=" + id_livraison +
                ", id_livreur=" + id_livreur +
                ", id_echange=" + id_echange +
                ", adresse_livraison='" + adresse_livraison + '\'' +
                ", etat=" + etat +
                ", date_livraison=" + date_livraison +
                '}';
    }
}
