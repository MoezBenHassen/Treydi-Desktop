package Services;

import Entities.Article;
import Entities.CategorieArticle;
import Interfaces.IArticleService;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieArticleService implements IArticleService<CategorieArticle> {
    Connection connection;
    Statement statement;
    public CategorieArticleService() {
        connection = MyDB.getInstance().getCon();
    }

    @Override
    public int add(CategorieArticle categorieArticle) {
        int rowsInserted = 0;
        try {
            String query = "INSERT INTO `categorie_article`(`libelle_cat`) VALUES ('" + categorieArticle.getLibelle_cat() + "')";
            statement = connection.createStatement();
            rowsInserted = statement.executeUpdate(query);
            if (rowsInserted > 0) {
                System.out.println("Categorie Inserted successfully ! ");
            } else {
                System.out.println("Insert failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsInserted;
    }

    @Override
    public List<CategorieArticle> afficher() {
        List<CategorieArticle> categorieArticles = new ArrayList<>();
        try{
            String query="SELECT * FROM `categorie_article` WHERE `archived`=0";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                CategorieArticle catArticle = new CategorieArticle();
                catArticle.setId_cat(resultSet.getInt("id_cat"));
                catArticle.setLibelle_cat(resultSet.getString("libelle_cat"));
                categorieArticles.add(catArticle);
            }
            return  categorieArticles;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categorieArticles;
    }

    @Override
    public List<CategorieArticle> afficherArchived() {
        List<CategorieArticle> categorieArticles = new ArrayList<>();
        try{
            String query="SELECT * FROM `categorie_article` WHERE `archived`<>0";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                CategorieArticle catArticle = new CategorieArticle();
                catArticle.setId_cat(resultSet.getInt("id_cat"));
                catArticle.setLibelle_cat(resultSet.getString("libelle_cat"));
                categorieArticles.add(catArticle);
            }
            return  categorieArticles;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categorieArticles;
    }

    @Override
    public Boolean update(CategorieArticle categorieArticle) {
        String query ="UPDATE `categorie_article` SET `libelle_cat`='"+categorieArticle.getLibelle_cat()+"' WHERE id_cat="+categorieArticle.getId_cat();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated>0){
                System.out.println("Updated categorie successfully ! ");
            }else {
                System.out.println("Update categorie failed");
            }
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return  false;
        }
    }

    @Override
    public Boolean delete(CategorieArticle categorieArticle) {
        String query="UPDATE `categorie_article` SET `archived`='"+1+"' WHERE id_cat="+categorieArticle.getId_cat();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated >0){
                System.out.println("Deleted categorie successfully ! ");
            }else {
                System.out.println("Delete categorie failed");
            }
            return  true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
