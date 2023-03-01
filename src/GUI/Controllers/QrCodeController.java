package GUI.Controllers;

import APIs.GenerateQRCode;
import javafx.fxml.Initializable;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QrCodeController implements Initializable {

private ImageView qrcode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setQrCode(String c) throws IOException {
            GenerateQRCode g = new GenerateQRCode();
            g.generate(c);
        }

    }

