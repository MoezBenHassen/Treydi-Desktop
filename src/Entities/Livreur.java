package Entities;

import Utils.Enums.Roles;

public class Livreur extends  Utilisateur {
    public Livreur() {
    }

    public Livreur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        super(password, nom, prenom, email, adresse, avatar_url, role);
    }
}