package GUI;

import Entities.Categorie_Items;
import Entities.Item;
import Services.CategorieItemsService;
import Services.ItemService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficherItemImageController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField textfield_libelle;

    @FXML
    private Item selectedItem ;

    @FXML
    private ImageView image ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setSelectedItem(Item item) {
        selectedItem = item;
        populateFields();
    }


    private void populateFields() {
        textfield_libelle.setText(selectedItem.getLibelle());
        Image newImage = new Image(selectedItem.getImageurl());
        image.setImage(newImage);

        textfield_libelle.setEditable(false);

    }


    @FXML
    private void shutdown(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
