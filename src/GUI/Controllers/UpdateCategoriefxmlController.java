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

import javax.swing.*;

public class UpdateCategoriefxmlController implements Initializable {
    @FXML
    private AnchorPane scenePane;

    @FXML
    private  Button updateButton;
    @FXML
    private TextField txt_libelle;
    Stage stage;
    int id_categorie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
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
    public void setLibelleCategorie (String libelleCategorie){
        this.txt_libelle.setText(libelleCategorie);
    }
    public void setId_categorie(int idCategorie) {
        this.id_categorie = idCategorie;
    }
    @FXML
    void update(ActionEvent event) throws IOException{
        String libelle = txt_libelle.getText().trim();
        if (libelle.equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Remplir tout les champs");
            a.show();
            return;
        }else {
            CategorieArticleService categorieArticleService = new CategorieArticleService();
            CategorieArticle categorieArticle = new CategorieArticle(id_categorie,libelle);
            categorieArticleService.update(categorieArticle);
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}
