import Entities.Coupon;
import Entities.Utilisateur;
import Services.CategorieCouponService;
import Services.CouponService;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategorieCouponService cs = new CategorieCouponService();
        System.out.println(cs.afficher());
    }
}