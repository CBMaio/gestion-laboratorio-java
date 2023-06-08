package Vistas.Listas;

import javax.swing.*;
import java.awt.*;

public class ListaPacientes extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JLabel lista;
    private JPanel pnlLista;
    private JList listElement;

    public ListaPacientes (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setSize(300,450);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
