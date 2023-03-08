package GUI.Controllers;

import Entities.Article;
import Services.CategorieArticleService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.*;
import java.net.URL;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData (String titre, String description, int id_cat){
        Image  image = new Image(getClass().getResourceAsStream(images[(int)(Math.random()*images.length)]));
       // Image  image = new Image(getClass().getResourceAsStream("../Assets/images/i4.jpeg"));
        System.out.println(image);
        imageCard.setImage(image);

        cardTitle.setText(titre);
        System.out.println("here");
        cardDescription.setText(description);

        System.out.println("here2");
    }
}
