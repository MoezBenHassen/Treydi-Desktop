package Services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Coupon;
import Entities.Utilisateur;
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
            String qry = "INSERT INTO `coupon`(`titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_categorie`) VALUES ('" + c.getTitre_coupon() + " ','" + c.getDescription_coupon() + "','" + c.getDate_expiration() + "','" + c.getEtat_coupon() +"','"+  c.getId_categoriecoupon() + "')";
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
                c.setCode(rs.getString("code"));
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


    public int affecterCouponCasual(Utilisateur u) {
        UtilisateurService cs = new UtilisateurService();
        Coupon c = new Coupon(1, u.getId(), "Coupon Mars", "Coupon 30% sur la livraison", "VALID", "CasCoupon23","2023-04-01");
        int couponId = -1;
        try {
            String qry = "INSERT INTO `coupon`(`titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `id_categorie`, `code`) VALUES ('" + c.getTitre_coupon() + "','" + c.getDescription_coupon() + "','" + c.getDate_expiration() + "','" + c.getEtat_coupon() + "','" + u.getId() + "','" + c.getId_categoriecoupon() + "','" + c.getCode() + "')";

            stm = cnx.createStatement();
            stm.executeUpdate(qry, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                couponId = rs.getInt(1);
            }
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Coupon ajouté avec succès!");
        return couponId;
    }

    public int affecterCouponSpecial(Utilisateur u) {
        UtilisateurService cs = new UtilisateurService();
        Coupon c = new Coupon(2, u.getId(), "Coupon Mars Silver", "Coupon 100% Livraison", "VALID", "SpecCoupon23","2023-04-01");
        int couponId = -1;
        try {
            String qry = "INSERT INTO `coupon`(`titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `id_categorie`, `code`) VALUES ('" + c.getTitre_coupon() + "','" + c.getDescription_coupon() + "','" + c.getDate_expiration() + "','" + c.getEtat_coupon() + "','" + u.getId() + "','" + c.getId_categoriecoupon() + "','" + c.getCode() + "')";

            stm = cnx.createStatement();
            stm.executeUpdate(qry, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                couponId = rs.getInt(1);
            }
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Coupon ajouté avec succès!");
        return couponId;
    }

    public int affecterCouponExclusif(Utilisateur u) {
        UtilisateurService cs= new UtilisateurService();
        Coupon c = new Coupon(3, u.getId(), "Coupon Mars Gold", "Carte de recharge OOREDOO", "VALID", "LoyaltyCoupon23","2023-04-01");
        int couponId = -1;
        try {
            String qry = "INSERT INTO `coupon`(`titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `id_categorie`, `code`) VALUES ('" + c.getTitre_coupon() + "','" + c.getDescription_coupon() + "','" + c.getDate_expiration() + "','" + c.getEtat_coupon() + "','" + u.getId() + "','" + c.getId_categoriecoupon() + "','" + c.getCode() + "')";

            stm = cnx.createStatement();
            stm.executeUpdate(qry, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                couponId = rs.getInt(1);
            }
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Coupon ajouté avec succès!");
        return couponId;
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

    public String getCode(int id) {
        String s = null;
        try {
            String qry = "SELECT `code` FROM `coupon` WHERE `id_coupon`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s = String.valueOf(rs.getString("code"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s;
    }


    public List<Coupon> afficherParUser(Utilisateur user) {
        List<Coupon> coupons = new ArrayList();
        try {
            String qry = "SELECT * FROM `coupon` WHERE `archived`=0 AND `id_user`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Coupon c = new Coupon();
                c.setId_coupon(rs.getInt("id_coupon"));
                c.setId_user(rs.getInt("id_user"));
                c.setTitre_coupon(rs.getString("titre_coupon"));
                c.setDescription_coupon(rs.getString("description_coupon"));
                c.setEtat_coupon(rs.getString("etat_coupon"));
                c.setDate_expiration(rs.getString("date_expiration"));
                c.setId_categoriecoupon(rs.getInt("id_categorie"));
                c.setCode(rs.getString("code"));
                coupons.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coupons;

    }

}