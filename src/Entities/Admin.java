package Entities;

import Utils.Enums.Roles;

import java.util.Date;

public class Admin extends Utilisateur{
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        super();
    }
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user);

    }
    public Admin(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user,archived);

    }

    public Admin() {
    }
}