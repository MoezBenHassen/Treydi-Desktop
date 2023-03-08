package GUI.Controllers;

import APIs.Captcha;
import APIs.CaptchaGenerator;
import Entities.Livreur;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Entities.Trader;
import Utils.Enums.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class InscriptionController implements Initializable {
    @FXML
    public WebView reCaptcha;
    @FXML
    public TextField captchaResponse;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private ToggleGroup role;

    private WebEngine webEngine;
    Captcha captcha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = reCaptcha.getEngine();
        webEngine.setJavaScriptEnabled(true);
        captcha = CaptchaGenerator.getCaptcha();
        webEngine.loadContent("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <style>\n" +
                "        html, body {\n" +
                "height: 100%;\n" +
                "margin: 0;\n" +
                "background-color: " + captcha.getBgColor() + ";\n" +
                "}\n" +
                "\n" +
                "#section1 {\n" +
                "    height: 90%; \n" +
                "text-align:center; \n" +
                "    display:table;\n" +
                "    width:100%;\n" +
                "}\n" +
                "\n" +
                "#section1 h1 {\n" +
                "    display:table-cell; \n" +
                "    vertical-align:middle;\n" +
                "    color: " + captcha.getColor() + "; \n" +
                "    font-family: " + captcha.getFont() + ";" +
                "font-style: oblique;\n" +
                "    font-size: 40px;" +
                "text-decoration: line-through;" +
                "    }\n" +
                "    </style>\n" +
                "    <div class=\"section\" id=\"section1\">\n" +
                "        <h1>" + captcha.getGenenumber() + "</h1>\n" +
                "        </div> \n" +
                "</body>\n" +
                "</html>");

    }

    @FXML
    private void openLoginPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

            // Close the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            System.out.println("Exception loading FXML file: " + ex.getMessage());
        }
    }

    public boolean validateCaptcha() {
        return captchaResponse.getText().equals(captcha.getGenenumber());
    }

    @FXML
    private void add(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        RadioButton selected_role = (RadioButton) role.getSelectedToggle();
        Roles rt = null;
        String srt = selected_role.getText();

        if (validateCaptcha()) {
            if (srt.equals("trader")) {
                rt = Roles.valueOf("trader");
                Utilisateur user = new Trader(tfpassword.getText(), tfnom.getText(), tfprenom.getText(), tfemail.getText(), tfadresse.getText(), "GUI/Assets/icons/avatar1.png", rt, 0,"2000-2-2", 0);
                if (tfpassword.getText().isEmpty() || tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty() || tfadresse.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Please fill in all the fields.");
                    alert.show();
                } else {
                    us.add(user);
                    openLoginPage(event);
                }
            } else if (srt.equals("livreur")) {
                rt = Roles.valueOf("livreur");
                Utilisateur user2 = new Livreur(tfpassword.getText(), tfnom.getText(), tfprenom.getText(), tfemail.getText(), tfadresse.getText(), "GUI/Assets/icons/avatar1.png", Roles.livreur, 0);
                if (tfpassword.getText().isEmpty() || tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty() || tfadresse.getText().isEmpty()) {

                    System.out.println("Error: Please fill in all the fields.");
                } else {
                    us.add(user2);
                    openLoginPage(event);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "CAPTCHA validation failed. Please try again.");
            alert.show();
        }
    }
    public void refreshCaptcha(ActionEvent actionEvent) {
        captcha = CaptchaGenerator.getCaptcha();
        webEngine.loadContent("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <style>\n" +
                "        html, body {\n" +
                "height: 100%;\n" +
                "margin: 0;\n" +
                "background-color: "+captcha.getBgColor()+";\n" +
                "}\n" +
                "\n" +
                "#section1 {\n" +
                "    height: 90%; \n" +
                "text-align:center; \n" +
                "    display:table;\n" +
                "    width:100%;\n" +
                "}\n" +
                "\n" +
                "#section1 h1 {\n" +
                "    display:table-cell; \n" +
                "    vertical-align:middle;\n" +
                "    color: "+captcha.getColor()+"; \n" +
                "    font-family: "+captcha.getFont()+";"+
                "font-style: oblique;\n" +
                "    font-size: 40px;"+
                "text-decoration: line-through;"+
                "    }\n" +
                "    </style>\n" +
                "    <div class=\"section\" id=\"section1\">\n" +
                "        <h1>"+captcha.getGenenumber()+"</h1>\n" +
                "        </div> \n" +
                "</body>\n" +
                "</html>");
    }
}