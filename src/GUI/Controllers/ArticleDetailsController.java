package GUI.Controllers;

import Entities.Article;
import Entities.CategorieArticle;
import Services.CategorieArticleService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleDetailsController implements Initializable {
    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView minimize;

    @FXML
    private Text text_author_date;

    @FXML
    private Label titre_article;
    @FXML
    private Text text_content;
    @FXML
    private Button categorieButton;
    @FXML
    private Article selectedArticle;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
        populateArticleFields();
    }
    private void populateArticleFields(){

        if (selectedArticle == null) {
            return;
        }
        text_author_date.setText(selectedArticle.getAuteur() + " | " + selectedArticle.getDate_publication());
        titre_article.setText(selectedArticle.getTitre());

        CategorieArticleService categorieArticleService = new CategorieArticleService();
        List<CategorieArticle> categorieArticles = categorieArticleService.getLibelle(selectedArticle.getId_article());

        String libelleCat = categorieArticles.get(0).getLibelle_cat();

        categorieButton.setText(libelleCat);

        text_content.setText(selectedArticle.getContenu());
        titre_article.setWrapText(true);
        titre_article.setPrefWidth(820);


    }


    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }
    @FXML
    public void logout(MouseEvent actionEvent) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("closed article detail pain");
            stage.close();
        }
}


