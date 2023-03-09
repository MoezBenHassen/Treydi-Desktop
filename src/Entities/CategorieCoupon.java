package Entities;


public class CategorieCoupon {
    private int id_categoriecoupon;
    private String nom_categorie, description_categorie;

public CategorieCoupon () {

}

    public CategorieCoupon(String nom_categorie, String description_categorie) {
        this.nom_categorie = nom_categorie;
        this.description_categorie = description_categorie;
    }

    public CategorieCoupon(int id_categoriecoupon, String nom_categorie, String description_categorie) {
    this.id_categoriecoupon= id_categoriecoupon;
    this.nom_categorie=nom_categorie;
    this.description_categorie=description_categorie;
    }

    public int getId_categoriecoupon() {
        return id_categoriecoupon;
    }

    public void setId_categoriecoupon(int id_categoriecoupon) {
        this.id_categoriecoupon = id_categoriecoupon;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescription_categorie() {
        return description_categorie;
    }

    public void setDescription_categorie(String description_categorie) {
        this.description_categorie = description_categorie;
 }

    @Override
    public String toString() {
        return "CategorieCoupon{" +
                "id_categoriecoupon=" + id_categoriecoupon +
                ", nom_categorie='" + nom_categorie + '\'' +
                ", description_categorie='" + description_categorie + '\'' +
                '}';
    }
}