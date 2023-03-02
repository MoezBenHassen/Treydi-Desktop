package GUI.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
public class UpdateArticlefxmlController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private ImageView minimize;

    @FXML
    private AnchorPane scenePane;
    @FXML
    private ComboBox categorieArticleBox;
    @FXML
    private TextField txt_titre ;
    @FXML
    private TextField txt_description ;
    @FXML
    private TextField txt_contenu ;
    @FXML
    private TextField txt_auteur ;



    Stage stage;


    private int id_article  ;
    private double xOffset;
    private double yOffset;
    private int id_categorie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher_combobox_cat();
    }
    @FXML
    private void afficher_combobox_cat() {
        CategorieArticleService cat = new CategorieArticleService();
        List<CategorieArticle> list = cat.afficher();
        for (CategorieArticle categorieArticle : list) {
            categorieArticleBox.getItems().add(categorieArticle.getLibelle_cat());
        }
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


    public void setId_article(int idArticle) {
        this.id_article = idArticle;
    }
    public void setId_categorie(int idCategorie) {
        this.id_categorie = idCategorie;
    }
    public void setTitreText(String text) {
        txt_titre.setText(text);
    }
    public void setDescriptionText(String text) {
        txt_description.setText(text);
    }
    public void setContenuText(String text) {

        txt_contenu.setText(text);
    }
    public void setAuteurText(String text) {
        txt_auteur.setText(text);
    }
    public void setCategorieArticleBox() {
        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> categorieArticles = categorieArticleService.getLibelle(id_article);

        String libelleCat = categorieArticles.get(0).getLibelle_cat();

        categorieArticleBox.setValue(libelleCat);

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
    void goToAddArticle(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Articles.fxml"));
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
    void goToListArticle(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeArticles.fxml"));
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
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    void modifier (ActionEvent event) throws IOException{
        String titre = txt_titre.getText().trim();
        String description = txt_description.getText().trim();
        String contenu = txt_contenu.getText().trim();
        String auteur = txt_auteur.getText().trim();
      //  String categorie = categorieArticleBox.getValue().toString();
//        int id_categorie = id_article;
        if (titre.equals("") || description.equals("") || contenu.equals("") || auteur.equals("") ){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Remplir tout les champs");
            a.show();
            return;
        }else {
            ArticleService articleService = new ArticleService();
            Article article = new Article(id_article, titre, description, contenu, id_categorie,auteur);
            articleService.update(article);
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

}
