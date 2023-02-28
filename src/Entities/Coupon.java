package Entities;

import java.util.Date;

public class Coupon {
private int id_coupon, id_categoriecoupon;
private String titre_coupon, description_coupon, etat_coupon;
private String date_expiration;


    public Coupon(int id_coupon, String titre_coupon, String description_coupon, String date_expiration, String etat_coupon, int id_categoriecoupon) {
        this.id_coupon = id_coupon;
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
        this.date_expiration = date_expiration;
        this.id_categoriecoupon=id_categoriecoupon;
    }


    public Coupon( String titre_coupon, String description_coupon, String etat_coupon, String date_expiration, int id_categoriecoupon) {
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
        this.date_expiration = date_expiration;
        this.id_categoriecoupon=id_categoriecoupon;
    }

    public Coupon( String titre_coupon, String description_coupon, String date_expiration, String etat_coupon) {
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
    }

    public Coupon() {
    }
    public int getId_coupon() {
        return id_coupon;
    }

    public void setId_coupon(int id_coupon) {
        this.id_coupon = id_coupon;
    }

    public String getTitre_coupon() {
        return titre_coupon;
    }

    public void setTitre_coupon(String titre_coupon) {
        this.titre_coupon = titre_coupon;
    }

    public String getDescription_coupon() {
        return description_coupon;
    }

    public void setDescription_coupon(String description_coupon) {
        this.description_coupon = description_coupon;
    }

    public String getEtat_coupon() {
        return etat_coupon;
    }

    public void setEtat_coupon(String etat_coupon) {
        this.etat_coupon = etat_coupon;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

        public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getId_categoriecoupon() {
        return id_categoriecoupon;
    }

    public void setId_categoriecoupon(int id_categoriecoupon) {
        this.id_categoriecoupon = id_categoriecoupon;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id_coupon=" + id_coupon +
                ", id_categoriecoupon=" + id_categoriecoupon +
                ", titre_coupon='" + titre_coupon + '\'' +
                ", description_coupon='" + description_coupon + '\'' +
                ", etat_coupon='" + etat_coupon + '\'' +
                ", date_expiration=" + date_expiration +
                '}';
    }
}
