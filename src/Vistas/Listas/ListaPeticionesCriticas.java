package Vistas.Listas;

import Vistas.utils.ListaModel;

import javax.swing.*;
import java.awt.*;

public class ListaPeticionesCriticas extends JDialog {
    private JPanel pnlPrincipal;
    private JList peticionesCriticas;
    private ListaModel model;

    public ListaPeticionesCriticas (Window owner, String title, ListaModel model) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.model = model;
        peticionesCriticas.setModel(model);
    }

}
