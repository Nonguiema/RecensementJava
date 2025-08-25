package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatistiqueRecensement {

    public static DonneesStatistiques calculerStatistiquesParZone(int zoneId) {
        int total = 0, enfants = 0, adultes = 0, ages = 0, actifs = 0, chomeurs = 0;

        String sql = """
            SELECT age, profession FROM citoyen
            INNER JOIN famille ON citoyen.famille_id = famille.id
            WHERE famille.zone_id = ?
        """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, zoneId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int age = rs.getInt("age");
                String profession = rs.getString("profession").toLowerCase();
                total++;

                if (age < 18) enfants++;
                else if (age < 65) adultes++;
                else ages++;

                if (profession.contains("aucun") || profession.contains("chômeur") || profession.contains("sans emploi")) {
                    chomeurs++;
                } else {
                    actifs++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new DonneesStatistiques(total, enfants, adultes, ages, actifs, chomeurs);
    }
}
