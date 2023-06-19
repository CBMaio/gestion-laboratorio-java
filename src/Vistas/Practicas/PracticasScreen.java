package Vistas.Practicas;

import Models.Practica;
import Models.Sucursal;
import Vistas.Listas.ListaPracticas;
import Vistas.Listas.ListaSucursal;
import Vistas.utils.ListaModel;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PracticasScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton altaButton;
    private JButton bajaButton;
    private JButton modificacionButton;
    private JButton listarPrácticasButton;
    private PracticasScreen self;
    private ListaModel model = new ListaModel();

    public PracticasScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos();
    }

    private void asociarEventos () {
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaPracticaScreen altaScreen = new AltaPracticaScreen(self, "Prácticas");
                altaScreen.setVisible(true);
            }
        });

        listarPrácticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model = getPracticasParaMostrar();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ListaPracticas lista = new ListaPracticas(self, "Prácticas", model);
                lista.setVisible(true);
            }
        });

        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BajaPracticaScreen bajaScreen = new BajaPracticaScreen(null, "Prácticas");
                bajaScreen.setVisible(true);
            }
        });

        modificacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPractica buscarPractica = new BuscarPractica(self, "Prácticas");
                buscarPractica.setVisible(true);
            }
        });
    }

    private ListaModel getPracticasParaMostrar () throws Exception {
        ArrayList<Practica> practicas = ControllerPracticasPeticiones.getInstance().mostrarPracitcas();
        ListaModel lista = new ListaModel();
        for (Practica item : practicas) {
            lista.add(item.getNombre());
        }

        return lista;
    }

}
