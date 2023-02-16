package Entities;

public class Utilisateur {
    private int id,score, tel;
    private String nom, prenom, image_url, email, adresse, interet;

    public Utilisateur(){}

    public Utilisateur(int id, String prenom, String nom, String image_url) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image_url = image_url;
    }

    public Utilisateur(int id, String nom, String prenom, String image_url, int tel, String email, String adresse, String interet, int score) {
        this.id = id;
        this.score = score;
        this.nom = nom;
        this.prenom = prenom;
        this.image_url = image_url;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.interet = interet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getInteret() {
        return interet;
    }

    public void setInteret(String interet) {
        this.interet = interet;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
