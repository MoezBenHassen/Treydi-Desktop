package Services;

import Entities.Categorie_Items;
import Interfaces.IService;
import Utils.MyDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategorieService implements IService<Categorie_Items> {

    Connection cnx;
    Statement stm;

    public CategorieService() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(Categorie_Items c) {
        try {
            String qry = "INSERT INTO `categorie_items` (`nom_categorie`,`archived`) VALUES ('" + c.getNom_categorie()+"',0);";
            stm = cnx.createStatement();

            int res = stm.executeUpdate(qry);

            if (res > 0) {
                System.out.println("ligne "+c.getNom_categorie()+" est insérée");
            } else {
                System.out.println("ligne "+c.getNom_categorie()+" n'est pas insérée");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Categorie_Items> afficher() {
        List<Categorie_Items> categories = new ArrayList();
        try {
            String qry = "SELECT * FROM `categorie_items` WHERE `archived` = 0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Categorie_Items c = new Categorie_Items();
                c.setId_categorie(rs.getInt("id_categorie"));
                c.setNom_categorie(rs.getString("nom_categorie"));

                categories.add(c);
            }
            return categories;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;

    }

    @Override
    public Boolean modifier(Categorie_Items c) {
        try {
            String qry = "UPDATE `categorie_items` SET `nom_categorie`='" + c.getNom_categorie() + "' WHERE `id_categorie`='" + c.getId_categorie() + "';";
            stm = cnx.createStatement();
            int res = stm.executeUpdate(qry);
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean supprimer(Categorie_Items c) {
        try {
            String qry = "UPDATE `categorie_items` SET `archived` = 1 WHERE `id_categorie`='" + c.getId_categorie() + "';";
            stm = cnx.createStatement();
            int res = stm.executeUpdate(qry);
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }




}
