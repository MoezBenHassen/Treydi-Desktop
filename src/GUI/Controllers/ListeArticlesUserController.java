package GUI.Controllers;

import Entities.Article;
import Services.ArticleService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeArticlesUserController implements Initializable {
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView minimize;

    @FXML
    private TextField txt_titre;

    @FXML
    private TextField txt_contenu;

    @FXML
    private TextField txt_description;

    @FXML
    private ComboBox<?> categorieArticleBox;

    @FXML
    private TextField txt_auteur;

    @FXML
    private Rectangle rect;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox cardLayout;

    @FXML
    private List<Article> recentlyAddedArticles;
    Stage stage;

    @FXML
    private TextField txt_titre1;
    public void logout(javafx.event.ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("YOU XYZ");
            stage.close();
        }
    }

    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyAddedArticles = new ArrayList<>(recentlyAdded());
        for (int i = 0; i < recentlyAddedArticles.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../articleCard.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                articleCardController articleCardController = fxmlLoader.getController();
                articleCardController.setData(recentlyAddedArticles.get(i));
                cardLayout.getChildren().add(cardBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End of initialize method");
        }
    }
    private List<Article> recentlyAdded(){
        ArticleService articleService = new ArticleService();
        List<Article> list = articleService.afficher();
        System.out.println("recently added done");
        return list;
    }
}
