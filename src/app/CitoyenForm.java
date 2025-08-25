package app;

import javax.swing.*;

public class CitoyenForm extends JFrame {
    public CitoyenForm() {
        setTitle("Ajouter un citoyen");
        setContentPane(new CitoyenFormPanel()); // On réutilise ton panel existant
        pack();
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
