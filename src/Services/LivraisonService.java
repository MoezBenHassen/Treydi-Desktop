package Services;


import Entities.Echange;
import Entities.Livraison;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interfaces.IService;
public class LivraisonService implements IService<Livraison> {
    Connection con;
    Statement stm;
    public LivraisonService() {
        con = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison livraison) {


    }

    @Override
    public void add(Livraison l) {

        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());

        String req = "INSERT INTO `livraison` ( `date_creation_livraison`, `etat_livraison`, `adresse_livraison1`, `adresse_livraison2`,  `id_livreur_id`, `id_echange_id`)"
                + "VALUES ('"+current_date+"', '"+l.getEtat()+"', '"+l.getAdresse_livraison1()+"', '"+l.getAdresse_livraison2()+"', '"+l.getId_livreur()+"', '"+l.getId_echange()+"')";
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
        String req = "UPDATE `livraison` SET `id_echange_id` = '"+l.getId_echange()+"', `adresse_livraison` = '"+l.getAdresse_livraison1()+"', `etat_livraison` = '"+l.getEtat()+"' WHERE id = '"+l.getId_livraison()+"'";
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
        String req = "UPDATE livraison SET archived = 1 WHERE id = '"+ l.getId_livraison()+"' ";

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
        String req = "SELECT `titre_echange` FROM `echange` WHERE id = '"+l.getId_echange()+"'";
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


    //for user adresse livraison
    public String userAdresse1(Echange e){
        String req = "SELECT `adresse` FROM `utilisateur` WHERE id = '"+e.getId_user1()+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            if (result.next()) {
                return result.getString("adresse");
            } else {
                throw new RuntimeException("No record found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public String userAdresse2(Echange e){
        String req = "SELECT `adresse` FROM `utilisateur` WHERE id = '"+e.getId_user2()+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            if (result.next()) {
                return result.getString("adresse");
            } else {
                throw new RuntimeException("No record found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    //afficher la list des livraison qui appartient a se livreur to be replaced with current user
    public List<Livraison> afficherListLivraisonLivreur(int id_livreur) {
        List<Livraison> livraison = new ArrayList<>();

        String query = "SELECT * from `livraison` WHERE `id_livreur_id` = '"+id_livreur+"' AND archived = 0";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                Livraison l = new Livraison(result.getInt("id_livraison"), result.getInt("id_livreur"), result.getInt("id_echange"),
                        result.getString("adresse_livraison1"), result.getString("adresse_livraison2"), Livraison.ETAT.valueOf(result.getString("etat_livraison")),
                        result.getDate("date_creation_livraison"), result.getDate("date_terminer_livraison"));
                livraison.add(l);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livraison;
    }

    public List<Livraison> afficherListLivraisonUser(Echange ee) {
        List<Livraison> livraison = new ArrayList<>();
        String query = "SELECT * from `livraison` WHERE `id_user_id` = '"+ee.getId_echange()+"' AND archived = 0";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                Livraison l = new Livraison(result.getInt("id_livraison"), result.getInt("id_livreur"), result.getInt("id_echange"),
                        result.getString("adresse_livraison1"), result.getString("adresse_livraison2"), Livraison.ETAT.valueOf(result.getString("etat_livraison")),
                        result.getDate("date_creation_livraison"), result.getDate("date_terminer_livraison"));
                livraison.add(l);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livraison;
    }

    public Boolean annulerLivraisonLivreur(Livraison l, Echange e){
        String req1 = "UPDATE livraison SET archived = 1 AND etat_livraison = '"+Livraison.ETAT.Termine+"' WHERE id = '"+ l.getId_livraison()+"' ";

        try {
            stm = con.createStatement();
            int rowsDeleted = stm.executeUpdate(req1);
            if (rowsDeleted > 0) {
                String req2 = "UPDATE echange SET liv_etat = '"+Echange.ETAT.Non_Accepter+"' WHERE id = '"+e.getId_echange()+"' ";
                stm.executeUpdate(req2);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void completerLivraisonLivreur(Livraison l){
        String req1 = "UPDATE livraison SET etat_livraison = '"+Livraison.ETAT.Termine+"' WHERE id = '"+ l.getId_livraison()+"' ";

        try {
            stm = con.createStatement();
            stm.executeUpdate(req1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

