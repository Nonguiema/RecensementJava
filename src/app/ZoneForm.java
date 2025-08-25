package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ZoneForm extends JFrame {

    public ZoneForm() {
        setTitle("Ajouter une Zone");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField txtNom = new JTextField();
        JTextField txtType = new JTextField();
        JTextField txtHabitants = new JTextField();

        JButton btnAjouter = new JButton("Ajouter");

        panel.add(new JLabel("Nom :"));
        panel.add(txtNom);
        panel.add(new JLabel("Type :"));
        panel.add(txtType);
        panel.add(new JLabel("Nombre d'habitants :"));
        panel.add(txtHabitants);

        panel.add(new JLabel(""));
        panel.add(btnAjouter);

        add(panel);

        btnAjouter.addActionListener((ActionEvent e) -> {
            try {
                String nom = txtNom.getText();
                String type = txtType.getText();
                int habitants = Integer.parseInt(txtHabitants.getText());

                ZoneGeographique zone = new ZoneGeographique(nom, type, habitants);
                zone.enregistrerDansBDD();
                JOptionPane.showMessageDialog(this, "✅ Zone ajoutée avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Erreur : " + ex.getMessage());
            }
        });
    }
}
