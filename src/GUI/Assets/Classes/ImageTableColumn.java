package GUI.Assets.Classes;

import Entities.Item;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableColumn extends TableColumn<Item, String> {

    public ImageTableColumn(String text) {
        super(text);
        setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        setCellFactory(param -> new TableCell() {
            private final ImageView imageView = new ImageView();


            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    Image image = new Image(imagePath);
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });
    }
}
