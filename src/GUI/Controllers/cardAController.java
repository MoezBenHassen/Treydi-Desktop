package GUI.Controllers;

import Entities.CategorieArticle;
import javafx.event.ActionEvent;

import Entities.Article;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class cardAController implements Initializable {

    @FXML
    private Label cardTitle;

    @FXML
    private Label cardDescription;
    @FXML
    private ImageView imageCard;
    @FXML
    private Button categorieButton;
    private String[] images = {"../Assets/images/i1.png","../Assets/images/i2.png", "../Assets/images/i3.png"};
    @FXML
    private Button ratingButton;

    @FXML
    private Rating ratingStars;


    int id_article;
    int id_user; //current user
    double rating;



    @FXML
    private  void ratingButtonAction(ActionEvent actionEvent){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add a listener to the rating property of the Rating control

        //ratingStars.setOnMouseClicked(event -> System.out.println("XXXXX"));
    }

    private void handleRatingChange(double userRating) {
        ArticleService articleService = new ArticleService();

        try {
            articleService.addRating(1, 4, userRating);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData (String titre, String description, int id_cat){
        Image  image = new Image(getClass().getResourceAsStream(images[(int)(Math.random()*images.length)]));
       // Image  image = new Image(getClass().getResourceAsStream("../Assets/images/i4.jpeg"));
       // System.out.println(image);
        imageCard.setImage(image);

        cardTitle.setText(titre);
        //System.out.println("here");
        cardDescription.setText(description);
        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> categorieArticles = categorieArticleService.getLibelle(id_cat);

        String libelleCat = categorieArticles.get(0).getLibelle_cat();
        categorieButton.setText(libelleCat);
        //System.out.println("here2");
    }
}
