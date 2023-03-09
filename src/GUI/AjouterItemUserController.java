package GUI;

import Entities.Categorie_Items;
import Entities.Item;
import Entities.Utilisateur;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterItemUserController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField textfield_libelle;
    @FXML
    private TextArea textarea_description;
    @FXML
    private ToggleGroup radio_type ;

    @FXML
    private ComboBox combobox_cat ;

    @FXML
    private ImageView imageview_imageurl ;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher_combobox_cat() ;
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



    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        ItemService sp = new ItemService();
        CategorieItemsService sp2 = new CategorieItemsService();

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

        if (textfield_libelle.getText().equals("") || textfield_libelle.getText().equals("Insérer libellé") || textarea_description.getText().equals("") || textarea_description.getText().equals("Insérer description") || cbs == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Erreur");

            a.setContentText("Assurez-vous d'insérer des entrées valides pour les détails de votre item.") ;
            a.show();
        } else {
            Item i = new Item(textfield_libelle.getText(), textarea_description.getText(), type, etat, imageview_imageurl.getImage().impl_getUrl(), CurrentUser.getInstance().getId_user(), id_cat,0,0,0);
            sp.ajouter(i);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Operation");

            a.setContentText("L'entrée d'item a été insérée avec succès!") ;
            a.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemUserFXML.fxml"));
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

    @FXML
    private void route_AjouterItem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemFXML.fxml"));
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
    private void route_ModifierItem(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierItemFXML.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemUserFXML.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemUserFXML.fxml"));
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
    private void modifier(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemUserFXML.fxml"));
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

        a.setContentText("Sélectionner item à modifier:");
        a.show();



    }

    @FXML
    private void route_AjouterItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemUserFXML.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChercherItemUserFXML.fxml"));
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
    private void shutdown(MouseEvent event) throws IOException {
        Platform.exit();

    }

    @FXML
    private void minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}