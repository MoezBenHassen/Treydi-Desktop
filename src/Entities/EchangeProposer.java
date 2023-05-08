package Entities;

import java.sql.Date;

public class EchangeProposer {
    int id_echange, id, id_user;
    Date date_proposer;

    public EchangeProposer(){}

    public EchangeProposer(int id, int id_echange, Date date_proposer, int id_user) {
        this.id = id;
        this.id_echange = id_echange;
        this.date_proposer = date_proposer;
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_proposer() {
        return date_proposer;
    }

    public void setDate_proposer(Date date_proposer) {
        this.date_proposer = date_proposer;
    }

    @Override
    public String toString() {
        return "EchangeProposer{" +
                "id_echange=" + id_echange +
                ", id_prop=" + id +
                ", id_user=" + id_user +
                ", date_proposer=" + date_proposer +
                '}';
    }
}
