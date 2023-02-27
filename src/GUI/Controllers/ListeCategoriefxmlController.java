package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            final Button editButton = new Button("");
            final Button deleteButton = new Button("");

            {
                Image editImage = new Image(getClass().getResourceAsStream("../Assets/icons/yellow/pencil.png"));
                ImageView editImageView = new ImageView(editImage);
                editImageView.setFitHeight(20);
                editImageView.setFitWidth(20);
                editButton.setGraphic(editImageView);

                Image deleteImage = new Image(getClass().getResourceAsStream("../Assets/icons/yellow/trash-bin.png"));
                ImageView deleteImageView = new ImageView(deleteImage);
                deleteImageView.setFitHeight(20);
                deleteImageView.setFitWidth(20);
                deleteButton.setGraphic(deleteImageView);
                editButton.setOnAction(event -> {
                    CategorieArticle categorie = getTableView().getItems().get(getIndex());

                });

                deleteButton.setOnAction(event -> {
                    CategorieArticle categorie = getTableView().getItems().get(getIndex());
                    //DELETE
                    if (categorie != null) {

                        CategorieArticleService categorieArticleService = new CategorieArticleService();

                        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmation.setTitle("Confirmation");
                        confirmation.setHeaderText(null);
                        confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
                        Optional<ButtonType> result = confirmation.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            boolean supprime = categorieArticleService.delete(categorie);
                            if (supprime) {
                                tableView.getItems().remove(categorie);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Suppression réussie");
                                alert.setHeaderText(null);
                                alert.setContentText("Suppression effectuée avec succès.");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur");
                                alert.setHeaderText(null);
                                alert.setContentText("Erreur lors de la suppression.");
                                alert.showAndWait();
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Avertissement");
                        alert.setHeaderText(null);
                        alert.setContentText("Veuillez sélectionner une catégorie à supprimer.");
                        alert.showAndWait();
                    }
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
        //PREVENT HORIZONTAL SCROLL BAR >>>>
        //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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


    @FXML
    void goToListArticle(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeArticles.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToAddArticle(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Articles.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void supprimer(ActionEvent event) {
        CategorieArticle selectedre = tableView.getSelectionModel().getSelectedItem();
        if (selectedre != null) {

            CategorieArticleService categorieArticleService = new CategorieArticleService();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean supprime = categorieArticleService.delete(selectedre);
                if (supprime) {
                    tableView.getItems().remove(selectedre);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression effectuée avec succès.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur lors de la suppression.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une catégorie à supprimer.");
            alert.showAndWait();
        }

    }
    @FXML
    void goToCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeCategorie.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
