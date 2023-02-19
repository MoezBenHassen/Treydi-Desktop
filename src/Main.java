

import Entities.Article;
import Entities.Livraison;
import Services.ArticleService;
import Services.LivraisonService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ArticleService articleService = new ArticleService();
        //ADD ARTICLE
        Article article = new Article("Titre article", "Short Description Article", "content", new Date(2023, 02,19), 1, 0, 7,"auteur");
        //articleService.add(article);
        // GET ARTICLE
        System.out.println(articleService.afficher());
        System.out.println(articleService.afficherArchived());
        //UPDATED ARTICLE
        Article articleUpdate = new Article(2,"ARTICLE 1", "Short Description Article UPDATED", "content", new Date(2023, 02,19), 1, 0, 7,"auteur");
        articleService.update(articleUpdate);
        //DELETE ARTICLE
        articleService.delete(articleUpdate);


        /*
        LivraisonService livraisonService = new LivraisonService();
        //GET
        System.out.println(livraisonService.afficher());
        //UPDATE
        Livraison livraisonUpdate = new Livraison(4,new Date(2022,10,10),"En-cours","adresse22",4,2);
        livraisonService.update(livraisonUpdate);
        //DELETE
        livraisonService.delete(9);

         */
    }
}