package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.ArticleService;
import Services.CategorieArticleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
public class ArticlefxmlController implements Initializable {
    @FXML
    private Button logoutButton;
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


    public void initialize(URL location, ResourceBundle resources) {
        afficher_combobox_cat() ;
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
            Article article = new Article(txt_titre.getText(), txt_description.getText(), txt_contenu.getText(), date, id_cat, 0, 4, txt_auteur.getText());
            int rowsInserted = articleService.add(article);
            if (rowsInserted>0){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Added Successfully");
                a.show();
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Add Failed");
                a.show();
            }

            //redirect
        }
    }


}
