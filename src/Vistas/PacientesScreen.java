package Vistas;

import Vistas.Listas.ListaPacientes;
import Vistas.utils.ListaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PacientesScreen extends JDialog{
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton alta;
    private JButton baja;
    private JButton modificacion;
    private JButton lista;
    private PacientesScreen self;

    private ListaModel model = new ListaModel();

    public PacientesScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.self = this;

    }

    private void asociarEventos () {
        alta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacientesAltaScreen altaDePaciente = new PacientesAltaScreen(self, "Dar de alta un paciente", model);
                altaDePaciente.setVisible(true);
            }
        });
        lista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaPacientes lista = new ListaPacientes(self, "Pacientes", model);
                lista.setVisible(true);
            }
        });

    }
}
