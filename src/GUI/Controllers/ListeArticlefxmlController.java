package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
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
import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    @FXML private Node topcircle;
    @FXML private Node bottomcirlce ;

    @FXML
    private TextField rechercheField;
    Stage stage;
    private double xOffset;
    private double yOffset;

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

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(topcircle);
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setByX(30);
        translateTransition.setByZ(-30);
        translateTransition.setByY(-30);

        translateTransition.setByX(-30);
        translateTransition.setByZ(-30);
        translateTransition.setByY(30);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        translateTransition.setNode(bottomcirlce);
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setByX(-200);
        translateTransition.setByY(200);
//affichage
        ArticleService articleService = new ArticleService();
        List<Article> articles = articleService.afficher() ;
        afficher(articles);
        // filtre affichage
        rechercheField.textProperty().addListener((obs, oldVal, newVal) -> {
            String titre = rechercheField.getText().trim();
            List<Article> articlesSearch = articles.stream()
                    .filter(recc -> recc.getTitre().toLowerCase().contains(titre.toLowerCase()))
                    .collect(Collectors.toList());
            afficher(articlesSearch);
        });
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                // Check if it's a double click
                try {
                    goToArticleDetails(event);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void afficher(List<Article> articleList) {
        tableView.getItems().clear();
        tableView.getItems().addAll(articleList);
    }

    @FXML
    private void goToArticleDetails(MouseEvent mouseEvent)throws IOException{
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ArticleDetails.fxml"));
        Parent root = loader.load();

        ArticleDetailsController articleDetailsController = loader.getController();
        articleDetailsController.setSelectedArticle(selectedArticle);

        Scene scene = new Scene(root);

        Stage stage1 = new Stage();
        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];

        scene.setOnMousePressed(eventM -> {
            xOffset[0] = eventM.getSceneX();
            yOffset[0] = eventM.getSceneY();
        });

        scene.setOnMouseDragged(eventM -> {
            stage1.setX(eventM.getScreenX() - xOffset[0]);
            stage1.setY(eventM.getScreenY() - yOffset[0]);
        });
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage1.setWidth(1024);
        stage1.setHeight(768);
        stage1.setScene(scene);
        stage1.showAndWait();
        //afficher(a);

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
            alert.setContentText("Veuillez sélectionner un article à supprimer.");
            alert.showAndWait();
        }

    }
    @FXML
    void goToCategorie(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeCategorie.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void openModifcation(ActionEvent actionEvent) throws IOException {
        Article selectedArticle = tableView.getSelectionModel().getSelectedItem();
        String titreC = String.valueOf(selectedArticle.getTitre());
        String descriptionC = String.valueOf(selectedArticle.getDescription());
        String contenuC = String.valueOf(selectedArticle.getContenu());
        //Date dateC =
        String auteurC = String.valueOf(selectedArticle.getAuteur());
        int id_categorie = Integer.valueOf(selectedArticle.getId_categorie());
        int id_article = Integer.valueOf(selectedArticle.getId_article());
       System.out.println(" categorie " + id_categorie);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UpdateArticle.fxml"));
        Parent root = loader.load();

        //set fields in the other controller
        UpdateArticlefxmlController updateArticlefxmlController = loader.getController();
        updateArticlefxmlController.setTitreText(titreC);
        updateArticlefxmlController.setDescriptionText(descriptionC);
        updateArticlefxmlController.setContenuText(contenuC);
        updateArticlefxmlController.setAuteurText(auteurC);
        updateArticlefxmlController.setId_article(id_article);
        updateArticlefxmlController.setId_categorie(id_categorie);
        updateArticlefxmlController.setCategorieArticleBox();
        System.out.println(id_categorie);

        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        // drag and move page method
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage1.setX(event.getScreenX() - xOffset);
            stage1.setY(event.getScreenY() - yOffset);
        });
        stage1.initStyle(StageStyle.UNDECORATED);

        stage1.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);


        stage1.setWidth(1024);
        stage1.setHeight(768);
        stage1.setScene(scene);
        stage1.showAndWait();
        //afficher();
    }
}
