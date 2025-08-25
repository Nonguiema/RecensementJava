package app;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Citoyen {
    private String nom, prenom, sexe, profession, etatCivil, adresse;
    private int age;

    public Citoyen(String nom, String prenom, int age, String sexe, String profession, String etatCivil, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.profession = profession;
        this.etatCivil = etatCivil;
        this.adresse = adresse;
    }

    public void enregistrerDansBDD(int idFamille) {
        try (Connection conn = ConnexionBDD.getConnection()) {
            String sql = "INSERT INTO citoyen (nom, prenom, age, sexe, profession, etat_civil, adresse, famille_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setInt(3, age);
            ps.setString(4, sexe);
            ps.setString(5, profession);
            ps.setString(6, etatCivil);
            ps.setString(7, adresse);
            ps.setInt(8, idFamille);
            ps.executeUpdate();
            System.out.println("✅ Citoyen enregistré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
