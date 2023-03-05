package GUI.Controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

import java.awt.print.Book;
import java.net.URL;
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



    public void setData (Article article){
        System.out.println("Setting data for article " + article.getDescription());
        titre.setText(article.getTitre());
        System.out.println("here");
        description.setText(article.getDescription());
        System.out.println("here2");
        categorie.setText(String.valueOf(article.getId_categorie()));
        System.out.println("Categorie button text set to: " + categorie.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ARTICLE CARD HERE");
    }
}
