package GUI;


import Entities.Reponse;

import Services.ServiceReponse;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AffichageReponseUserControlleur  implements Initializable {

    @FXML
    private ImageView mod ;
    HBox hbox ;
    @FXML
    private ImageView supp ;
    @FXML
    private VBox pnrep = null;
    @FXML
    private Label titrerep;

    @FXML
    private Label descriptionrep;
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Label idrep;
    private List<Reponse> reponses;

    @FXML
    private int idReclamation;

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
        System.out.println(idReclamation);
        afficherRe(idReclamation);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        }


    @FXML
    private void afficherRe(int idReclamation) {
        ServiceReponse sp = new ServiceReponse();
        System.out.println(idReclamation);
        reponses = sp.afficherRep(idReclamation);
        System.out.println(reponses);
        System.out.println(idReclamation);
        VBox vbox = new VBox();

        scrollpane.setContent(vbox);

        Slider slider = new Slider(0.5, 2, 1);
        slider.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            vbox.setScaleX(newValue.doubleValue());
            vbox.setScaleY(newValue.doubleValue());
        });
        scrollpane.setStyle("-fx-background-color: transparent;");

        for (Reponse obj : reponses) {


            Label titrerep = new Label(obj.getTitre_reponse());
            Label descriptionrep = new Label(obj.getDescription_reponse());

            Image image = new Image(getClass().getResource("/GUI/Assets/icons/blue/trash-bin.png").toExternalForm());

            ImageView supp = new ImageView();
            supp.setImage(image);
            supp.setFitWidth(40);
            supp.setFitHeight(40);
            // set the preferred width of each label
            titrerep.setPrefWidth(150);
            descriptionrep.setPrefWidth(250);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), supp);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            // add the animation to the ImageView when the mouse enters
            supp.setOnMouseEntered(event -> {
                scaleTransition.play();
            });

            // remove the animation from the ImageView when the mouse exits
            supp.setOnMouseExited(event -> {
                scaleTransition.stop();
                supp.setScaleX(1);
                supp.setScaleY(1);
            });


            titrerep.setStyle("-fx-text-fill: white;-fx-font-family: 'Sans serif' !important;");
            descriptionrep.setStyle("-fx-text-fill: white;-fx-font-family: 'Sans serif' !important;");
            HBox hbox= new HBox(titrerep, descriptionrep,supp);
            hbox.setSpacing(30);

            // set the padding around the hbox container
            hbox.setPadding(new Insets(10, 10, 10, 10));

            supp.setOnMouseClicked(event1 -> {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setHeaderText(null);
                confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
                Optional<ButtonType> result2 = confirmation.showAndWait();


                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                    boolean result = sp.supprimer(obj);
                    if (result) {
                        vbox.getChildren().remove(hbox);
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

           /* hbox.setOnMouseClicked(event -> {
                supp.setOnMouseClicked(event1 -> {
                    boolean result = sp.supprimer(obj);
                    if (result) {
                        vbox.getChildren().remove(hbox);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Erreur lors de la suppression.");
                        alert.showAndWait();
                    }
                });
            });*/
         /*   hbox.setOnMouseClicked(event2 -> {
                System.out.println("HBox sélectionné !");
                System.out.println("Titre : " + obj.getTitre_reponse());
                System.out.println("Description : " + obj.getDescription_reponse());
                System.out.println("id : " + obj.getId_reclamation());
                mod.setOnMouseClicked(event3 -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificationReponse.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ModificationReponseControlleur modificationreponseControlleur = loader.getController();
                    modificationreponseControlleur.setTitreText(obj.getTitre_reponse());
                    modificationreponseControlleur.setDescriptionText(obj.getDescription_reponse());
                    modificationreponseControlleur.setIdRepnse(obj.getId_reclamation());

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();


                });
            });   */


            titrerep.setAlignment(Pos.CENTER_LEFT);
            descriptionrep.setAlignment(Pos.CENTER);
            hbox.setSpacing(300);
            hbox.setPrefWidth(1120);
            hbox.setPrefHeight(100);
            hbox.setMaxWidth(descriptionrep.getMaxWidth());
            hbox.setStyle("-fx-background-radius: 25 ;-fx-background-color: transparant;");

           // hbox.setOnMouseEntered(e -> finalhbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 15;"));
            // HBox finalHBox1 = hBox;
            // hBox.setOnMouseExited(e -> finalHBox1.setStyle("-fx-background-radius: 15;  -fx-background-color: rgba(255, 255, 255, 0.5);"));

            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-radius: 15; -fx-background-color: #56144D;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-radius: 15; -fx-background-color: transparent;"));


            //  hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-background-radius: 15;"));
           // hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: transparent;"));
            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(8);
            vbox.setStyle("-fx-background-color: transparent; -fx-background-radius: 25");

            vbox.getChildren().add(hbox);

        }
}

    /*@FXML
    private void afficher() {
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> Reponses = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(Reponses);

    }
*/

    @FXML
    void gotoreclamation(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamationUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /*
    @FXML
    void openModifcation(MouseEvent event) throws IOException {
        Reponse selectedReponse = tableView.getSelectionModel().getSelectedItem();
        String titreR = String.valueOf(selectedReponse.getTitre_reponse());
        String descriptionR = String.valueOf(selectedReponse.getDescription_reponse());

        if (selectedReponse != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificationReponse.fxml"));
            Parent root = loader.load();
            ModificationReponseControlleur modificationreponseControlleur = loader.getController();
            modificationreponseControlleur.setTitreText(selectedReponse.getTitre_reponse());
            modificationreponseControlleur.setDescriptionText(selectedReponse.getDescription_reponse());
            modificationreponseControlleur.setIdRepnse(selectedReponse.getId_reponse());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        }
    }*/
    /*@FXML
    void supprimer(MouseEvent event) {
        Reponse selectedrep = tableView.getSelectionModel().getSelectedItem();
        if (selectedrep != null) {
            ServiceReponse servicerep = new ServiceReponse();

            boolean result = servicerep.supprimer(selectedrep);

            if (result) {
                tableView.getItems().remove(selectedrep);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression .");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner  .");
            alert.showAndWait();
        }

    }
*/
        Stage stage;
        @FXML
        private AnchorPane scenePane;
        public void logout (MouseEvent event){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to exit ! ");

            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) scenePane.getScene().getWindow();

                stage.close();
            }
        }

        @FXML
        public void Minimize (MouseEvent event ){
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
        }

    }

