package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Famille {
    private int id; // ID généré après insertion
    private String nomFamille;
    private String nomPere;
    private String nomMere;
    private int zoneId; // clé étrangère vers ZoneGeographique
    private List<Citoyen> membres;

    public Famille(String nomFamille, String nomPere, String nomMere, int zoneId) {
        this.nomFamille = nomFamille;
        this.nomPere = nomPere;
        this.nomMere = nomMere;
        this.zoneId = zoneId;
        this.membres = new ArrayList<>();
    }

    public void ajouterMembre(Citoyen c) {
        membres.add(c);
    }

    public String getNomFamille() { return nomFamille; }
    public String getNomPere() { return nomPere; }
    public String getNomMere() { return nomMere; }
    public List<Citoyen> getMembres() { return membres; }
    public int getId() { return id; }

    // Enregistre la famille dans la base de données
    public void enregistrerDansBDD() {
        try (Connection conn = ConnexionBDD.getConnection()) {
            String sql = "INSERT INTO famille (nom_famille, nom_pere, nom_mere, zone_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nomFamille);
            ps.setString(2, nomPere);
            ps.setString(3, nomMere);
            ps.setInt(4, zoneId);
            ps.executeUpdate();

            // Récupérer l’ID auto-généré
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("Famille enregistrée avec ID : " + id);

                // Enregistrer tous les membres dans la table citoyen
                for (Citoyen c : membres) {
                    c.enregistrerDansBDD(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Famille " + nomFamille + " (" + membres.size() + " membres)";
    }
}

