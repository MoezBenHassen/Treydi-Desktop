package GUI.Controllers;

import APIs.GenerateQRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

public class QrCodeController implements Initializable {
@FXML
Labeled label;

@FXML
GridPane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AfficherImage();
    }




    public void setQrCode(String c) throws IOException {
            String text = c;
            String filePath = "src/APIs/img/MyQR.png";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


            BitMatrix bitMatrix = null;
            try {
                bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500, hints);
            } catch (WriterException e) {
                System.err.println("Error generating QR code: " + e.getMessage());
                return;
            }


            Path path = FileSystems.getDefault().getPath(filePath);
            try {
                MatrixToImageWriter.writeToFile(bitMatrix, "PNG", path.toFile());
            } catch (IOException e) {
                System.err.println("Error writing QR code to file: " + e.getMessage());
            }

            System.out.println("QR code generated successfully.");
        }

        public void AfficherImage () {
            int row = 0;
            int col = 0;
            Image image = new Image("APIs/img/MyQR.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            VBox vbox = new VBox();
             vbox.getChildren().add(imageView);
            vbox.setMaxWidth(Double.MAX_VALUE);
            vbox.setMaxHeight(Double.MAX_VALUE);
            pane.add(vbox, col, row);
            pane.setHalignment(vbox, HPos.CENTER);
            pane.setValignment(vbox, VPos.CENTER);

        }
    public void setTitreText(String titre) {
        label.setText(titre);
    }

    }

