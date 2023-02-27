import Entities.Admin;
import Entities.Livreur;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        Utilisateur lv = new Admin("dd","ddd","ddd","fff","fff",null,Roles.admin,0);
        us.add(lv);

    }
}