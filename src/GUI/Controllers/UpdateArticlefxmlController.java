package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateArticlefxmlController implements Initializable {
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

    @FXML
    private ImageView minimize;

    Stage stage;


    private int id_article  ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setId_article(int idReclamation) {
        this.id_article = idReclamation;
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
    public void setCategorieArticleBox(String text) {
        categorieArticleBox.setValue(text);
    }

}
