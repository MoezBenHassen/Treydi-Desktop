package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;

import javax.swing.*;

public class ListeArticlefxmlController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView minimize;
    @FXML
    private TableView<Article> tableView;
    @FXML
    private TableColumn<Article,String> titreC;
    @FXML
    private TableColumn<Article,String>  descriptionC;
    @FXML
    private TableColumn<Article,String> contenuC;
    @FXML
    private TableColumn<Article,Date> datePubC;

    @FXML
    private TableColumn<Article, String> categorieC;
    @FXML
    private TableColumn<Article,String> auteurC;

    Stage stage;
    public void logout(javafx.event.ActionEvent actionEvent) {
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
    public void initialize(URL url, ResourceBundle rb) {

        titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        contenuC.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datePubC.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
        categorieC.setCellValueFactory(cellData -> {
            int idCategorie = cellData.getValue().getId_categorie();

            CategorieArticleService categorieArticleService = new CategorieArticleService();
            List<CategorieArticle> categorieArticles = categorieArticleService.getLibelle(idCategorie);

            String libelleCat = categorieArticles.get(0).getLibelle_cat();
            return new SimpleStringProperty(libelleCat);
        });
       // categorieC.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));

        auteurC.setCellValueFactory(new PropertyValueFactory<>("auteur"));

       //PREVENT HORIZONTAL SCROLL BAR >>>>
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        afficher();
    }

    private void afficher() {
        ArticleService articleService = new ArticleService();
        List<Article> articles = articleService.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(articles);
    }


    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
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
        Article selectedre = tableView.getSelectionModel().getSelectedItem();
        if (selectedre != null) {

            ArticleService articleService = new ArticleService();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean supprime = articleService.delete(selectedre);
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
            alert.setContentText("Veuillez sélectionner une réclamation.");
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
