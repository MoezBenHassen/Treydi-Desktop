package Services;

import Entities.Article;
import Interfaces.IArticleService;
import Utils.MyDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleService implements IArticleService<Article> {
    Connection connection;
    Statement statement;
    public ArticleService() {
        connection = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Article article) {
        try{
            String query = "INSERT INTO `article`(`titre`, `description`, `contenu`, `date_publication`," +
                         " `id_categorie`, `archived`, `id_user`, `auteur`) VALUES ('"+article.getTitre()+"','"+article.getDescription()+"'," +
                         "'"+article.getContenu()+"','"+article.getDate_publication()+"','"+article.getId_categorie()+"','"+article.getArchived()+"','"+article.getId_user()+"'" +
                         ",'"+article.getAuteur()+"')";
            statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(query);
            if (rowsInserted >0){
                System.out.println("Inserted successfully ! ");
            }else {
                System.out.println("Insert failed");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Article> afficher() {
        List<Article> articles = new ArrayList<>();
        try{
            String query="SELECT * FROM `article` WHERE `archived`=0";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Article article = new Article();
                article.setId_article(resultSet.getInt("id_article"));
                article.setTitre(resultSet.getString("titre"));
                article.setDescription(resultSet.getString("description"));
                article.setContenu(resultSet.getString("contenu"));
                article.setDate_publication(resultSet.getDate("date_publication"));
                article.setId_categorie(resultSet.getInt("id_categorie"));
                article.setArchived(resultSet.getInt("archived"));
                article.setId_user(resultSet.getInt("id_user"));
                article.setAuteur(resultSet.getString("auteur"));
                articles.add(article);
            }
            return  articles;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return articles;
    }

    @Override
    public List<Article> afficherArchived() {

        List<Article> articles = new ArrayList<>();
        try{
            String query="SELECT * FROM `article` WHERE `archived`<>0";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Article article = new Article();
                article.setId_article(resultSet.getInt("id_article"));
                article.setTitre(resultSet.getString("titre"));
                article.setDescription(resultSet.getString("description"));
                article.setContenu(resultSet.getString("contenu"));
                article.setDate_publication(resultSet.getDate("date_publication"));
                article.setId_categorie(resultSet.getInt("id_categorie"));
                article.setArchived(resultSet.getInt("archived"));
                article.setId_user(resultSet.getInt("id_user"));
                article.setAuteur(resultSet.getString("auteur"));
                articles.add(article);
            }
            return  articles;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return articles;
    }

    @Override
    public Boolean update(Article article) {
        return null;
    }

    @Override
    public Boolean delete(Article article) {
        return null;
    }


}
