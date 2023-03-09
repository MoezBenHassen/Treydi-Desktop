package Entities;

import java.util.Date;

public class Echange {
    private int id_echange;
    private String titre_echange;
    private int id_user1;
    private int id_user2;
    private Date date_echange;
    public enum ETAT {Non_Accepter, Accepter};


    public Echange() {}

    public Echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public Echange( int id_user1, int id_user2) {
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
    }

    public Echange( int id_echange, int id_user1, int id_user2){
        this.id_echange = id_echange;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
    }

    public Echange(int id_echange, int id_user1, int id_user2, Date date_echange){
        this.id_echange = id_echange;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.date_echange = date_echange;
    }

    public Echange(int id_echange, String titre_echange, int id_user1, int id_user2, Date date_echange) {
        this.id_echange = id_echange;
        this.titre_echange = titre_echange;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.date_echange = date_echange;
    }

    public String getTitre_echange() {
        return titre_echange;
    }

    public void setTitre_echange(String titre_echange) {
        this.titre_echange = titre_echange;
    }

    public int getId_echange() {
        return id_echange;
    }

    public int getId_user1() {
        return id_user1;
    }

    public int getId_user2() {
        return id_user2;
    }

    public Date getDate_echange() {
        return date_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public void setId_user1(int id_user1) {
        this.id_user1 = id_user1;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }


    public void setDate_echange(Date date_echange) {
        this.date_echange = date_echange;
    }

    @Override
    public String toString() {
        return "Echange{" +
                "id_echange=" + id_echange +
                ", id_user1=" + id_user1 +
                ", id_user2=" + id_user2 +
                ", date='" + date_echange + '\'' +
                '}';
    }
}
