/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import java.util.Properties;
import java.util.Random;

import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;





/**
 *
 * @author houssem
 */
public class ForgotPasswordController {


    @FXML
    private TextField emailOrUsernameField;

    @FXML
    private void handleResetPassword(ActionEvent event) {
        String email = emailOrUsernameField.getText();

        // Check if email or username is empty
        if (email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter your email or username.");
            alert.showAndWait();
            return;
        }

        // Get user by email or username
        UtilisateurService us = new UtilisateurService();
        Utilisateur user = us.getUserByEmail(email);
        System.out.println(user);
        if (user == null) {
            // User not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No user found with the provided email or username.");
            alert.showAndWait();
            return;
        }

        // Generate new random password
        String newPassword = generateRandomPassword();

        // Update user's password

        us.updateUserPassword(user,newPassword);

        // Send password reset email
        sendPasswordResetEmail(user, newPassword);

        // Display success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your new password has been sent to your email address.");
        alert.showAndWait();

    }
    private String generateRandomPassword() {
        // Implement this method to generate a random password
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        Random random = new Random();
        int length = 8;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
    private void sendPasswordResetEmail(Utilisateur user, String newPassword) {
        // Get the email properties from your application configuration
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "medadem2000@gmail.com";
        String password = "pleixhcsocxnwhdu";

        // Set the sender and recipient email addresses
        String from = "medadem2000@gmail.com";
        String to = user.getEmail();

        // Set the email subject and content
        String subject = "Password reset request";
        String content = "Hello " + user.getNom() + ",\n\n"
                + "Your password has been reset. Your new password is: " + newPassword + "\n\n"
                + "Please login with your new password and update it as soon as possible.\n\n"
                + "Best regards,\n"
                + "Treydi";

        // Create the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            // Send the email message
            Transport.send(message);

            System.out.println("Password reset email sent successfully to " + user.getEmail());

        } catch (MessagingException e) {
            System.out.println("Failed to send password reset email to " + user.getEmail());
            e.printStackTrace();
        }
    }

}