import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;

import java.sql.SQLException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        Utilisateur tr = new Trader("newpassword3", "NewNom5", "NewPrenom5", "newavatar_urllll","fff","ff",Roles.trader, 12,new Date(2000,1,1),21,0);

        us.modifier(tr);

    }
}