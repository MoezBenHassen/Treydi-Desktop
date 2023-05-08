package GUI;

import Entities.Categorie_Items;
import Entities.Item;
import Services.CategorieItemsService;
import Services.ItemService;
import Utils.CurrentUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ModifierItemAdminController implements Initializable {

    private Item selectedItem;

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField textfield_libelle;
    @FXML
    private TextArea textarea_description;
    @FXML
    private RadioButton radio_type_1 ;

    @FXML
    private RadioButton radio_type_2 ;

    @FXML
    private RadioButton radio_type_3 ;

    @FXML
    private RadioButton radio_type_4 ;
    @FXML
    private ToggleGroup radio_type;

    @FXML
    private ComboBox combobox_cat ;

    @FXML
    private ImageView imageview_imageurl ;

    @FXML
    private TextField textfield_id;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher_combobox_cat() ;
    }

    public void setSelectedItem(Item item) {
        selectedItem = item;
        populateFields();
    }

    @FXML
    private void afficher_combobox_cat() {
        CategorieItemsService sp = new CategorieItemsService();
        List<Categorie_Items> list = sp.afficher();
        for (Categorie_Items categorieItems : list) {
            combobox_cat.getItems().add(categorieItems.getNom_categorie());
        }
        }



    @FXML
    private void select_image() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageview_imageurl.setImage(image);
        }
    }

    private void populateFields() {
        textfield_libelle.setText(selectedItem.getLibelle());
        textarea_description.setText(selectedItem.getDescription());
        textfield_id.setText(String.valueOf(selectedItem.getId_item()));

        Image image = new Image(selectedItem.getImageurl());
        imageview_imageurl.setImage(image);

        CategorieItemsService sp2 = new CategorieItemsService();
        List<Categorie_Items> list = sp2.afficher();
        String cbs = list.stream().filter((t) -> t.getId_categorie() == selectedItem.getId_categorie()).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", ")) ;
        combobox_cat.setValue(cbs) ;



        String srt1 = String.valueOf(selectedItem.getType());
        String srt2 = String.valueOf(selectedItem.getEtat());
        if (srt1.equals("Physique")) {
            if (srt2.equals("Neuf")) {
                radio_type_1.setSelected(true);
            }
            if (srt2.equals("Occasion")) {

                radio_type_2.setSelected(true);
            }

        } else if (srt1.equals("Virtuelle")) {
            radio_type_3.setSelected(true);

        } else if (srt1.equals("Service")) {
            radio_type_4.setSelected(true);

        }
    }


    @FXML
    private void modifier(ActionEvent event) throws IOException {
        ItemService sp = new ItemService();
        CategorieItemsService sp2 = new CategorieItemsService();

        if (textfield_id.getText().matches("-?\\d+")) {

            List<Categorie_Items> list = sp2.afficher();
            String cbs = (String) combobox_cat.getValue();
            int id_cat = list.stream().filter((t) -> t.getNom_categorie().equals(cbs)).mapToInt((t) -> t.getId_categorie()).sum();

            RadioButton selected_radio_type = (RadioButton) radio_type.getSelectedToggle();
            Item.type type = null;
            Item.state etat = null;

            String srt = selected_radio_type.getText();
            if (srt.equals("Physique Neuf")) {
                etat = Item.state.valueOf("Neuf");
                type = Item.type.valueOf("Physique");

            } else if (srt.equals("Physique Occasion")) {
                etat = Item.state.valueOf("Occasion");
                type = Item.type.valueOf("Physique");

            } else if (srt.equals("Virtuelle")) {
                etat = Item.state.valueOf("Null");
                type = Item.type.valueOf("Virtuelle");

            } else if (srt.equals("Service")) {
                etat = Item.state.valueOf("Null");
                type = Item.type.valueOf("Service");
            }

            List<Item> items = sp.afficher();
            boolean found = false;
            for (Item item : items) {
                if (item.getId_item() == Integer.parseInt(textfield_id.getText())) {
                    found = true;
                }
            }

            if (found == false) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Erreur");
     
                a.setContentText("Vous n'avez pas cet article.");
                a.show();
            } else {
                if (textfield_libelle.getText().equals("") || textfield_libelle.getText().equals("Insérer libellé") || textarea_description.getText().equals("") || textarea_description.getText().equals("Insérer description") || cbs == null) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
               
                                a.setHeaderText("Erreur");a.setContentText("Assurez-vous d'insérer des entrées valides pour les détails de votre item.");
                    a.show();
                } else {
                    Item i = new Item(Integer.parseInt(textfield_id.getText()), textfield_libelle.getText(), textarea_description.getText(), type, etat, imageview_imageurl.getImage().impl_getUrl(), CurrentUser.getInstance().getId(), id_cat, 0,0,0);
                    sp.modifier(i);
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Notification");
                    a.setContentText("L'entrée d'item a été modifiée avec succès!");
                    a.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemAdminFXML.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            }

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Erreur");
            a.setContentText("Veuillez insérer un id de votre article.");
            a.show();
        }


    }

    @FXML
    private void route_CategorieItems(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieItemsAdminFXML.fxml"));
        Parent root = loader.load();
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
    private void route_AjouterItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    private void route_AfficherItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    private void route_ChercherItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChercherItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    private void supprimer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Operation");

        a.setContentText("Sélectionner item à supprimer:");
        a.show();

    }

    @FXML
    private void shutdown(MouseEvent event) throws IOException {
        Platform.exit();

    }

    @FXML
    private void minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}