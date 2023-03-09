package Entities;

public class ArticleRating {
    private int id_rating;
    private int id_user;
    private int  id_article;
    private double rating;

    //CONSTRUCTORS
    public ArticleRating(int id_user, int id_article, double rating) {
        this.id_user = id_user;
        this.id_article = id_article;
        this.rating = rating;
    }

    // GETTERS AND SETTERS
    public int getId_rating() {
        return id_rating;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
