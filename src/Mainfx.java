import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Mainfx extends Application {
    private double xOffset;
    private double yOffset;
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/AjoutReclamationUser.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Treydi");

           /* primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            primaryStage.setWidth(1920);
            primaryStage.setHeight(1080);
*/     primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            primaryStage.setWidth(1600);
            primaryStage.setHeight(900);

            Image image = new Image("/GUI/Assets/images/log-04.png",32,32,true,true);
            scene.getStylesheets().add("/GUI/Assets/css/style.css");




            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Err"+ex.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}


