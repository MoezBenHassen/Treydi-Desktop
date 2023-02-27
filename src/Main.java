

import Entities.Article;
import Entities.CategorieArticle;
import Entities.Livraison;
import Services.ArticleService;
import Services.CategorieArticleService;
import Services.LivraisonService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
    //ARTICLE :
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

    //CATEGORIE ARTICLE :
        //ADD CATEGORIE ARTICLE
            CategorieArticleService categorieArticleService = new CategorieArticleService();
            CategorieArticle categorieArticle = new CategorieArticle("RECHERE");
          //  categorieArticleService.add(categorieArticle);
        //GET CATEGORIE ARTICLE
            System.out.println(categorieArticleService.afficher());
        //UPDATE
            CategorieArticle categorieArticle2 = new CategorieArticle(3,"UPDATED RECHERCHEE");
            categorieArticleService.update(categorieArticle2);
        //DELETE
            categorieArticleService.delete(categorieArticle2);
    }
}