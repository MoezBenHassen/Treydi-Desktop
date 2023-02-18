package Entities;

import Utils.Enums.Roles;

public class livreur extends utilisateur {
    public livreur( String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        super(password, nom, prenom, email, adresse, avatar_url, role);
    }
}
