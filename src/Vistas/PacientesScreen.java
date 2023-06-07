package Vistas;

import javax.swing.*;
import java.awt.*;

public class PacientesScreen extends JDialog{
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;

    public PacientesScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
