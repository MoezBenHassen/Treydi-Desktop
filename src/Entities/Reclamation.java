package Entities;




import java.util.Date;

public class Reclamation {
    private int id_reclamation;
    private String titre_reclamation;
    private String description;
    private Etat_reclamation etat_reclamation;
    private Date  date_creation ;
    private int id_user ;
    private int archived ;
    private Date date_cloture;
    public Reclamation() {

    }



    public Reclamation(int id_user, String titre_reclamation, String description) {
        this.titre_reclamation = titre_reclamation;
        this.description = description;
        this.id_user = id_user ;
    }

    public Reclamation(String titre_reclamation, String description, Etat_reclamation etat_reclamation, Date date_creation , Date date_cloture, int id_user, int archived) {
        this.titre_reclamation = titre_reclamation;
        this.description = description;
        this.etat_reclamation = etat_reclamation;
        this.date_creation = date_creation;
        this.date_cloture = date_cloture;
        this.id_user =  id_user ;
        this.archived = archived;


    }

    public Reclamation(String titre_reclamation, String description, Etat_reclamation etat_reclamation, Date date_cloture, int id_user, int archived) {
        this.titre_reclamation = titre_reclamation;
        this.description = description;
        this.etat_reclamation = etat_reclamation;
        this.id_user = id_user;
        this.archived = archived;
        this.date_cloture = date_cloture;
    }

    public Reclamation(int id_reclamation, String titre_reclamation, String description, Etat_reclamation etat_reclamation, Date date_creation , Date date_cloture, int id_user, int archived)  {
        this.id_reclamation = id_reclamation ;
        this.titre_reclamation = titre_reclamation;
        this.description = description;
        this.etat_reclamation = etat_reclamation;
        this.date_creation = date_creation;
        this.date_cloture = date_cloture;
        this.id_user =  id_user ;
        this.archived = archived;

    }



    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getTitre_reclamation() {
        return titre_reclamation;
    }

    public void setTitre_reclamation(String titre_reclamation) {
        this.titre_reclamation = titre_reclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etat_reclamation getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(Etat_reclamation etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    public Date getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(Date date_cloture) {
        this.date_cloture = date_cloture;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", titre_reclamation='" + titre_reclamation + '\'' +
                ", description='" + description + '\'' +
                ", etat_reclamation='" + etat_reclamation + '\'' +
                ", date_creation='" + date_creation + '\'' +
                ", date_cloture='" + date_cloture + '\'' +
                '}';
    }
}
