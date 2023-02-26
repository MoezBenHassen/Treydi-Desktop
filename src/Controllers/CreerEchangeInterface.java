package Controllers;

import java.net.URL;
import java.util.*;
import java.util.List;

import Entities.Echange;
import Entities.Item;
import Services.EchangeService;
import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

import static java.sql.Types.NULL;


public class CreerEchangeInterface {

    List<Item> items_selectionner = new ArrayList<>();


    @FXML
    private ScrollPane items_grid_scroll;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane items_grid;

    @FXML
    private GridPane echange_grid;

    @FXML
    private Pane item_pane;

    ItemService is = new ItemService();
    EchangeService es = new EchangeService();

    @FXML
    void initialize() {
        assert items_grid != null : "fx:id=\"items_grid\" was not injected: check your FXML file 'CreerEchangeInterface.fxml'.";
        assert echange_grid != null : "fx:id=\"echange_grid\" was not injected: check your FXML file 'CreerEchangeInterface.fxml'.";
        echange_creation();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        System.out.println(items_selectionner);
        if (items_selectionner.size() == 0) {
            System.out.println("EMPTY");
        } else {
            Echange e = new Echange(5, 8);
            es.CreerEchange(e, items_selectionner);
        }
    }

    private void echange_creation() {
        List<Item> items = is.afficherUserItems(5);
        int column = 0, row = 0;

        for (int i = 0; i < items.size(); i++) {
            Rectangle rectangle = new Rectangle(80, 80);
            rectangle.setFill(Color.BLUE);
            Text text = new Text(items.get(i).getLibelle());
            StackPane stackPane = new StackPane(rectangle, text);
            stackPane.setAlignment(Pos.CENTER);
            if (items.get(i).getId_echange() == NULL) {
                items_grid.add(stackPane, column, row);
                row = i / 2;
                column = i % 2;
            }
            final int index = i;
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    rectangle.setFill(Color.RED);
                    int selectedCount = items_selectionner.size();
                    int row = selectedCount / 2;
                    int column = selectedCount % 2;
                    Rectangle newRectangle = new Rectangle(80, 80);
                    newRectangle.setFill(Color.RED);
                    Text newText = new Text(items.get(index).getLibelle());
                    StackPane newStackPane = new StackPane(newRectangle, newText);
                    newStackPane.setAlignment(Pos.CENTER);
                    echange_grid.add(newStackPane, column, row);
                    items_grid.getChildren().remove(stackPane);
                    items_selectionner.add(items.get(index));
                    newStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            newRectangle.setFill(Color.BLUE);
                            items_selectionner.remove(items.get(index));
                            echange_grid.getChildren().remove(newStackPane);
                            items_grid.add(stackPane, column, row);
                            rectangle.setFill(Color.BLUE);
                        }
                    });
                }
            });
        }
        //this adds a mousevent to each stackpane in echange_grid to be able to move it back to items grid
        for (Node node : echange_grid.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0);
                Text text = (Text) stackPane.getChildren().get(1);
                stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        rectangle.setFill(Color.BLUE);
                        items_selectionner.remove(items.indexOf(text.getText()));
                        echange_grid.getChildren().remove(stackPane);
                        items_grid.add(stackPane, items.indexOf(text.getText()) % 2, items.indexOf(text.getText()) / 2);
                    }
                });
            }
        }
    }
}
