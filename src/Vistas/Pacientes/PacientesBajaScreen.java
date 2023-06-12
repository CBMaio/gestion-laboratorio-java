package Vistas.Pacientes;

import javax.swing.*;
import java.awt.*;

public class PacientesBajaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton bajaBtn;
    private JTextField textField1;

    public PacientesBajaScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
    }

}
