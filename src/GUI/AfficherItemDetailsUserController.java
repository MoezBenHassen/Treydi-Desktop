package GUI;

import APIs.ToPDFItem;
import APIs.ToXLSItem;
import Entities.Categorie_Items;
import Entities.Item;
import GUI.ModifierItemAdminController;
import Services.CategorieItemsService;
import Services.ItemService;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class AfficherItemDetailsUserController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;


    private  List<Item> items ;
    private  List<Categorie_Items> categories ;




    @FXML
    private TextField textfield_libelle;
    @FXML
    private TextArea textarea_description;

    @FXML
    private TextField textfield_categorie;

    @FXML
    private TextField textfield_typeetat ;

    @FXML
    private Item selectedItem ;

    @FXML
    private ImageView image ;

    @FXML
    private Text t1 ;

    @FXML
    private Text t2 ;

    @FXML
    private Text t3 ;

    @FXML
    private Text t4 ;

    @FXML
    private CheckBox ech ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemService sp = new ItemService();
        items = sp.afficherAdmin();

        CategorieItemsService sp2 = new CategorieItemsService();
        categories = sp2.afficher();

    }

    public void setSelectedItem(Item item) {
        selectedItem = item;
        populateFields();
    }


    private void populateFields() {
        textfield_libelle.setText(selectedItem.getLibelle());
        textarea_description.setText(selectedItem.getDescription());
        System.out.println();

        Image newImage = new Image(selectedItem.getImageurl());
        image.setImage(newImage);


        CategorieItemsService sp2 = new CategorieItemsService();
        List<Categorie_Items> list = sp2.afficher();
        String cbs = list.stream().filter((t) -> t.getId_categorie() == selectedItem.getId_categorie()).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
        textfield_categorie.setText(cbs);


        String srt1 = String.valueOf(selectedItem.getType());
        String srt2 = String.valueOf(selectedItem.getEtat());
        if (srt1.equals("Physique")) {
            if (srt2.equals("Neuf")) {
                textfield_typeetat.setText("Physique Neuf");
            }
            if (srt2.equals("Occasion")) {

                textfield_typeetat.setText("Physique Occasion");
            }

        } else if (srt1.equals("Virtuelle")) {
            textfield_typeetat.setText("Virtuelle");

        } else if (srt1.equals("Service")) {
            textfield_typeetat.setText("Service");

        }


        textfield_libelle.setEditable(false);
        textfield_categorie.setEditable(false);
        textfield_typeetat.setEditable(false);
        textarea_description.setEditable(false);


        Set<String> searchSetClib = new HashSet<>(Arrays.asList(selectedItem.getLibelle().split(" ")));


        long x1 = items.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase())))
                .filter(t -> t.getId_echange() != 0).count();

        t1.setText("Nombre d'items similaires à celui-ci disponibles à l'échange : " + String.valueOf(x1));

        long x2 = items.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase()))).count();
        t2.setText("Nombre d'items similaires à celui-ci de tous les utilisateurs : " + String.valueOf(x2));


        if (srt1.equals("Physique")) {
            if (srt2.equals("Neuf")) {
                long x3 = items.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase())))
                        .filter((t) -> t.getType() == Item.type.Physique)
                        .filter((t) -> t.getEtat() == Item.state.Occasion)
                        .count();
                t3.setText("Nombre d'items similaires mais utilisé : " + String.valueOf(x3));
            } else {
                long x3 = items.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase())))
                        .filter((t) -> t.getType() == Item.type.Physique)
                        .filter((t) -> t.getEtat() == Item.state.Neuf)
                        .count();
                t3.setText("Nombre d'items similaires mais neufs : " + String.valueOf(x3));
            }

        } else {
            t3.setText("");
        }

        if (selectedItem.getId_echange() != 0) {
            ech.setSelected(true);
        }

        long x4 = items.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase())))
                .filter((t) -> t.getId_categorie() == selectedItem.getId_categorie())
                .count();
        t4.setText("Nombre d'items avec le méme categorie : " + String.valueOf(x4));


    }

    @FXML
    private void route_Image(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemDetailsAdminFXML.fxml"));
        Parent root = loader.load();
        AfficherItemImageController controller = loader.getController();
        controller.setSelectedItem(selectedItem);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);


        stage.initStyle(StageStyle.TRANSPARENT);
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
