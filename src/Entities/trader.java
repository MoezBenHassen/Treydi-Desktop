package Entities;


import Utils.Enums.Roles;

import java.util.Date;

public class trader extends utilisateur {

    private int score;
    private Date date_naissance;


    public trader( String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, Date date_naissance) {
        super( password, nom, prenom, email, adresse, avatar_url, role);
        this.score = score;
        this.date_naissance = date_naissance;
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
        return "trader{" +
                "score=" + score +
                ", date_naissance=" + date_naissance +
                '}';
    }
}


