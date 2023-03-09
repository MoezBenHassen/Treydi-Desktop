package GUI;

import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import Utils.CurrentUser;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;



public class AffichageReclamationControlleurUser  implements Initializable {

    @FXML
    private TextField titrechercher;
    private  List<Reclamation> reclist ;
    @FXML
    private  ScrollPane scrollPane1  ;
    HBox hbox1 ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sp = new ServiceReclamation();
        // lena ba3eed yo5ou id_user
        //reclist = sp.afficherUser(CurrentUser.getInstance().getId_user());
        reclist = sp.afficher();
        afficher(reclist) ;

        titrechercher.textProperty().addListener((obs, oldVal, newVal) -> {
            String titre = titrechercher.getText().trim();
            List<Reclamation> reclamationsFiltrees1 = reclist.stream()
                    .filter(recc -> recc.getTitre_reclamation().toLowerCase().contains(titre.toLowerCase()))
                    .collect(Collectors.toList());
            List<Reclamation> reclamationsFiltrees2 = reclist.stream()
                    .filter(recc -> recc.getDescription().toLowerCase().contains(titre.toLowerCase()))
                    .collect(Collectors.toList());
            List<Reclamation> reclamationsFiltrees = new ArrayList<>(); ;
            reclamationsFiltrees.addAll(reclamationsFiltrees1) ;
            reclamationsFiltrees.addAll(reclamationsFiltrees2);
            afficher(reclamationsFiltrees);
        });
        afficher(reclist) ;
    }

     @FXML
     private void afficher(List<Reclamation> reclist){
        ServiceReclamation sp = new ServiceReclamation();

        VBox vbox1 = new VBox();
        scrollPane1.setContent(vbox1);
        scrollPane1.setStyle("-fx-background-color: transparent;");
        Slider slider = new Slider(0.5, 2, 1);
        slider.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            vbox1.setScaleX(newValue.doubleValue());
            vbox1.setScaleY(newValue.doubleValue());
        });

        for (Reclamation rec : reclist) {

            Label titrerec = new Label(rec.getTitre_reclamation());
            Label descriptionrec = new Label(rec.getDescription());

            Label etat = new Label(rec.getEtat_reclamation().toString());

            Image image = new Image(getClass().getResource("/GUI/Assets/icons/pink/trash-bin.png").toExternalForm());
            Image image2 = new Image(getClass().getResource("/GUI/Assets/icons/pink/pencil.png").toExternalForm());
            ImageView mod = new ImageView();
            mod.setImage(image2);
            mod.setFitWidth(35);
            mod.setFitHeight(35);

            ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(100), mod);
            scaleTransition2.setToX(1.2);
            scaleTransition2.setToY(1.2);

            mod.setOnMouseEntered(event -> {
                scaleTransition2.play();
            });


            mod.setOnMouseExited(event -> {
                scaleTransition2.stop();
                mod.setScaleX(1);
                mod.setScaleY(1);
            });

            ImageView supp = new ImageView();
            supp.setImage(image);
            supp.setFitWidth(40);
            supp.setFitHeight(40);
            titrerec.setPrefWidth(150);
            descriptionrec.setPrefWidth(250);
            etat.setPrefWidth(100);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), supp);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);

            supp.setOnMouseEntered(event -> {
                scaleTransition.play();
            });
            supp.setOnMouseExited(event -> {
                scaleTransition.stop();
                supp.setScaleX(1);
                supp.setScaleY(1);
            });
            titrerec.setStyle("-fx-text-fill: white;-fx-font-family: 'Sans serif' !important;");
            descriptionrec.setStyle("-fx-text-fill: white;-fx-font-family: 'Sans serif' !important;");
            etat.setStyle("-fx-text-fill: white;-fx-font-family: 'Sans serif' !important;");
            HBox hbox1 = new HBox( titrerec, descriptionrec,etat,mod ,supp);

            hbox1.setSpacing(30);

            hbox1.setPadding(new Insets(30, 30, 30, 30));
            descriptionrec.setWrapText(true);
            descriptionrec.setPrefWidth(500);
            titrerec.setWrapText(true);
            titrerec.setPrefWidth(250);

            titrerec.setAlignment(Pos.CENTER_LEFT);
            descriptionrec.setAlignment(Pos.CENTER_LEFT);
            etat.setAlignment(Pos.CENTER);
            hbox1.setPrefWidth(1120);
            hbox1.setMaxWidth(descriptionrec.getMaxWidth());
            hbox1.setStyle("-fx-background-radius: 25 ;-fx-background-color: transparent;");
            hbox1.setOnMouseEntered(e -> hbox1.setStyle("-fx-background-radius: 15; -fx-background-color: #56144D;"));
            hbox1.setOnMouseExited(e -> hbox1.setStyle("-fx-background-radius: 15; -fx-background-color: transparent;"));


            hbox1.setAlignment(Pos.CENTER_LEFT);
            vbox1.setAlignment(Pos.CENTER);
            vbox1.setSpacing(8);
            vbox1.setStyle("-fx-background-color: transparent; -fx-background-radius: 25");

            vbox1.getChildren().add(hbox1);


            hbox1.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2)  {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReponseUser.fxml"));
                    Parent root = loader.load();

                    AffichageReponseUserControlleur controller = loader.getController();

                    System.out.println(rec.getId_reclamation());
                    controller.setIdReclamation(rec.getId_reclamation());

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) hbox1.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                }

            });
            supp.setOnMouseClicked(event1 -> {
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Confirmation");
                    confirmation.setHeaderText(null);
                    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
                    Optional<ButtonType> result2 = confirmation.showAndWait();

                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                    boolean result = sp.supprimer(rec);
                    if (result) {
                        vbox1.getChildren().remove(hbox1);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Suppression réussie");
                        alert.setHeaderText(null);
                        alert.setContentText("Suppression effectuée avec succès.");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Erreur lors de la suppression.");
                        alert.showAndWait();
                    }
                    }
                });

                    mod.setOnMouseClicked(event3 -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamationUser.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ModifierReclamationUserControlleur modificationreclamationControlleur = loader.getController();
                        modificationreclamationControlleur.setTitreText(rec.getTitre_reclamation());
                        modificationreclamationControlleur.setDescriptionText(rec.getDescription());
                        modificationreclamationControlleur.setIdReclamation(rec.getId_reclamation());
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                        ref();
                    });
        }
    }
    private  void ref(){
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclis ;
        reclis = sr.afficher();
        afficher(reclis);
    }

       /* tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();
                // load the affichageReponse.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageReponseUser.fxml"));
                try {
                    Parent root = loader.load();
                    // assuming you have a controller for the affichageReponse.fxml file
                    AffichageReponseUserControlleur controller = loader.getController();
                    // pass any necessary data to the controller
                    controller.setIdReclamation(selectedReclamation.getId_reclamation());
                    // switch to the affichageReponse.fxml view
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) tableView.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        titreC.setCellValueFactory(new PropertyValueFactory<>("titre_reclamation"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        afficher();
        ServiceReclamation sr = new ServiceReclamation();
        reclist = sr.afficher();
*/

    @FXML
    private void gotoajout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamationUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /*
    @FXML
    void openReponseaffichage(MouseEvent event) throws IOException {
        Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();

        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a reclamation");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();

        AjoutReponseControlleur ajoutReponseController = loader.getController();

        ajoutReponseController.setIdReclamation(selectedReclamation.getId_reclamation());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
*/

    /*
    @FXML
    void openModifcation(MouseEvent event) throws IOException {
        Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();
        String tittre = String.valueOf(selectedReclamation.getTitre_reclamation());
        String description = String.valueOf(selectedReclamation.getDescription());

        if (selectedReclamation != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamationUser.fxml"));
            Parent root = loader.load();
            ModifierReclamationUserControlleur modificationControlleur = loader.getController();
            modificationControlleur.setTitreText(selectedReclamation.getTitre_reclamation());
            modificationControlleur.setDescriptionText(selectedReclamation.getDescription());
            modificationControlleur.setIdReclamation(selectedReclamation.getId_reclamation());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
            afficher();

        }
    }
*/
    Stage stage;
    @FXML
    private AnchorPane scenePane;
    public void logout(MouseEvent event) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();

            stage.close();
        }
    }

    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);

    }

}
