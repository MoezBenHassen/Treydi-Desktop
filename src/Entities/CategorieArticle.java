package Entities;

public class CategorieArticle {

    //ATTRIUBUTES
    private int id_cat;
    private String libelle_cat;
    private int archived;

    //CONSTRUCTORS

    public CategorieArticle() {
    }

    public CategorieArticle(String libelle_cat) {
        this.libelle_cat = libelle_cat;
    }

    public CategorieArticle(int id_cat, String libelle_cat) {
        this.id_cat = id_cat;
        this.libelle_cat = libelle_cat;
    }

    public CategorieArticle(String libelle_cat, int archived) {
        this.libelle_cat = libelle_cat;
        this.archived = archived;
    }

    public CategorieArticle(int id_cat, String libelle_cat, int archived) {
        this.id_cat = id_cat;
        this.libelle_cat = libelle_cat;
        this.archived = archived;
    }

    //ADDITIONAL METHODS

    @Override
    public String toString() {
        return "\nCategorieArticle{" +
                "id_cat=" + id_cat +
                ", libelle_cat='" + libelle_cat + '\'' +
                ", archived=" + archived +
                '}';
    }

    //GETTER & SETTERS
    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getLibelle_cat() {
        return libelle_cat;
    }

    public void setLibelle_cat(String libelle_cat) {
        this.libelle_cat = libelle_cat;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }
}
