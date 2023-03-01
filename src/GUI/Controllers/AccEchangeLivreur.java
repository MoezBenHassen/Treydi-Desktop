package GUI.Controllers;

import Entities.Echange;
import Entities.Item;
import Services.ItemService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

import java.util.List;

public class AccEchangeLivreur {
    private Echange selectedEchange;

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
    @FXML
    void initialize() {

    }


    public void setSelectedEchangeAccLiv(Echange echange) {
        selectedEchange = echange;
        loadItemsUser1();
        loadItemsUser2();
    }

    public void loadItemsUser1(){
        List<Item> items = is.afficherUserItemsEchange(selectedEchange.getId_user1(),selectedEchange);
        int column = 0, row = 0;
        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(60, 60);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            user1_items.add(stackPane, column, row);
            row = (i + 1) / 4;
            column = i % 4;

    }
}

    public void loadItemsUser2(){
        List<Item> items = is.afficherUserItemsEchange(selectedEchange.getId_user2(),selectedEchange);
        int column = 0, row = 0;
        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(60, 60);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            user2_items.add(stackPane, column, row);
            row = (i + 1) / 4;
            column = i % 4;
        }
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
