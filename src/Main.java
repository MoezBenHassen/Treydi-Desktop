<<<<<<< HEAD
import java.io.IOException;

import Entities.Utilisateur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;

import Services.UtilisateurService;
import Utils.Enums.Roles;

import java.sql.SQLException;
import java.sql.Date;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage primaryStage) throws IOException {

        Utilisateur.setLoginid(4);
        Utilisateur.setLogintype(0);


        if (Utilisateur.getLogintype() == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("GUI/AfficherItemAdminFXML.fxml"));
            Scene scene = new Scene(root);

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
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            primaryStage.setTitle("Treydi Desktop Edition");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();


        } else if (Utilisateur.getLogintype() == 1) {

            Parent root = FXMLLoader.load(getClass().getResource("GUI/AfficherItemUserFXML.fxml"));
            Scene scene = new Scene(root);

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
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            primaryStage.setTitle("Treydi Desktop Edition");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();


        }





public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        Utilisateur tr = new Trader("aaz", "NewNom5", "NewPrenom5", "aaz","Tunis","file:/D:/avatar1.png",Roles.trader, 12,"2000-1-1",0);

        us.add(tr);


    }

}





