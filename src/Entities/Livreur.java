package Entities;

import Utils.Enums.Roles;

public class Livreur extends  Utilisateur {
    public Livreur() {
    }

    public Livreur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        super(password, nom, prenom, email, adresse, avatar_url, role);
    }
    public Livreur(String password, String nom, String prenom, String email, String adresse, String avatar_url, int archived, Roles role) {
        super(password, nom, prenom, email, adresse, avatar_url, role,archived);

    }  public Livreur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user);

    }
    public Livreur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user,archived);

    }
    public Livreur(String password, String nom, String prenom, String avatar_url,int id_user,int archived) {
        super(password, nom, prenom, avatar_url,id_user,archived);

    }

}