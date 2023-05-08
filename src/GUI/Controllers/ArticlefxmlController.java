package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

import javax.swing.*;

public class ArticlefxmlController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Button addButton;

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

    @FXML
    private ImageView minimize;

    Stage stage;


    public void initialize(URL location, ResourceBundle resources) {
        afficher_combobox_cat() ;
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(addButton);
        translateTransition.setDuration(Duration.millis(600));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setByX(5);
        translateTransition.setByY(3);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
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
    private void afficher_combobox_cat() {
        CategorieArticleService cat = new CategorieArticleService();
        List<CategorieArticle> list = cat.afficher();
        for (CategorieArticle categorieArticle : list) {
            categorieArticleBox.getItems().add(categorieArticle.getLibelle_cat());
        }
    }

    public void ajouter(ActionEvent actionEvent) {
        ArticleService articleService = new ArticleService();
        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> list = categorieArticleService.afficher();
        String cbs = (String) categorieArticleBox.getValue();
        int id_cat = list.stream().filter((t) -> t.getLibelle_cat().equals(cbs)).mapToInt((t) -> t.getId_cat()).sum();

        if (txt_titre.getText().equals("") || txt_contenu.getText().equals("") || txt_contenu.getText().equals("") || cbs ==null){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Remplir tout les champs");
            a.show();
        }else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = dateFormat.parse("2022-02-23");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Article article = new Article(txt_titre.getText(), txt_description.getText(), txt_contenu.getText(), date, id_cat, 0, 4, 1);
            int rowsInserted = articleService.add(article);
            if (rowsInserted>0){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Added Successfully");
                a.show();
                txt_titre.clear();
                txt_description.clear();
                txt_contenu.clear();
                txt_auteur.clear();
                categorieArticleBox.setValue(null);
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Add Failed");
                a.show();
            }

            //redirect
        }
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
