package Services;

import Entities.Coupon;
import Entities.Utilisateur;
import Utils.MyDB;

import javax.rmi.CORBA.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {

    static Connection cnx;
    static Statement stm;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCon();

    }

    public int afficherscore(Utilisateur c) {
        int s = 0;
        try {
            String qry = "SELECT `score` FROM `utilisateur` WHERE `id_user`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, c.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s = rs.getInt("score");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s;
    }


    public boolean setScore(Utilisateur c, int somme) {
        UtilisateurService us= new UtilisateurService();
        int sub= us.afficherscore(c)-somme;
        System.out.println(somme);
        System.out.println(us.afficherscore(c));
        System.out.println(sub);
        try {
            String qry = "UPDATE `utilisateur` SET `score` ='" + sub +  "'WHERE `id_user`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, c.getId());
            int rs = stmt.executeUpdate();
            System.out.println("Update Successful!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; }


    public List<Utilisateur> afficherUsers() {
        List<Utilisateur> users = new ArrayList();
        try {
            String qry = "SELECT * FROM `utilisateur`";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Utilisateur c = new Utilisateur();
                c.setId(rs.getInt("id_user"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAdresse(rs.getString("adresse"));
                c.setEmail(rs.getString("email"));
                c.setImage_url(rs.getString("avatar_url"));
                c.setRole(rs.getString("role"));
                c.setDatenaissance(rs.getString("date_naissance"));
                c.setScore(rs.getInt("score"));
                users.add(c);
            } }
        catch ( SQLException ex) { System.out.println(ex.getMessage());
        }
        return users;
    }


    public List<Utilisateur> afficherTraders() {
        List<Utilisateur> users = new ArrayList();
        try {
            String qry = "SELECT * FROM `utilisateur` where `role`='trader'";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Utilisateur c = new Utilisateur();
                c.setId(rs.getInt("id_user"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAdresse(rs.getString("adresse"));
                c.setEmail(rs.getString("email"));
                c.setImage_url(rs.getString("avatar_url"));
                c.setRole(rs.getString("role"));
                c.setDatenaissance(rs.getString("date_naissance"));
                c.setScore(rs.getInt("score"));
                users.add(c);
            } }
        catch ( SQLException ex) { System.out.println(ex.getMessage());
        }
        return users;
    }
}

