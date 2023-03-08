package Entities;

public class Coupon {
private int id_coupon, id_categoriecoupon, id_user;
private String titre_coupon, description_coupon, code;
private String etat_coupon;

private String date_expiration;



    public Coupon( int id_categoriecoupon, int id_user, String titre_coupon, String description_coupon, String etat_coupon, String code, String date_expiration) {
        this.id_categoriecoupon = id_categoriecoupon;
        this.id_user = id_user;
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
        this.code = code;
        this.date_expiration = date_expiration;
    }

    public Coupon(int id_coupon, int id_categoriecoupon, int id_user, String titre_coupon, String description_coupon, String etat_coupon, String code, String date_expiration) {
        this.id_coupon = id_coupon;
        this.id_categoriecoupon = id_categoriecoupon;
        this.id_user = id_user;
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
        this.code = code;
        this.date_expiration = date_expiration;
    }

    public Coupon(String titre_coupon, String description_coupon, String etat_coupon, String date_expiration, int id_categoriecoupon) {
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

    public Coupon(int id_coupon) {
        this.id_coupon = id_coupon;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id_coupon=" + id_coupon +
                ", id_categoriecoupon=" + id_categoriecoupon +
                ", id_user=" + id_user +
                ", titre_coupon='" + titre_coupon + '\'' +
                ", description_coupon='" + description_coupon + '\'' +
                ", etat_coupon='" + etat_coupon + '\'' +
                ", code='" + code + '\'' +
                ", date_expiration='" + date_expiration + '\'' +
                '}';
    }
}

