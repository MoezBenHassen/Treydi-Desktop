package APIs;

import Entities.Reclamation;
import Services.ServiceReclamation ;
import Utils.MyDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StatistiqueReclamation {
    Connection cnx;
    Statement stm;
    public StatistiqueReclamation() {
        cnx = MyDB.getInstance().getCon();
    }
    public int getNombreReclamationParMois(int mois, int annee) {
        int nombreReclamation = 0;
        try {
            String qry = "SELECT COUNT(*) FROM `reclamation` WHERE MONTH(date_creation) = ? AND YEAR(date_creation) = ? AND archived = 0";
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, mois);
            pst.setInt(2, annee);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombreReclamation = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nombreReclamation;
    }
    public int getNombreReclamationTraiteesParMois(int mois, int annee) {
        int nbrRT = 0;
        try {
            String qry = "SELECT COUNT(*) AS nbReclamations FROM reclamation WHERE etat_reclamation = 'Traité' AND archived = 0 AND YEAR(date_creation) = ? AND MONTH(date_creation) = ?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, annee);
            ps.setInt(2, mois);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nbrRT = rs.getInt("nbReclamations");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbrRT;
    }

    
}
