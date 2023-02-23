package Entities;


import java.util.Date;
public class Reponse {

    private int id_reponse ;
    private String titre_reponse ;
    private String description_reponse ;
    private Date date_reponse ;
    private int id_reclamation ;
    private int archived ;

    public Reponse() {
    }


    public Reponse(String titre_reponse, String description_reponse, int id_reclamation) {
        this.titre_reponse = titre_reponse;
        this.description_reponse = description_reponse;
        this.id_reclamation = id_reclamation;
    }

    public Reponse(String titre_reponse, String description_reponse, Date date_reponse, int id_reclamation, int archived) {
        this.titre_reponse = titre_reponse;
        this.description_reponse = description_reponse;
        this.archived = archived;
        this.date_reponse = date_reponse;
        this.id_reclamation = id_reclamation ;



    }

    public Reponse(int id_reponse, String titre_reponse, String description_reponse, Date date_reponse,int id_reclamation, int archived)   {
        this.id_reponse = id_reponse;
        this.titre_reponse = titre_reponse ;
        this.archived = archived;
        this.description_reponse = description_reponse;
        this.date_reponse = date_reponse;
        this.id_reclamation = id_reclamation ;


    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public String getTitre_reponse() {
        return titre_reponse;
    }

    public void setTitre(String titre_reponse) {
        this.titre_reponse = titre_reponse;
    }

    public String getDescription_reponse() {
        return description_reponse;
    }

    public void setDescription_reponse(String description_reponse) {
        this.description_reponse = description_reponse;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }



    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id_reponse=" + id_reponse +
                ", titre_reponse='" + titre_reponse + '\'' +
                ", Description_reponse='" + description_reponse + '\'' +
                ", date_reponse='" + date_reponse + '\'' +
                '}';
    }
}
