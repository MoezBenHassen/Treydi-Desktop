package Services;

import Entities.Livraison;
import Interfaces.ILivraisonService;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements ILivraisonService<Livraison> {
    Connection con;
    Statement statement;

    public LivraisonService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Livraison l) {

        try {
            String query = "INSERT INTO `livraison`(`date_livraison`," +
                    " `etat_livraison`, `adresse_livraison`, `id_livreur`, `id_echange`) " +
                    "VALUES ('"+l.getDate_livraison()+"','"+l.getEtat_livraison()+"','"+l.getAdresse_livraison()+"','"+l.getId_livreur()+"','"+l.getId_echange()+"')";
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Livraison> afficher() {
        List<Livraison> livraisons = new ArrayList<>();
        try {
            String query="SELECT * FROM `livraison`";

            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Livraison l = new Livraison();
                l.setDate_livraison(resultSet.getDate("date_livraison"));
                l.setEtat_livraison(resultSet.getString("etat_livraison"));
                l.setAdresse_livraison(resultSet.getString("adresse_livraison"));
                l.setId_livreur(resultSet.getInt("id_livreur"));
                l.setId_echange(resultSet.getInt("id_echange"));
                livraisons.add(l);
            }
            return livraisons;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return livraisons;
    }

    @Override
    public Boolean update(Livraison l)  {
        String query="UPDATE `livraison` SET `date_livraison`='"+l.getDate_livraison()+"'," +
                "`etat_livraison`='"+l.getEtat_livraison()+"',`adresse_livraison`='"+l.getAdresse_livraison()+"',`id_livreur`='"+l.getId_livreur()+"'," +
                "`id_echange`='"+l.getId_echange()+"' WHERE id_livraison="+l.getId();
        try{
            PreparedStatement preparedStatement=con.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated >0){
                System.out.println("Updated successfully ! ");
            }else {
                System.out.println("Update failed");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    public Boolean delete(int id) {
        String query= "DELETE FROM Livraison WHERE id_livraison=?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1,id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted >0){
                System.out.println("Deleted successfully ! ");
                return true;
            }else {
                System.out.println("DELETE FAILED");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

}
