package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

public class RecensementForm extends JFrame {

    private JComboBox<ZoneItem> zoneCombo;
    private JTextField anneeField, dateField;
    private JTextArea remarqueArea;
    private JLabel statsLabel;
    private DonneesStatistiques stats;

    public RecensementForm() {
        setTitle("Nouvelle campagne de recensement");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Champs
        anneeField = new JTextField();
        dateField = new JTextField("2025-08-01"); // format yyyy-MM-dd
        remarqueArea = new JTextArea(3, 20);

        // Zone
        panel.add(new JLabel("Zone géographique :"));
        zoneCombo = new JComboBox<>();
        chargerZones();
        panel.add(zoneCombo);

        panel.add(new JLabel("Année :"));
        panel.add(anneeField);

        panel.add(new JLabel("Date du recensement (yyyy-MM-dd) :"));
        panel.add(dateField);

        panel.add(new JLabel("Remarques :"));
        panel.add(new JScrollPane(remarqueArea));

        // Bouton Calcul
        JButton calculerBtn = new JButton("📊 Calculer pour cette zone");
        calculerBtn.addActionListener(e -> calculerStatistiques());
        panel.add(calculerBtn);

        // Résultats
        statsLabel = new JLabel("Statistiques non encore calculées.");
        panel.add(statsLabel);

        // Bouton Enregistrer
        JButton enregistrerBtn = new JButton("✅ Enregistrer dans la base");
        enregistrerBtn.addActionListener(e -> enregistrerRecensement());
        panel.add(enregistrerBtn);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void chargerZones() {
        try (var conn = ConnexionBDD.getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("SELECT id, nom FROM zone_geographique")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                zoneCombo.addItem(new ZoneItem(id, nom));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculerStatistiques() {
        ZoneItem zone = (ZoneItem) zoneCombo.getSelectedItem();
        if (zone == null) return;

        stats = StatistiqueRecensement.calculerStatistiquesParZone(zone.id);

        statsLabel.setText("<html>📊 Population : " + stats.populationTotale
                + "<br>👶 Enfants : " + stats.nombreEnfants
                + "<br>🧑 Adultes : " + stats.nombreAdultes
                + "<br>👴 Aînés : " + stats.nombreAges
                + "<br>💼 Actifs : " + stats.nombreActifs
                + "<br>❌ Chômeurs : " + stats.nombreChomeurs + "</html>");
    }

    private void enregistrerRecensement() {
        try {
            int annee = Integer.parseInt(anneeField.getText());
            Date date = Date.valueOf(dateField.getText());
            ZoneItem zone = (ZoneItem) zoneCombo.getSelectedItem();
            String remarque = remarqueArea.getText();

            if (stats == null) {
                JOptionPane.showMessageDialog(this, "Veuillez d'abord calculer les statistiques.");
                return;
            }

            Recensement r = new Recensement(annee, date, zone.id, stats, remarque);
            r.enregistrerDansBDD();

            JOptionPane.showMessageDialog(this, "Recensement enregistré !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }
    }

    // Classe interne pour stocker une zone dans JComboBox
    class ZoneItem {
        int id;
        String nom;

        ZoneItem(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        public String toString() {
            return nom;
        }
    }

    public static void main(String[] args) {
        new RecensementForm();
    }
}
