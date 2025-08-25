package app;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class RecensementFormPanel extends JPanel {
    public RecensementFormPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtAnnee = new JTextField();
        JTextField txtDate = new JTextField();
        JTextField txtZoneId = new JTextField();
        JTextArea txtRemarque = new JTextArea();

        add(new JLabel("Année :")); add(txtAnnee);
        add(new JLabel("Date (YYYY-MM-DD) :")); add(txtDate);
        add(new JLabel("ID Zone :")); add(txtZoneId);
        add(new JLabel("Remarques :")); add(new JScrollPane(txtRemarque));

        JButton btnCalculer = new JButton("Calculer et Enregistrer");
        add(new JLabel()); add(btnCalculer);

        btnCalculer.addActionListener(e -> {
            try {
                DonneesStatistiques stats = StatistiqueRecensement.calculerStatistiquesParZone(Integer.parseInt(txtZoneId.getText()));
                Recensement r = new Recensement(
                    Integer.parseInt(txtAnnee.getText()),
                    Date.valueOf(txtDate.getText()),
                    Integer.parseInt(txtZoneId.getText()),
                    stats.populationTotale,
                    stats.nombreEnfants,
                    stats.nombreAdultes,
                    stats.nombreAges,
                    stats.nombreActifs,
                    stats.nombreChomeurs,
                    txtRemarque.getText()
                );
                r.enregistrerDansBDD();
                JOptionPane.showMessageDialog(this, "Recensement calculé et enregistré !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
