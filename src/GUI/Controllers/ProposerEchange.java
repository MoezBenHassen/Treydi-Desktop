package GUI.Controllers;

import Entities.Echange;
import Entities.Item;
import Services.EchangeService;
import Services.EchangeProposerService;
import Services.ItemService;
import javafx.application.Platform;
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
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

public class ProposerEchange {

    private Echange selectedEchange;

    @FXML
    private GridPane user1_items;

    @FXML
    private GridPane items_grid;

    @FXML
    private GridPane user2_items;

    private double xOffset;
    private double yOffset;

    ItemService is = new ItemService();
    EchangeService es = new EchangeService();
    EchangeProposerService eps = new EchangeProposerService();

    List<Item> items_selectionner = new ArrayList<>();

    public void setSelectedEchangeProposer(Echange echange) {
        selectedEchange = echange;
        System.out.println(selectedEchange);
        loadUser1Items();
        echange_proposer();
    }

    @FXML
    void initialize() {

    }

    @FXML
    private void proposerEchange(MouseEvent event) {
        System.out.println(items_selectionner);
        if (items_selectionner.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vide");
            alert.setContentText("Ajouter des items");
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Confirmer");
            a.setContentText("vous etes sure ?");
            Optional<ButtonType> result = a.showAndWait();
            result.ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    //CURRENT USER ID
                    eps.ProposerEchange(selectedEchange, items_selectionner, 6);

                    try {
                        moveToAffListEchange(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            Platform.runLater(() -> {
                // show notification
                Notifications.create()
                        .title("Proposer")
                        .text("Proposition Envoyé")
                        .showInformation();
            });
        }
    }

    private void loadUser1Items() {
        List<Item> items = is.afficherUserItemsEchange(selectedEchange.getId_user1(), selectedEchange);
        System.out.println(selectedEchange);
        System.out.println(items);
        int column = 0, row = 0;

        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(80, 80);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            user1_items.add(stackPane, column, row);
            row = i / 4;
            column = i % 4;
        }
    }

    private void echange_proposer() {
        List<Item> items = is.afficherUserItems(6);
        int column = 0, row = 0;

        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(80, 80);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            if (items.get(i).getId_echange() == NULL) {
                items_grid.add(stackPane, column, row);
                row = i / 4;
                column = i % 4;
            }
            final int index = i;
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    rectangle.setFill(Color.RED);
                    int selectedCount = items_selectionner.size();
                    int row = selectedCount / 4;
                    int column = selectedCount % 4;
                    Rectangle newRectangle = new Rectangle(80, 80);
                    newRectangle.setFill(Color.RED);
                    Text newText = new Text(items.get(index).getLibelle());
                    StackPane newStackPane = new StackPane(newRectangle, newText);
                    newStackPane.setAlignment(Pos.CENTER);
                    user2_items.add(newStackPane, column, row);
                    items_grid.getChildren().remove(stackPane);
                    items_selectionner.add(items.get(index));
                    newStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            newRectangle.setFill(Color.BLUE);
                            items_selectionner.remove(items.get(index));
                            user2_items.getChildren().remove(newStackPane);
                            items_grid.add(stackPane, column, row);
                            rectangle.setFill(Color.BLUE);
                        }
                    });
                }
            });
        }
        //this adds a mousevent to each stackpane in echange_grid to be able to move it back to items grid
        for (Node node : user2_items.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0);
                Text text = (Text) stackPane.getChildren().get(1);
                stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        rectangle.setFill(Color.BLUE);
                        items_selectionner.remove(items.indexOf(text.getText()));
                        user2_items.getChildren().remove(stackPane);
                        items_grid.add(stackPane, items.indexOf(text.getText()) % 4, items.indexOf(text.getText()) / 4);
                    }
                });
            }
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
