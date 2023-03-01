package Entities;

public class Utilisateur {
    private int id, score;
    private String nom, prenom, password, image_url, email, adresse, role, datenaissance;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String image_url, String datenaissance, String email, String adresse, String password, int score, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image_url = image_url;
        this.datenaissance = datenaissance;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
        this.score=score;
        this.role=role;
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", score=" + score +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", image_url='" + image_url + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", role='" + role + '\'' +
                ", datenaissance='" + datenaissance + '\'' +
                '}';
    }
}
