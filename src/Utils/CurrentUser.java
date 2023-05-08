package Utils;

import Utils.Enums.Roles;

public class CurrentUser {
    // Add fields specific to Trader
    private int score;
    private String date_naissance;
    private String sessionId;

    private static CurrentUser instance;

    private int id;
    private String password, nom, prenom, email, adresse, avatar_url;
    private Roles roles;
    private int archived;

    // Private constructor to prevent instantiation from outside the class
    private CurrentUser(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles roles, int id) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.roles = roles;
        this.id = id;
    }
 private CurrentUser(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles roles, int score, String date_naissance, int id) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.roles = roles;
        this.score = score;
        this.date_naissance=date_naissance;
        this.id = id;
    }

    public static CurrentUser getInstance(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id) {
        if (instance == null) {
            // Synchronize here to ensure that only one instance is created
            synchronized (CurrentUser.class) {
                if (instance == null) {
                    instance = new CurrentUser(password, nom, prenom, email, adresse, avatar_url, role, id);
                }
            }
        }
        return instance;
    }
  public static CurrentUser getInstance(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role,int score,String date_naissance, int id) {
        if (instance == null) {
            // Synchronize here to ensure that only one instance is created
            synchronized (CurrentUser.class) {
                if (instance == null) {
                    instance = new CurrentUser(password, nom, prenom, email, adresse, avatar_url, role,score,date_naissance, id);
                }
            }
        }
        return instance;
    }

    public static CurrentUser getInstance() {
        return instance;
    }



    // Add methods specific to Trader


    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getId_user() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    // Override toString method to include Trader specific fields
    @Override
    public String toString() {
        if (this.roles == Roles.trader) {
            return "\nTrader{" +
                    "id=" + id +
                    ", password='" + password + '\'' +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", role=" + roles + '\'' +
                    ", score=" + score + '\'' +
                    ", date_naissance=" + date_naissance +
                    '}';
        } else {
            return "\nCurrentUser{" +
                    "id=" + id +
                    ", password='" + password + '\'' +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", role=" + roles +
                    '}';
        }
    }
}

