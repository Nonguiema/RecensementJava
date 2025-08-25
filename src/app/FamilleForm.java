package app;

import javax.swing.*;

public class FamilleForm extends JFrame {
    public FamilleForm() {
        setTitle("Ajouter une famille");
        setContentPane(new FamilleFormMySQL()); // On réutilise ta classe existante
        pack();
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
