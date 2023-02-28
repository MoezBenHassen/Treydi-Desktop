package Services;

import Entities.CategorieCoupon;
import Interfaces.IService;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieCouponService implements IService<CategorieCoupon> {

        Connection cnx;
        Statement stm;

        public CategorieCouponService() {
            cnx = MyDB.getInstance().getCon();

        }

    public void add(CategorieCoupon c) {
        try {
                String qry = "INSERT INTO `categorie_coupon`( `nom_categorie`, `description_categorie`) VALUES ('" + c.getNom_categorie() + "','" + c.getDescription_categorie() + "')";
                stm = cnx.createStatement();
                stm.executeUpdate(qry);
            }
         catch (SQLException ex) {

            System.out.println(ex.getMessage());
            System.out.println("ERREUR! Catégorie non ajouté.");
        }
        System.out.println("Catégorie ajouté avec succès!");
    }

    public List<CategorieCoupon> afficher() {
        List<CategorieCoupon> categories = new ArrayList();
        try {
            String qry = "SELECT * FROM `categorie_coupon` WHERE `archived`=0";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                CategorieCoupon c = new CategorieCoupon();
                c.setId_categoriecoupon(rs.getInt("id_categoriecoupon"));
                c.setNom_categorie(rs.getString("nom_categorie"));
                c.setDescription_categorie(rs.getString("description_categorie"));

                categories.add(c);
            }
            return categories;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERREUR! Impossible d'afficher les catégories des coupons.");
    }
        System.out.println("Liste de catégories coupons générée avec succès");
        return categories;

    }
@Override
    public Boolean modifier(CategorieCoupon c) {
        try {
                String qry = "UPDATE `categorie_coupon` SET ( `nom_categorie`, `description_categorie`) " +
                        "VALUES ('" + c.getNom_categorie() + "','" + c.getDescription_categorie() + "')";
                stm = cnx.createStatement();
                int rqu = stm.executeUpdate(qry);
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERREUR! Impossible de modifier la catégorie des coupons.");
            return false;
        }
        System.out.println("Catégorie coupon modifiée avec succès");
        return true;
    }

    @Override
    public Boolean supprimer(CategorieCoupon c) {
        try {
                String qry = "UPDATE categorie_coupon SET archived='" + 1 + "' where id_categoriecoupon ='" + c.getId_categoriecoupon() + "';";
                stm = cnx.createStatement();
                int rqu = stm.executeUpdate(qry);
                if (rqu > 0) {
                    System.out.println("Delete Successful!");
                    return true;
                }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Delete NOT Successful!");
        }
        return false;

    }

}
