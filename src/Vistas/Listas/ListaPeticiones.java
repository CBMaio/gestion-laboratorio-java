package Vistas.Listas;

import Vistas.utils.ListaModel;

import javax.swing.*;
import java.awt.*;

public class ListaPeticiones extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JList listaPeticiones;
    private ListaModel model;

    public ListaPeticiones (Window owner, String title, ListaModel model) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.model = model;
        listaPeticiones.setModel(model);
    }
}
