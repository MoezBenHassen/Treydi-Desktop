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
import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;

public class AjouterCategoriefxmlController implements  Initializable {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField txt_libelle ;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void ajouter(ActionEvent actionEvent) {
        //ArticleService articleService = new ArticleService();
        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> list = categorieArticleService.afficher();
        List<CategorieArticle> filteredList = list.stream()
                .filter(categorie -> categorie.getLibelle_cat().contains(txt_libelle.getText()))
                .collect(Collectors.toList());
        if (txt_libelle.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Remplir tout les champs");
            a.show();
        }else {
            CategorieArticle categorieArticle = new CategorieArticle(txt_libelle.getText());

            int rowsInserted = categorieArticleService.add(categorieArticle);
            if (rowsInserted>0){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Added Successfully");
                a.show();
                txt_libelle.clear();
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Add Failed");
                a.show();
            }

            //redirect
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
}
