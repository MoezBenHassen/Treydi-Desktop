import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class javafxMain extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/ListeArticles.fxml"));

            Scene scene = new Scene(root);

            primaryStage.initStyle(StageStyle.UNDECORATED);

            // Mouse drag scene
            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            // set window size
            primaryStage.setWidth(1600);
            primaryStage.setHeight(900);
            // add css to scene
            scene.getStylesheets().add("GUI/Assets/css/style.css");
            //Add app icon
            Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
            primaryStage.getIcons().add(image);
            //Add app title
            primaryStage.setTitle("Treydi ");
            // make scene tranparent
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
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