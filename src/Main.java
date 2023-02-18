import Entities.admin;
import Entities.livreur;
import Entities.utilisateur;
import Services.utilisateurService;
import Utils.Enums.Roles;

public class Main {
    public static void main(String[] args) {
        utilisateurService us =new utilisateurService();
        System.out.println(us.afficher()) ;

    }
}