package Vistas.Peticiones;

import Models.Peticiones;
import Vistas.Listas.ListaPeticiones;
import Vistas.utils.ListaModel;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JTextField dniInput;
    private JButton continuarButton;
    private JButton altaButton;
    private JButton bajaButton;
    private JButton modificaciónButton;
    private JButton listarPeticionesButton;
    private JButton cargarResultadosButton;
    private PeticionesScreen self;
    private PacienteSucursalDto pacienteData;
    private ListaModel model = new ListaModel();

    public PeticionesScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos();
    }

    private void asociarEventos () {
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPaciente altaForm = new BuscarPaciente(self, "Peticiones", "alta");
                altaForm.setVisible(true);
            }
        });
        listarPeticionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model = getPeticionesParaMostrar();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ListaPeticiones lista = new ListaPeticiones(self, "Peticiones", model);
                lista.setVisible(true);
            }
        });
        cargarResultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPeticion form = new BuscarPeticion(self, "Peticiones");
                form.setVisible(true);
            }
        });
    }

    private ListaModel getPeticionesParaMostrar () throws Exception {
        ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
        ArrayList<Peticiones> peticiones = controller.mostrarPeticiones();
        ListaModel lista = new ListaModel();
        for (Peticiones item : peticiones) {
            String numero = item.getNumeroPeticion().toString();
            String fecha = item.getFechaCarga();
            String nombrePaciente = item.getPaciente().getNombreCompleto();
            lista.add(numero + " - " + nombrePaciente + " - " + fecha);
        }

        return lista;
    }

}
