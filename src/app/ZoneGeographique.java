package app;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ZoneGeographique {
    private String nom;
    private String type;
    private int nombreHabitants;

    public ZoneGeographique(String nom, String type, int habitants) {
    this.nom = nom;
    this.type = type;
    this.nombreHabitants = habitants;
}


    public void setNombreHabitants(int n) { this.nombreHabitants = n; }

    public void enregistrerDansBDD() {
        try (Connection conn = ConnexionBDD.getConnection()) {
            String sql = "INSERT INTO zone_geographique (nom, type, nombre_habitants) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, type);
            ps.setInt(3, nombreHabitants);
            ps.executeUpdate();
            System.out.println("Zone enregistrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
