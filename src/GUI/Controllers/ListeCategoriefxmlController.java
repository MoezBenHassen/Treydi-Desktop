package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCategoriefxmlController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView minimize;

    Stage stage;

    @FXML
    private TableView<CategorieArticle> tableView;
    @FXML
    private TableColumn<CategorieArticle,String> libelleC;
    @FXML
    private TableColumn<CategorieArticle,String> actionC;


    public void initialize(URL location, ResourceBundle resources) {
        libelleC.setCellValueFactory(new PropertyValueFactory<>("libelle_cat"));
        actionC.setCellFactory(column -> new TableCell<CategorieArticle, String>() {
            final Button editButton = new Button("Edit");
            final Button deleteButton = new Button("Delete");

            {
                Image editImage = new Image(getClass().getResourceAsStream("Assets/icons/blue/pencil.png"));
                ImageView editImageView = new ImageView(editImage);
                editImageView.setFitHeight(20);
                editImageView.setFitWidth(20);
                editButton.setGraphic(editImageView);

                Image deleteImage = new Image(getClass().getResourceAsStream("Assets/icons/blue/pencil.png"));
                ImageView deleteImageView = new ImageView(deleteImage);
                deleteImageView.setFitHeight(20);
                deleteImageView.setFitWidth(20);
                deleteButton.setGraphic(deleteImageView);
                editButton.setOnAction(event -> {
                    CategorieArticle categorie = getTableView().getItems().get(getIndex());
                    // Handle edit button action here
                });

                deleteButton.setOnAction(event -> {
                    CategorieArticle categorie = getTableView().getItems().get(getIndex());
                    // Handle delete button action here
                });
                editButton.setId("editButton");
                deleteButton.setId("deleteButton");
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(editButton, deleteButton);
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                }
            }
        });

        afficher();
    }

    private void afficher() {
        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> categorieArticles = categorieArticleService.afficher();
        tableView.getItems().clear();
        tableView.getItems().addAll(categorieArticles);
    }


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
}
