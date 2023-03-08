package GUI.Controllers;

import Entities.Article;
import Services.CategorieArticleService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class cardAController implements Initializable {

    @FXML
    private Label cardTitle;

    @FXML
    private Label cardDescription;
    @FXML
    private Button categorieButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData (String titre, String description, int id_cat){

        cardTitle.setText(titre);
        System.out.println("here");
        cardDescription.setText(description);

        System.out.println("here2");
    }
}
