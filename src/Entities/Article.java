package Entities;

import java.util.Date;

public class Article {
// ATTTRIBUTE
    private int id_article;
    private String titre, description, contenu;
    private Date date_publication;
    private int id_categorie;
    private int archived;
    private int id_user;
    private String auteur;
    private float AvgRating;

    public float getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(float average_ratings) {
        this.AvgRating = average_ratings;
    }


//CONSTRUCTORS
    public Article() {
    }


    public Article(String titre, String description, int id_categorie) {
        this.titre = titre;
        this.description = description;
        this.id_categorie = id_categorie;
    }

    public Article(int id_article, String titre, String description, String contenu, int id_categorie, String auteur) {
        this.id_article = id_article;
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.id_categorie = id_categorie;
        this.auteur = auteur;
    }

    public Article(String titre, String description, String contenu, int id_categorie, String auteur) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.id_categorie = id_categorie;
        this.auteur = auteur;
    }

    //constructor without ID_ARTICLE
    public Article(String titre, String description, String contenu, Date date_publication, int id_categorie, int archived, int id_user, String auteur) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.date_publication = date_publication;
        this.id_categorie = id_categorie;
        this.archived = archived;
        this.id_user = id_user;
        this.auteur = auteur;
    }

    //CONSTRUCTOR WITH ID_ARTICLE
    public Article(int id_article, String titre, String description, String contenu, Date date_publication, int id_categorie, int archived, int id_user, String auteur) {
        this.id_article = id_article;
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.date_publication = date_publication;
        this.id_categorie = id_categorie;
        this.archived = archived;
        this.id_user = id_user;
        this.auteur = auteur;
    }



    //ADDITIONAL METHODS
    @Override
    public String toString() {
        return "\nArticle{" +
                "id_article=" + id_article +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", contenu='" + contenu + '\'' +
                ", date_publication=" + date_publication +
                ", id_categorie=" + id_categorie +
                ", archived=" + archived +
                ", id_user=" + id_user +
                ", auteur='" + auteur + '\'' +
                '}';
    }

    //GETTERS AND SETTERS
    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }


}
