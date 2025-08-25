package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FamilleFormMySQL extends JFrame {

    private JTextField nomFamilleField, nomPereField, nomMereField, zoneIdField;
    private JTextField nomMembreField, prenomField, ageField, sexeField, professionField, etatCivilField, adresseField;
    private JTextArea membresArea;

    private List<Citoyen> membres = new ArrayList<>();

    public FamilleFormMySQL() {
        setTitle("Ajouter une Famille (MySQL)");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Partie haute : infos famille
        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Nom de la famille :"));
        nomFamilleField = new JTextField();
        topPanel.add(nomFamilleField);

        topPanel.add(new JLabel("Nom du père :"));
        nomPereField = new JTextField();
        topPanel.add(nomPereField);

        topPanel.add(new JLabel("Nom de la mère :"));
        nomMereField = new JTextField();
        topPanel.add(nomMereField);

        topPanel.add(new JLabel("ID de la zone :"));
        zoneIdField = new JTextField();
        topPanel.add(zoneIdField);

        add(topPanel, BorderLayout.NORTH);

        // Formulaire d’un citoyen
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        nomMembreField = new JTextField();
        prenomField = new JTextField();
        ageField = new JTextField();
        sexeField = new JTextField();
        professionField = new JTextField();
        etatCivilField = new JTextField();
        adresseField = new JTextField();

        formPanel.add(new JLabel("Nom membre :")); formPanel.add(nomMembreField);
        formPanel.add(new JLabel("Prénom :")); formPanel.add(prenomField);
        formPanel.add(new JLabel("Âge :")); formPanel.add(ageField);
        formPanel.add(new JLabel("Sexe :")); formPanel.add(sexeField);
        formPanel.add(new JLabel("Profession :")); formPanel.add(professionField);
        formPanel.add(new JLabel("État civil :")); formPanel.add(etatCivilField);
        formPanel.add(new JLabel("Adresse :")); formPanel.add(adresseField);

        JButton ajouterMembreBtn = new JButton("Ajouter membre");
        formPanel.add(ajouterMembreBtn);
        add(formPanel, BorderLayout.CENTER);

        // Zone d’affichage + bouton enregistrer
        JPanel bottomPanel = new JPanel(new BorderLayout());
        membresArea = new JTextArea(8, 40);
        membresArea.setEditable(false);
        bottomPanel.add(new JScrollPane(membresArea), BorderLayout.CENTER);

        JButton enregistrerFamilleBtn = new JButton("Enregistrer Famille");
        bottomPanel.add(enregistrerFamilleBtn, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        ajouterMembreBtn.addActionListener(e -> ajouterMembre());
        enregistrerFamilleBtn.addActionListener(e -> enregistrerFamille());

        setVisible(true);
    }

    private void ajouterMembre() {
        try {
            String nom = nomMembreField.getText();
            String prenom = prenomField.getText();
            int age = Integer.parseInt(ageField.getText());
            String sexe = sexeField.getText();
            String profession = professionField.getText();
            String etatCivil = etatCivilField.getText();
            String adresse = adresseField.getText();

            Citoyen citoyen = new Citoyen(nom, prenom, age, sexe, profession, etatCivil, adresse);
            membres.add(citoyen);
            membresArea.append("- " + citoyen.toString() + "\n");

            // Clear fields
            nomMembreField.setText("");
            prenomField.setText("");
            ageField.setText("");
            sexeField.setText("");
            professionField.setText("");
            etatCivilField.setText("");
            adresseField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'âge doit être un nombre !");
        }
    }

    private void enregistrerFamille() {
        try {
            String nomFamille = nomFamilleField.getText();
            String pere = nomPereField.getText();
            String mere = nomMereField.getText();
            int zoneId = Integer.parseInt(zoneIdField.getText());

            Famille famille = new Famille(nomFamille, pere, mere, zoneId);
            for (Citoyen c : membres) {
                famille.ajouterMembre(c);
            }

            famille.enregistrerDansBDD();
            JOptionPane.showMessageDialog(this, "✅ Famille enregistrée avec succès !");
            resetForm();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L’ID de zone doit être un nombre !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    private void resetForm() {
        nomFamilleField.setText("");
        nomPereField.setText("");
        nomMereField.setText("");
        zoneIdField.setText("");
        membres.clear();
        membresArea.setText("");
    }

    public static void main(String[] args) {
        new FamilleFormMySQL();
    }
}
