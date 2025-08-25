
package app;

import javax.swing.*;
import java.awt.*;

public class FamilleFormPanel extends JPanel {
    public FamilleFormPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblNomFamille = new JLabel("Nom de la famille :");
        JTextField txtNomFamille = new JTextField();

        JLabel lblNomPere = new JLabel("Nom du père :");
        JTextField txtNomPere = new JTextField();

        JLabel lblNomMere = new JLabel("Nom de la mère :");
        JTextField txtNomMere = new JTextField();

        JLabel lblZoneId = new JLabel("ID Zone :");
        JTextField txtZoneId = new JTextField();

        JButton btnSave = new JButton("Enregistrer la Famille");

        add(lblNomFamille); add(txtNomFamille);
        add(lblNomPere); add(txtNomPere);
        add(lblNomMere); add(txtNomMere);
        add(lblZoneId); add(txtZoneId);
        add(new JLabel()); add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                Famille famille = new Famille(txtNomFamille.getText(), txtNomPere.getText(), txtNomMere.getText(), Integer.parseInt(txtZoneId.getText()));
                famille.enregistrerDansBDD();
                JOptionPane.showMessageDialog(this, "Famille enregistrée avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
