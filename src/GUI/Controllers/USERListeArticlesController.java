package GUI.Controllers;

import Entities.Article;
import Services.ArticleService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class USERListeArticlesController implements Initializable {
    @FXML
    private AnchorPane scenePane;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    private VBox cardLayout;
    @FXML
    private List<Article> articles;
    Stage stage;
    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }
    public void logout(MouseEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        // Access the DialogPane
        DialogPane dialogPane = alert.getDialogPane();

        // Apply custom styles
        dialogPane.getStylesheets().add(getClass().getResource("../Assets/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("YOU XYZ");
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(recentlyAdded());
        afficherArticles();
    }

    private List<Article> recentlyAdded(){
        ArticleService articleService = new ArticleService();
        List<Article> list = articleService.afficher();
        return list;
    }

    @FXML
    private void afficherArticles(){
        ArticleService articleService = new ArticleService();
        articles= articleService.afficher();


        VBox vBox= new VBox();
        scrollPane.setContent(vBox);
        Slider slider = new Slider(0.5,2,1);
        slider.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");

        scrollPane.setStyle("-fx-background-color: transparent;");

        for (Article obj: articles
             ) {
            Label titre = new Label(obj.getTitre());
            Label description = new Label(obj.getDescription());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../cardA.fxml"));


                    HBox hBox = null;
            try {
                hBox = loader.load();
                cardAController cardAController = loader.getController();

                System.out.println("CCCCCC");
                cardAController.setData(obj.getTitre(), obj.getDescription(), obj.getId_categorie());

                vBox.setAlignment(Pos.CENTER_LEFT);
                vBox.setStyle("-fx-background-color: transparent !important; -fx-background-radius: 25 ");
                HBox finalHBox = hBox;
                hBox.setOnMouseEntered(e -> finalHBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 15;"));
                HBox finalHBox1 = hBox;
                hBox.setOnMouseExited(e -> finalHBox1.setStyle("-fx-background-radius: 15;  -fx-background-color: rgba(255, 255, 255, 0.5);"));
                vBox.setSpacing(8);
                System.out.println("FFFFF");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /*
            titre.setAlignment(Pos.CENTER_LEFT);
            description.setAlignment(Pos.CENTER_LEFT);

            hBox.setSpacing(150);
            hBox.setPrefWidth(900);
            hBox.setPrefHeight(150);
            hBox.setStyle("-fx-background-radius: 25");
            hBox.setOnMouseEntered(e -> hBox.setStyle("-fx-background-color: #56144D; -fx-text-fill: #56144D !important; -fx-border-radius: 5;-fx-background-radius: 5;"));
            hBox.setOnMouseExited(e -> hBox.setStyle("-fx-background-color: transparent;"));
            hBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setAlignment(Pos.CENTER);
            vBox.setStyle("-fx-background-color: transparent !important; -fx-background-radius: 25");
            scrollPane.setStyle("-fx-background-color: transparent !important;");
            */

            vBox.getChildren().add(hBox);
            scrollPane.setContent(vBox);
        }
    }
}
