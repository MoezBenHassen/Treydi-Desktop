package Entities;
import Utils.Enums.Roles;

import java.util.Date;
public class Trader extends Utilisateur{
    private int score;
    private Date date_naissance;



    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, Date date_naissance) {
        super(password, nom, prenom, email, adresse, avatar_url, role);
        this.score = score;
        this.date_naissance = date_naissance;
    }

    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, Date date_naissance,int id_user) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user);
        this.score = score;
        this.date_naissance = date_naissance;
    }
    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, Date date_naissance,int id_user,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user,archived);
        this.score = score;
        this.date_naissance = date_naissance;
    }

    public Trader() {

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "\n"+super.toString() + ", " +
                "score=" + score +
                ", date_naissance=" + date_naissance +
                '}';
    }
}