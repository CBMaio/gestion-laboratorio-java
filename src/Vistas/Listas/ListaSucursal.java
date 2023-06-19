package Vistas.Listas;

import Vistas.utils.ListaModel;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;

public class ListaSucursal extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JList listaSucursales;
    private ListaModel model;

    public ListaSucursal (Window owner, String title, ListaModel model) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.model = model;
        listaSucursales.setModel(model);
    }

}
