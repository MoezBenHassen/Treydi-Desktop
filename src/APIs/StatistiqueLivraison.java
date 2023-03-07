package APIs;

import Utils.MyDB;

import java.sql.*;

public class StatistiqueLivraison {
    Connection cnx;
    Statement stm;
    public StatistiqueLivraison() {
        cnx = MyDB.getInstance().getCon();
    }
    public int getNombreLivraisonAnnulerParMois(int mois, int annee) {
        int nbrRT = 0;
        try {
            String qry = "SELECT COUNT(*) AS nblivraisonA FROM `livraison` WHERE etat_livraison = 'Annule' AND archived = 0 AND YEAR(date_creation_livraison) = ? AND MONTH(date_creation_livraison) = ?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, annee);
            ps.setInt(2, mois);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nbrRT = rs.getInt("nblivraisonA");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbrRT;
    }
    public int getNombreLivraisonTerminerParMois(int mois, int annee) {
        int nbrRT = 0;
        try {
            String qry = "SELECT COUNT(*) AS nblivraisonT FROM `livraison` WHERE etat_livraison = 'Termine' AND archived = 0 AND YEAR(date_creation_livraison) = ? AND MONTH(date_creation_livraison) = ?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, annee);
            ps.setInt(2, mois);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nbrRT = rs.getInt("nblivraisonT");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbrRT;
    }






}
