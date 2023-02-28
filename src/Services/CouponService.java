package Services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Coupon;
import Interfaces.IService;
import Utils.MyDB;


public class CouponService implements IService<Coupon> {

    static Connection cnx;
    static Statement stm;

    public CouponService() {
        cnx = MyDB.getInstance().getCon();

    }

    @Override
    public void add(Coupon c) {
        try {
            String qry = "INSERT INTO `coupon`(`titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_categoriecoupon`) VALUES ('" + c.getTitre_coupon() + " ','" + c.getDescription_coupon() + "','" + c.getDate_expiration() + "','" + c.getEtat_coupon() +  c.getId_categoriecoupon() + "')";
            stm = cnx.createStatement();
            stm.executeUpdate(qry);
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Coupon ajouté avec succès!");
        }


    public List<Coupon> afficher() {
        List<Coupon> coupons = new ArrayList();
        try {
            String qry = "SELECT * FROM `coupon` WHERE `archived`=0;";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Coupon c = new Coupon();
                c.setId_coupon(rs.getInt("id_coupon"));
                c.setTitre_coupon(rs.getString("titre_coupon"));
                c.setDescription_coupon(rs.getString("description_coupon"));
                c.setEtat_coupon(rs.getString("etat_coupon"));
                c.setDate_expiration(rs.getString("date_expiration"));
                c.setId_categoriecoupon(rs.getInt("id_categorie"));
                coupons.add(c);
            } }
        catch ( SQLException ex) { System.out.println(ex.getMessage());
    }
        return coupons;
    }

    public Boolean modifier(Coupon c) {
        try {
            String qry = "UPDATE `coupon` SET `titre_coupon`='" + c.getTitre_coupon() + "',`description_coupon`='" + c.getDescription_coupon() + "',`etat_coupon`='" + c.getEtat_coupon() + "'";
            stm = cnx.createStatement();
            int rset = stm.executeUpdate(qry);
            System.out.println("Update Successful!");
            return true;

        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Update NOT Successful!");
        }
        return false;
    }

    public Boolean supprimer(Coupon c) {
        try {
            String qry = "UPDATE coupon SET archived='" + 1 + "' where id_coupon ='" + c.getId_coupon() + "';";
            stm = cnx.createStatement();
            int rset = stm.executeUpdate(qry);
            if (rset > 0) {
                System.out.println("Delete Successful!");
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Delete NOT Successful!");
        }
        return false;
    }

}
