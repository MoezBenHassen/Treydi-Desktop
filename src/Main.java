import Entities.Coupon;
import Entities.Utilisateur;
import APIs.GenerateQRCode;
import Services.CouponService;
import Services.UtilisateurService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        //Coupon c= new Coupon(3);
        CouponService cs= new CouponService();
        //String s= cs.getCode(c.getId_coupon());
        Utilisateur u= new Utilisateur(6);
        List<Coupon> coupons = cs.afficherParUser(u);
        System.out.println(coupons);
        UtilisateurService us= new UtilisateurService();
        System.out.println(us.afficherUsers());
        System.out.println(us.afficherTraders());
        //us.setScore(u,1000);
        //GenerateQRCode g= new GenerateQRCode();
        //g.generate(s);
    }
}