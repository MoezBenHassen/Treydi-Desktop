package Entities;

import Utils.Enums.Roles;

import java.util.Date;

public class Admin extends Utilisateur{
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        super();
    }
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id);

    }
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id,archived);

    }

    public Admin() {
    }
}