package Services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Utilisateur;
import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Utils.MyDB;
import Utils.Enums.Roles;

import javax.management.relation.Role;

public class UtilisateurService  implements IService<Utilisateur> {

    Connection cnx;
    Statement stm;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Utilisateur user) {
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final Admin admin = (Admin) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ admin.getPassword() +
                            "', '"+ admin.getNom() +
                            "', '" + admin.getPrenom() +
                            "', '" + admin.getEmail() +
                            "', '" + admin.getAdresse() +
                            "', '" + admin.getAvatar_url() +
                            "', '" + admin.getRole() +"');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case trader:
                    final Trader trader = (Trader) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`, `score`, `date_naissance`)" +
                            "VALUES ('"+ trader.getPassword() +
                            "', '"+ trader.getNom() +
                            "', '" + trader.getPrenom() +
                            "', '" + trader.getEmail() +
                            "', '" + trader.getAdresse() +
                            "', '" + trader.getAvatar_url() +
                            "', '" + trader.getRole()+
                            "', '" + trader.getScore() +
                            "', '" + trader.getDate_naissance() + "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case livreur:
                    final Livreur livreur = (Livreur) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ livreur.getPassword() +
                            "', '"+ livreur.getNom() +
                            "', '" + livreur.getPrenom() +
                            "', '" + livreur.getEmail() +
                            "', '" + livreur.getAdresse() +
                            "', '" + livreur.getAvatar_url() +
                            "', '" + livreur.getRole()+"');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                default:
                    System.out.println("Error,Role not defined");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}



    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList();
        try {

            Statement statement = cnx.createStatement();
            String query = "SELECT * FROM `utilisateur`";
            ResultSet rs = statement.executeQuery(query);



            while(rs.next()){
                String role = rs.getString("role");

                if(Roles.valueOf(role)==Roles.livreur){
                    Livreur livreur = new Livreur();
                    livreur.setId_user(rs.getInt("id_user"));
                    livreur.setPassword(rs.getString("password"));
                    livreur.setNom(rs.getString("nom"));
                    livreur.setPrenom(rs.getString("prenom"));
                    livreur.setEmail(rs.getString("email"));
                    livreur.setAdresse(rs.getString("adresse"));
                    livreur.setAvatar_url(rs.getString("avatar_url"));
                    livreur.setRole(Roles.valueOf(rs.getString("role")));
                    utilisateurs.add(livreur);
                }
                if(Roles.valueOf(role)==Roles.trader){
                    Trader trader = new Trader();
                    trader.setId_user(rs.getInt("id_user"));
                    trader.setPassword(rs.getString("password"));
                    trader.setNom(rs.getString("nom"));
                    trader.setPrenom(rs.getString("prenom"));
                    trader.setEmail(rs.getString("email"));
                    trader.setAdresse(rs.getString("adresse"));
                    trader.setAvatar_url(rs.getString("avatar_url"));
                    trader.setRole(Roles.valueOf(rs.getString("role")));
                    trader.setScore((rs.getInt("score")));
                    trader.setDate_naissance(rs.getDate("date_naissance"));
                    utilisateurs.add(trader);

                }
                if(Roles.valueOf(role)==Roles.admin){
                    Admin admin = new Admin();
                    admin.setId_user(rs.getInt("id_user"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setAvatar_url(rs.getString("avatar_url"));
                    admin.setRole(Roles.valueOf(rs.getString("role")));
                    utilisateurs.add(admin);

                }
            }
            return utilisateurs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return null;
    }
    @Override
    public Boolean modifier(Utilisateur user){
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final Admin admin = (Admin) user;
                    qry = "UPDATE `utilisateur` SET `password`='"+ admin.getPassword() +
                            "',`nom`= '"+ admin.getNom() +
                            "',`prenom`= '" + admin.getPrenom() +
                            "',`email`= '" + admin.getEmail() +
                            "',adresse= '" + admin.getAdresse() +
                            "',avatar_url= '" + admin.getAvatar_url() +
                            "',role= '" + admin.getRole() +
                            "' WHERE `utilisateur`.`id_user`= " + admin.getId_user() +
                            ";";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case trader:
                    final Trader trader = (Trader) user;
                    qry = "UPDATE `utilisateur` SET `password`='"+ trader.getPassword() +
                            "',`nom`= '"+ trader.getNom() +
                            "',`prenom`= '" + trader.getPrenom() +
                            "',`email`= '" + trader.getEmail() +
                            "',adresse= '" + trader.getAdresse() +
                            "',avatar_url= '" + trader.getAvatar_url() +
                            "',role= '" + trader.getRole() +
                            "',score= '" + trader.getScore() +
                            "',date_naissance= '" + trader.getDate_naissance() +
                            "' WHERE `utilisateur`.`id_user`= " + trader.getId_user() +
                            ";";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case livreur:
                    final Livreur livreur = (Livreur) user;
                    qry = "UPDATE `utilisateur` SET `password`='"+ livreur.getPassword() +
                            "',`nom`= '"+ livreur.getNom() +
                            "',`prenom`= '" + livreur.getPrenom() +
                            "',`email`= '" + livreur.getEmail() +
                            "',adresse= '" + livreur.getAdresse() +
                            "',avatar_url= '" + livreur.getAvatar_url() +
                            "',role= '" + livreur.getRole() +
                            "' WHERE `utilisateur`.`id_user`= " + livreur.getId_user() +
                            ";";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                default:
                    System.out.println("Error,Role not defined");
            }
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    public int Validate(String email, String password) {
        int id=0;
        try {
            Statement statement  = cnx.createStatement();
            String req = "SELECT * FROM `utilisateur` WHERE email ='" + email + "' and password ='" + password + "';";
            ResultSet rs = statement.executeQuery(req);
            if (rs.next()) {
                id = rs.getInt("id_user");
                return id;
            }

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return id;
    }

    @Override
    public Boolean supprimer(Utilisateur user) {
        String query ="UPDATE Utilisateur SET archived='"+1+"' WHERE id_user="+user.getId_user();
        try{
            PreparedStatement preparedStatement=cnx.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated >0){
                System.out.println("Deleted user successfully ! ");
            }else {
                System.out.println("Delete user failed");
            }
            return  true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Utilisateur getUserById(int userId) {
        Utilisateur user = null;
        try {
            String qry = "SELECT * FROM Utilisateur WHERE id_user = " + userId + " AND archived = 0;";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            if (rs.next()) {
                user = new Utilisateur();
                user.setId_user(rs.getInt("id_user"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Roles.valueOf(rs.getString("role")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

}