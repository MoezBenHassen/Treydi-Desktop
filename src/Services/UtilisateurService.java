package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Interfaces.IService;
import Entities.Utilisateur;
import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;
import Utils.CurrentUser;
import Utils.MyDB;
import Utils.Enums.Roles;

public class UtilisateurService  implements IService<Utilisateur> {

    Connection cnx;
    Statement stm;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    
        public int afficherscore() {
        int s = 0;
        try {
            String qry = "SELECT `score` FROM `utilisateur` WHERE `id`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, CurrentUser.getInstance().getId_user());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s = rs.getInt("score");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s;
    }


    public boolean setScore(Trader c, int somme) {
        UtilisateurService us= new UtilisateurService();
        int sub= us.afficherscore()-somme;
        System.out.println(somme);
        System.out.println(us.afficherscore());
        System.out.println(sub);
        try {
            String qry = "UPDATE `utilisateur` SET `score` ='" + sub +  "'WHERE `id`=?";
            PreparedStatement stmt = cnx.prepareStatement(qry);
            stmt.setInt(1, CurrentUser.getInstance().getId_user());
            int rs = stmt.executeUpdate();
            System.out.println("Update Successful!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; }

    public int getScore() {
        UtilisateurService u = new UtilisateurService();
        int sc = u.afficherscore();
        return sc;
    }

    public List<Trader> afficherTraders() {
        List<Trader> users = new ArrayList();
        try {
            String qry = "SELECT * FROM `utilisateur` where `roles`='trader'";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Trader c = new Trader();
                c.setId_user(rs.getInt("id"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAdresse(rs.getString("adresse"));
                c.setEmail(rs.getString("email"));
                c.setAvatar_url(rs.getString("avatar_url"));
                c.setRole(Roles.trader);
                c.setDate_naissance(rs.getString("date_naissance"));
                c.setScore(rs.getInt("score"));
                users.add(c);
            } }
        catch ( SQLException ex) { System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) {

    }

    @Override
    public void add(Utilisateur user) {
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final Admin admin = (Admin) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `roles`, `archived`)" +
                            "VALUES ('"+ admin.getPassword() +
                            "', '"+ admin.getNom() +
                            "', '" + admin.getPrenom() +
                            "', '" + admin.getEmail() +
                            "', '" + admin.getAdresse() +
                            "', '" + admin.getAvatar_url() +
                            "', '" + admin.getRole() +
                            "', '" + admin.getArchived() +
                            "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case trader:
                    final Trader trader = (Trader) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `roles`, `score`, `date_naissance`, `archived`)" +
                            "VALUES ('"+ trader.getPassword() +
                            "', '"+ trader.getNom() +
                            "', '" + trader.getPrenom() +
                            "', '" + trader.getEmail() +
                            "', '" + trader.getAdresse() +
                            "', '" + trader.getAvatar_url() +
                            "', '" + trader.getRole()+
                            "', '" + trader.getScore() +
                            "', '" + trader.getDate_naissance() +
                            "', '" + trader.getArchived() +
                            "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;
                case livreur:
                    final Livreur livreur = (Livreur) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `roles`, `archived`)" +
                            "VALUES ('"+ livreur.getPassword() +
                            "', '"+ livreur.getNom() +
                            "', '" + livreur.getPrenom() +
                            "', '" + livreur.getEmail() +
                            "', '" + livreur.getAdresse() +
                            "', '" + livreur.getAvatar_url() +
                            "', '" + livreur.getRole()+
                            "', '" + livreur.getArchived() +
                            "');";
                    System.out.println(statement.executeUpdate(qry) + " Row inserted");
                    break;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}



    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList();
        try {

            Statement statement = cnx.createStatement();
            String query = "SELECT * FROM `utilisateur`WHERE archived =0";
            ResultSet rs = statement.executeQuery(query);



            while(rs.next()){
                String role = rs.getString("roles");

                if(Roles.valueOf(role)==Roles.livreur){
                    Livreur livreur = new Livreur();
                    livreur.setId_user(rs.getInt("id"));
                    livreur.setPassword(rs.getString("password"));
                    livreur.setNom(rs.getString("nom"));
                    livreur.setPrenom(rs.getString("prenom"));
                    livreur.setEmail(rs.getString("email"));
                    livreur.setAdresse(rs.getString("adresse"));
                    livreur.setAvatar_url(rs.getString("avatar_url"));
                    livreur.setRole(Roles.valueOf(rs.getString("roles")));
                    utilisateurs.add(livreur);
                }
                if(Roles.valueOf(role)==Roles.trader){
                    Trader trader = new Trader();
                    trader.setId_user(rs.getInt("id"));
                    trader.setPassword(rs.getString("password"));
                    trader.setNom(rs.getString("nom"));
                    trader.setPrenom(rs.getString("prenom"));
                    trader.setEmail(rs.getString("email"));
                    trader.setAdresse(rs.getString("adresse"));
                    trader.setAvatar_url(rs.getString("avatar_url"));
                    trader.setRole(Roles.valueOf(rs.getString("roles")));
                    trader.setScore((rs.getInt("score")));
                    trader.setDate_naissance(rs.getString("date_naissance"));
                    utilisateurs.add(trader);

                }
                if(Roles.valueOf(role)==Roles.admin){
                    Admin admin = new Admin();
                    admin.setId_user(rs.getInt("id"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setAvatar_url(rs.getString("avatar_url"));
                    admin.setRole(Roles.valueOf(rs.getString("roles")));
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
                            "',roles= '" + admin.getRole() +
                            "' WHERE `utilisateur`.`id`= " + admin.getId_user() +
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
                            "',roles= '" + trader.getRole() +
                            "',score= '" + trader.getScore() +
                            "',date_naissance= '" + trader.getDate_naissance() +
                            "' WHERE `utilisateur`.`id`= " + trader.getId_user() +
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
                            "',roles= '" + livreur.getRole() +
                            "' WHERE `utilisateur`.`id`= " + livreur.getId_user() +
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
                id = rs.getInt("id");
                return id;
            }

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return id;
    }

    @Override
    public Boolean supprimer(Utilisateur user) {
        String query ="UPDATE Utilisateur SET archived='"+1+"' WHERE id="+user.getId_user();
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
            String qry = "SELECT * FROM utilisateur WHERE id = " + userId + " AND archived = 0;";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            if (rs.next()) {
                user = new Utilisateur();
                user.setId_user(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setAdresse(rs.getString("adresse"));
                user.setAvatar_url(rs.getString("avatar_url"));
                user.setPrenom(rs.getString("prenom"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Roles.valueOf(rs.getString("roles")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    public Trader getTraderById(int userId) {
        Trader user = null;
        try {
            String qry = "SELECT * FROM utilisateur WHERE id = " + userId + " AND archived = 0;";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            if (rs.next()) {
                user = new Trader();
                user.setId_user(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("adresse"));
                user.setAvatar_url(rs.getString("avatar_url"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Roles.valueOf(rs.getString("roles")));
                user.setScore(rs.getInt("score"));
                user.setDate_naissance(rs.getString("date_naissance"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public Utilisateur getUserByEmail(String email) {
        Utilisateur user = null;
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            stm = cnx.createStatement();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId_user(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Roles.valueOf(rs.getString("roles")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }



    public void updateUserPassword(Utilisateur user, String newPassword) {
        user.setPassword(newPassword);
        try {
            String qry = "UPDATE utilisateur SET `password`='" + newPassword + "' WHERE id = '" + user.getId_user() + "';";
            stm = cnx.createStatement();
            stm.executeUpdate(qry);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
