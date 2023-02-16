

import Entities.Livraison;
import Services.LivraisonService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        LivraisonService livraisonService = new LivraisonService();
        //ADD
        Livraison livraison = new Livraison(new Date(System.currentTimeMillis()),"En-cours","adresse22",4,2);
        livraisonService.add(livraison);
        //GET
        System.out.println(livraisonService.afficher());
        //UPDATE
        Livraison livraisonUpdate = new Livraison(4,new Date(2022,10,10),"En-cours","adresse22",4,2);
        livraisonService.update(livraisonUpdate);
        //DELETE
        livraisonService.delete(9);
    }
}