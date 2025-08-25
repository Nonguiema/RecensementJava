package app;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("📊 Application de Recensement");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnZone = new JButton("➕ Ajouter une Zone");
        JButton btnFamille = new JButton("👨‍👩‍👧 Ajouter une Famille");
        JButton btnCitoyen = new JButton("🧑 Ajouter un Citoyen");
        JButton btnRecensement = new JButton("📊 Voir / Calculer Recensement");

        // Actions
        btnZone.addActionListener(e -> new ZoneForm().setVisible(true));
        btnFamille.addActionListener(e -> new FamilleForm().setVisible(true));
        btnCitoyen.addActionListener(e -> new CitoyenForm().setVisible(true));
        btnRecensement.addActionListener(e -> new RecensementForm().setVisible(true));

        panel.add(btnZone);
        panel.add(btnFamille);
        panel.add(btnCitoyen);
        panel.add(btnRecensement);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}
