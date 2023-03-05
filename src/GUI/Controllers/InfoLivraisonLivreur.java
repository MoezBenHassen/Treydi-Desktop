package GUI.Controllers;

import Entities.Echange;
import Entities.Item;
import Entities.Livraison;
import Services.EchangeService;
import Services.ItemService;
import Services.LivraisonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InfoLivraisonLivreur implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    private Livraison selectedLivraison;

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView minimize;

    @FXML
    private GridPane user1_items;

    @FXML
    private GridPane user2_items;

    Stage stage;

    ItemService is = new ItemService();
    LivraisonService ls = new LivraisonService();
    EchangeService es = new EchangeService();
    Echange e = new Echange();


    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    public void setSelectedLivraisonInfo(Livraison livraison) {
        this.selectedLivraison = livraison;
        System.out.println(selectedLivraison);
        e = es.getEchangeByLivraison(selectedLivraison);
        loadItemsUser1();
        loadItemsUser2();
    }

    public void loadItemsUser1(){

        List<Item> items = is.afficherUserItemsEchange(e.getId_user1(),e);
        int column = 0, row = 0;
        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(60, 60);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            user1_items.add(stackPane, column, row);
            row = i / 4;
            column = i % 4;

        }
    }

    public void loadItemsUser2(){
        List<Item> items = is.afficherUserItemsEchange(e.getId_user2(),e);
        int column = 0, row = 0;
        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(60, 60);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            user2_items.add(stackPane, column, row);
            row = i / 4;
            column = i % 4;
        }
    }

    public void goToListEchangeLivreur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffListEchangeLivreur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void annulerButton(MouseEvent event){
        ls.annulerLivraisonLivreur(selectedLivraison, es.getEchangeByLivraison(selectedLivraison));
    }

    public void completerButton(MouseEvent event){
        ls.completerLivraisonLivreur(selectedLivraison);
    }

    public void goToListLivraisonLivreur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffListLivraisonLivreur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
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
