package Services;

import Entities.Livraison;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements Services.IService<Livraison> {
    Connection con;
    Statement stm;
    public LivraisonService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Livraison l) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());

        String req = "INSERT INTO `livraison` ( `date_livraison`, `etat_livraison`, `adresse_livraison`, `id_livreur`, `id_echange`)"
                + "VALUES ('"+current_date+"', '"+l.getEtat()+"', '"+l.getAdresse_livraison1()+"', '"+l.getId_livreur()+"', '"+l.getId_echange()+"')";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Livraison> afficher() {
        List<Livraison> livraison = new ArrayList<>();

        String query = "SELECT * from `livraison`";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                Livraison l = new Livraison(result.getInt("id_livraison"), result.getInt("id_livreur"), result.getInt("id_echange"),
                        result.getString("adresse_livraison1"), result.getString("adresse_livraison1"), Livraison.ETAT.valueOf(result.getString("etat_livraison")),
                        result.getDate("date_creation_livraison"), result.getDate("date_terminer_livraison"));
                livraison.add(l);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livraison;
    }

    @Override
    public Boolean modifier(Livraison l) {
        String req = "UPDATE `livraison` SET `id_echange` = '"+l.getId_echange()+"', `adresse_livraison` = '"+l.getAdresse_livraison1()+"', `etat_livraison` = '"+l.getEtat()+"' WHERE id_livraison = '"+l.getId_livraison()+"'";
        try {
            stm = con.createStatement();
            int rowsUpdated = stm.executeUpdate(req);
            if (rowsUpdated >0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Boolean supprimer(Livraison l) {
        String req = "UPDATE livraison SET archived = 1 WHERE id_livraison = '"+ l.getId_livraison()+"' ";

        try {
            stm = con.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getTitreEchange(Livraison l) {
        String req = "SELECT `titre_echange` FROM `echange` WHERE id_echange = '"+l.getId_echange()+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            if (result.next()) {
                return result.getString("titre_echange");
            } else {
                throw new RuntimeException("No record found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}