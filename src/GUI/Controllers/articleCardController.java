package GUI.Controllers;

import Entities.Article;
import Services.ArticleService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

import java.awt.event.ActionEvent;
import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class articleCardController implements Initializable {
    @FXML
    private HBox box;

    @FXML
    private ImageView cover;

    @FXML
    private Label titre;

    @FXML
    private Label description;

    @FXML
    private Button categorie;

    @FXML
    private Button ratingButton;

    @FXML
    private Rating ratingStars;

    @FXML
    private  void ratingButtonAction(ActionEvent actionEvent){

    }

    int id_article;
    int id_user; //current user
    double rating;
    public void setData (Article article){
       // System.out.println("Setting data for article " + article.getDescription());
        titre.setText(article.getTitre());
       // System.out.println("here");
        description.setText(article.getDescription());
        //System.out.println("here2");
        categorie.setText(String.valueOf(article.getId_categorie()));
        //System.out.println("Categorie button text set to: " + categorie.getText());

        this.id_article = article.getId_article();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("ARTICLE CARD HERE");
        ratingButton.setOnAction(event -> {
            ArticleService articleService = new ArticleService();
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("../Rate.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         */

        });
        ratingStars.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                System.out.println(" \n ############################################# OLD " +oldValue);
                System.out.println(" New " + newValue);
                //handleRatingChange();
            }
        });
       /* ratingStars.ratingProperty().addListener((observable, oldValue, newValue) -> {
            handleRatingChange();
        });
        */

    }
    private void handleRatingChange() {

        ArticleService articleService = new ArticleService();
        double userRating = ratingStars.getRating();

        try {
            articleService.addRating(id_article, 4, userRating);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       /* RatingService ratingService = new RatingService();
        ratingService.insertRating(articleId, userId, rating);

        */
    }
}
