package GUI.Controllers;

import Entities.Echange;
import Entities.EchangeProposer;
import Entities.Item;
import Services.EchangeProposerService;
import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static java.sql.Types.NULL;

public class InfoEchangeUserProp {
    private double xOffset = 0;
    private double yOffset = 0;

    private EchangeProposer selectedEchangeProp;

    @FXML
    private GridPane user1_items;

    @FXML
    private GridPane user2_items;

    ItemService is = new ItemService();
    EchangeProposerService eps = new EchangeProposerService();
    Echange e = new Echange();

    @FXML
    void initialize() {
    }

    public void setSelectedEchangeInfoAccRefProp(EchangeProposer echangeProp) {
        selectedEchangeProp = echangeProp;
        e = eps.getEchangeByProp(selectedEchangeProp);
        System.out.println("ECHANNNNNNNNNGE" + e);
        System.out.println("PROPPPPPPP" + selectedEchangeProp);
        loadItemsUser1();
        loadItemsUser2();

    }

    public void accProp(ActionEvent event ){
        List<Item> items = eps.getListItemsByProp(selectedEchangeProp);
        eps.accProposition(selectedEchangeProp, items, e);
    }

    public void refProp(ActionEvent event ) {
        List<Item> items = is.afficherUserItemsEchange(selectedEchangeProp.getId_user(),e);
        eps.refuserPropostion(selectedEchangeProp, items);
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
        List<Item> items = is.afficherUserItemsEchange(selectedEchangeProp.getId_user(),e);
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

    @FXML
    private void moveToAffListEchange(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffListEchange.fxml"));
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

    //LOGOUT
    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView minimize;

    Stage stage;
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
