package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }
    @FXML
    public void logout(MouseEvent actionEvent) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("YOU XYZ");
            stage.close();
        }
}


