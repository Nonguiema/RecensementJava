package app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Recensement {
    private int id;
    private int annee;
    private Date dateRecensement;
    private int zoneId;
    private int populationTotale;
    private int nombreEnfants;       // < 18 ans
    private int nombreAdultes;       // 18 - 64 ans
    private int nombreAges;          // >= 65 ans
    private int nombreActifs;        // en emploi
    private int nombreChomeurs;      // sans emploi
    private String remarque;

    // ✅ Constructeur complet avec tous les champs
    public Recensement(int annee, Date dateRecensement, int zoneId,
                       int populationTotale, int nombreEnfants, int nombreAdultes,
                       int nombreAges, int nombreActifs, int nombreChomeurs, String remarque) {
        this.annee = annee;
        this.dateRecensement = dateRecensement;
        this.zoneId = zoneId;
        this.populationTotale = populationTotale;
        this.nombreEnfants = nombreEnfants;
        this.nombreAdultes = nombreAdultes;
        this.nombreAges = nombreAges;
        this.nombreActifs = nombreActifs;
        this.nombreChomeurs = nombreChomeurs;
        this.remarque = remarque;
    }

    // ✅ Constructeur simplifié avec DonneesStatistiques
    public Recensement(int annee, Date dateRecensement, int zoneId, DonneesStatistiques stats, String remarque) {
        this.annee = annee;
        this.dateRecensement = dateRecensement;
        this.zoneId = zoneId;
        this.populationTotale = stats.populationTotale;
        this.nombreEnfants = stats.nombreEnfants;
        this.nombreAdultes = stats.nombreAdultes;
        this.nombreAges = stats.nombreAges;
        this.nombreActifs = stats.nombreActifs;
        this.nombreChomeurs = stats.nombreChomeurs;
        this.remarque = remarque;
    }

    public void enregistrerDansBDD() {
        String sql = """
            INSERT INTO recensement (
                annee, date_recensement, zone_id,
                population_totale, nombre_enfants, nombre_adultes,
                nombre_ages, nombre_actifs, nombre_chomeurs, remarque
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, annee);
            stmt.setDate(2, dateRecensement);
            stmt.setInt(3, zoneId);
            stmt.setInt(4, populationTotale);
            stmt.setInt(5, nombreEnfants);
            stmt.setInt(6, nombreAdultes);
            stmt.setInt(7, nombreAges);
            stmt.setInt(8, nombreActifs);
            stmt.setInt(9, nombreChomeurs);
            stmt.setString(10, remarque);

            stmt.executeUpdate();
            System.out.println("✅ Recensement enregistré avec toutes les statistiques !");
        } catch (SQLException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}
