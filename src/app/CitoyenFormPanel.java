package app;

import javax.swing.*;
import java.awt.*;

public class CitoyenFormPanel extends JPanel {
    public CitoyenFormPanel() {
        setLayout(new GridLayout(8, 2, 10, 10));

        JTextField txtNom = new JTextField();
        JTextField txtPrenom = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtSexe = new JTextField();
        JTextField txtProfession = new JTextField();
        JTextField txtEtatCivil = new JTextField();
        JTextField txtAdresse = new JTextField();
        JTextField txtFamilleId = new JTextField();

        add(new JLabel("Nom :")); add(txtNom);
        add(new JLabel("Prénom :")); add(txtPrenom);
        add(new JLabel("Âge :")); add(txtAge);
        add(new JLabel("Sexe :")); add(txtSexe);
        add(new JLabel("Profession :")); add(txtProfession);
        add(new JLabel("État civil :")); add(txtEtatCivil);
        add(new JLabel("Adresse :")); add(txtAdresse);
        add(new JLabel("ID Famille :")); add(txtFamilleId);

        JButton btnSave = new JButton("Enregistrer le Citoyen");
        add(new JLabel()); add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                int idFamille = Integer.parseInt(txtFamilleId.getText());

                Citoyen citoyen = new Citoyen(
                    txtNom.getText(),
                    txtPrenom.getText(),
                    Integer.parseInt(txtAge.getText()),
                    txtSexe.getText(),
                    txtProfession.getText(),
                    txtEtatCivil.getText(),
                    txtAdresse.getText()
                );

                citoyen.enregistrerDansBDD(idFamille);

                JOptionPane.showMessageDialog(this, "Citoyen enregistré avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
