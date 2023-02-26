package Entities;

public class Item {
    private int id_item ;
    private String libelle ;
    private String description ;

    public enum type {
        Physique,
        Virtuelle,
        Service
    }
    public enum state {
        Neuf,
        Occasion,
        Null
    }
    private type type ;
    private state etat ;

    private String imageurl ;
    private int id_user ;

    private int id_categorie ;

    private int id_echange ;

    public Item() {
    }

    public Item(int id_item, String libelle, String description, Item.type type, state etat, String imageurl, int id_user, int id_categorie, int id_echange) {
        this.id_item = id_item;
        this.libelle = libelle;
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.imageurl = imageurl;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.id_echange = id_echange;
    }

    public Item(String libelle, String description, Item.type type, state etat, String imageurl, int id_user, int id_categorie, int id_echange) {
        this.libelle = libelle;
        this.description = description;
        this.type = type;
        this.etat = etat;
        this.imageurl = imageurl;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.id_echange = id_echange;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item.type getType() {
        return type;
    }

    public void setType(Item.type type) {
        this.type = type;
    }

    public state getEtat() {
        return etat;
    }

    public void setEtat(state etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id_item=" + id_item +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", etat=" + etat +
                ", imageurl='" + imageurl + '\'' +
                ", id_user=" + id_user +
                ", id_categorie=" + id_categorie +
                ", id_echange=" + id_echange +
                '}';
    }
}
