import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class javafxMain extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/Articles.fxml"));
            Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);

            Scene scene = new Scene(root);


            primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            // set window size
            primaryStage.setWidth(1600);
            primaryStage.setHeight(900);

            scene.getStylesheets().add("GUI/Assets/css/style.css");

            primaryStage.getIcons().add(image);
            primaryStage.setTitle("Treydi ");
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