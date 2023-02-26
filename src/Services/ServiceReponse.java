package Services;

import Entities.Reponse;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ServiceReponse implements IService<Reponse> {
    Connection cnx;
    Statement stm;

    public ServiceReponse() {
        cnx = MyDB.getInstance().getCon();

    }
   @Override
    public void ajouter(Reponse Re) {
       java.sql.Date dateR = new java.sql.Date(System.currentTimeMillis());
        try {
            String qry = "INSERT INTO `reponse`( `titre_reponse`, `description_reponse`, `date_reponse`, `id_reclamation`, `archived`) VALUES ('" + Re.getTitre_reponse() + "','" + Re.getDescription_reponse() + "','" + dateR + "','" + Re.getId_reclamation() + "','" + 0 +"')";

            stm = cnx.createStatement();
            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }



    @Override
    public List<Reponse> afficher() {
        List<Reponse> Reponses = new ArrayList();
        try {
            String qry = "SELECT * FROM `reponse` WHERE archived = 0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Reponse Re = new Reponse();
                Re.setId_reponse(rs.getInt("id_reponse"));
                Re.setTitre(rs.getString("titre_reponse"));
                Re.setDescription_reponse(rs.getString("description_reponse"));
                Re.setDate_reponse(rs.getDate("date_reponse"));


                Reponses.add(Re);
            }

            return  Reponses;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Reponses;

    }

    @Override
    public Boolean modifier(Reponse Re) {
        try {
            String qry = "UPDATE `reclamation` SET `titre_reponse`='" + Re.getTitre_reponse() + "',`description_reponse`='" + Re.getDescription_reponse() + "',`date_reponse`='" + Re.getDate_reponse() + "',`id_reclamation`='" + Re.getId_reclamation() + "''";

            stm = cnx.createStatement();
            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;

        }

    }

    @Override
    public Boolean supprimer(Reponse Re) {
        String qry = "UPDATE `reponse` SET `archived`='"+ 1 +"' where id_reponse ='" + Re.getId_reponse() + "'";
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }



    }










