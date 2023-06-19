package Vistas.Peticiones;

import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;

public class AltaPeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton crearPeticiónButton;
    private JPanel pnlForm;
    private JTextField dniInput;
    private JTextField obraSocialInput;
    private JTextField fechaCargaInput;
    private JTextField practicasInput;
    private JTextField fechaEntregaInput;

    public AltaPeticionesScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
