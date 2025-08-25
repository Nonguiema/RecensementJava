package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AjoutCitoyenForm extends JFrame {

    private JTextField nomField, prenomField, ageField, sexeField, professionField, etatCivilField, adresseField, familleIdField;

    public AjoutCitoyenForm() {
        setTitle("Ajout d'un citoyen");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Champs
        nomField = new JTextField();
        prenomField = new JTextField();
        ageField = new JTextField();
        sexeField = new JTextField();
        professionField = new JTextField();
        etatCivilField = new JTextField();
        adresseField = new JTextField();
        familleIdField = new JTextField();

        add(new JLabel("Nom :")); add(nomField);
        add(new JLabel("Prénom :")); add(prenomField);
        add(new JLabel("Âge :")); add(ageField);
        add(new JLabel("Sexe :")); add(sexeField);
        add(new JLabel("Profession :")); add(professionField);
        add(new JLabel("État civil :")); add(etatCivilField);
        add(new JLabel("Adresse :")); add(adresseField);
        add(new JLabel("ID de la famille :")); add(familleIdField);

        JButton enregistrerBtn = new JButton("Enregistrer dans la base");
        add(enregistrerBtn);
        JLabel messageLabel = new JLabel("");
        add(messageLabel);

        enregistrerBtn.addActionListener(e -> {
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                String sexe = sexeField.getText();
                String profession = professionField.getText();
                String etatCivil = etatCivilField.getText();
                String adresse = adresseField.getText();
                int idFamille = Integer.parseInt(familleIdField.getText());

                Citoyen citoyen = new Citoyen(nom, prenom, age, sexe, profession, etatCivil, adresse);
                citoyen.enregistrerDansBDD(idFamille);

                messageLabel.setText("✅ Citoyen enregistré !");
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : âge et ID famille doivent être des nombres.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        ageField.setText("");
        sexeField.setText("");
        professionField.setText("");
        etatCivilField.setText("");
        adresseField.setText("");
        familleIdField.setText("");
    }

    public static void main(String[] args) {
        new AjoutCitoyenForm();
    }
}
