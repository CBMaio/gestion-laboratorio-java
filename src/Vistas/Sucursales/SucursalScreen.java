package Vistas.Sucursales;

import Models.Paciente;
import Models.Sucursal;
import Vistas.Listas.ListaPacientes;
import Vistas.Listas.ListaSucursal;
import Vistas.utils.ListaModel;
import controllers.ControllerPacienteSucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SucursalScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel main;
    private JButton altaButton;
    private JButton bajaButton;
    private JButton modificaciónButton;
    private JButton listarSucursalesButton;
    private SucursalScreen self;
    private ListaModel model = new ListaModel();

    public SucursalScreen (Window owner, String title) {
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
                AltaSucursalScreen altaSucursal = new AltaSucursalScreen(self, "Sucursales");
                altaSucursal.setVisible(true);
            }
        });

        listarSucursalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model = getSucursalesParaMostrar();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ListaSucursal lista = new ListaSucursal(self, "Sucursales", model);
                lista.setVisible(true);
            }
        });
    }

    private ListaModel getSucursalesParaMostrar () throws Exception {
        ArrayList<Sucursal> sucursales = ControllerPacienteSucursal.getInstance().mostrarSucursal();
        ListaModel lista = new ListaModel();
        for (Sucursal item : sucursales) {
            lista.add(item.getNombre());
        }

        return lista;
    }
}
