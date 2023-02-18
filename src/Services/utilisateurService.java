package Services;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.admin;
import Entities.livreur;
import Entities.trader;
import Entities.utilisateur;
import Utils.MyDB;
import Utils.Enums.Roles;

import javax.management.relation.Role;


public class utilisateurService implements IService<utilisateur> {

    Connection cnx;
    Statement stm;

    public utilisateurService() {
        cnx = MyDB.getInstance().getCon();
    }

    @Override
    public void add(utilisateur user) {
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final admin admin = (admin) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ user.getPassword() +
                            "', '"+ user.getNom() +
                            "', '" + user.getPrenom() +
                            "', '" + user.getEmail() +
                            "', '" + user.getAdresse() +
                            "', '" + user.getAvatar_url() +
                            "', '" + user.getRole() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case trader:
                    final trader tra = (trader) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ user.getPassword() +
                            "', '" + user.getNom() +
                            "', '" + user.getPrenom() +
                            "', '" + user.getEmail() +
                            "', '" + user.getAdresse() +
                            "', '" + user.getAvatar_url() +
                            "', '" +tra.getDate_naissance()+
                            "', '" +tra.getScore() +
                            "', '" + user.getRole() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case livreur:
                    final livreur liv = (livreur) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ user.getPassword() +
                            "', '" + user.getNom() +
                            "', '" + user.getPrenom() +
                            "', '" + user.getEmail() +
                            "', '" + user.getAdresse() +
                            "', '" + user.getAvatar_url() +
                            "', '" + user.getRole() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                default:
                    System.out.println("Error : Role not defined");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<utilisateur> afficher() {
        List<utilisateur> utilisateurs = new ArrayList();
        try {
            Statement statement = cnx.createStatement();
            String query = "SELECT * FROM `utilisateur`";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String role = rs.getString("role");
                if (role != null) {
                    switch (Roles.valueOf(role)) {
                        case admin:
                            admin ad = new admin();
                                    ad.setAdresse(rs.getString("password"));
                                    ad.setNom(rs.getString("nom"));
                                    ad.setPrenom(rs.getString("prenom"));
                                    ad.setEmail(rs.getString("email"));
                                    ad.setAdresse(rs.getString("adresse"));
                                    ad.setAvatar_url(rs.getString("avatar_url"));
                                    ad.setRole(admin.role.valueOf(rs.getString("role")));
                            utilisateurs.add(ad);
                            break;
                            default:
                            System.out.println("error,role");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }


    @Override
    public Boolean modifier(utilisateur user) {
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final admin admin = (admin) user;
                    qry = "UPDATE `user` SET `Password` = '"+ user.getPassword() +
                            "',`Nom` = '" + user.getNom() +
                            "',`Prenom` = '" + user.getPrenom() +
                            "',`email` = '" + user.getEmail() +
                            "',`Adresse` = '" + user.getAdresse() +
                            "',`Avatar` = '" + user.getAvatar_url() +
                            "',`Role` = '" + user.getRole() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case trader:
                    final trader tra = (trader) user;
                    qry = "UPDATE `user` SET `Password` = '"+ user.getPassword() +
                            "',`Nom` = '" + user.getNom() +
                            "',`Prenom` = '" + user.getPrenom() +
                            "',`email` = '" + user.getEmail() +
                            "',`Adresse` = '" + user.getAdresse() +
                            "',`Avatar` = '" + user.getAvatar_url() +
                            "',`Role` = '" + user.getRole() +
                            "',`Avatar` = '" + tra.getScore() +
                            "',`Avatar` = '" + tra.getDate_naissance() +"');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case livreur:
                    final livreur liv = (livreur) user;
                    qry = "UPDATE `user` SET `Password` = '"+ user.getPassword() +
                            "',`Nom` = '" + user.getNom() +
                            "',`Prenom` = '" + user.getPrenom() +
                            "',`email` = '" + user.getEmail() +
                            "',`Adresse` = '" + user.getAdresse() +
                            "',`Avatar` = '" + user.getAvatar_url() +
                            "',`Role` = '" + user.getRole() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                default:
                    System.out.println("Error : Role not defined");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    @Override
    public Boolean supprimer(utilisateur utilisateur) {
        return null;
    }


}
