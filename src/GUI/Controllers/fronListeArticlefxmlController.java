package GUI.Controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fronListeArticlefxmlController implements Initializable {

    public ScrollPane scrollPane;
    public VBox vbox1;
    public TextField txt_titre1;
    public Rectangle rect;
    public Button logoutButton;
    public ImageView minimize;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private VBox cardLayout;
    @FXML
    private List<Article> recentlyAdded;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try{
            for (int i = 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("../articleCard.fxml"));

                VBox cardBox = fxmlLoader.load();

                articleCardController articleCardController = fxmlLoader.getController();

                articleCardController.setData(recentlyAdded.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        }catch (IOException e){
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null,e);
        }
        System.out.println("End of initialize method");
    }

    private List<Article> recentlyAdded(){
        List<Article> list = new ArrayList<>();
        Article article = new Article("titre", "description", 5);
        list.add(article);
        return list;
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
}
