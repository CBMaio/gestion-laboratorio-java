package Vistas.Listas;

import Vistas.utils.ListaModel;

import javax.swing.*;
import java.awt.*;

public class ListaPacientes extends JDialog{
    private JPanel pnlPrincipal;
    private JList listaPacientes;
    private JPanel pnlTitle;

    private ListaModel model;

    public ListaPacientes (Window owner, String title, ListaModel model) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.model = model;
        listaPacientes.setModel(model);
    }
}
