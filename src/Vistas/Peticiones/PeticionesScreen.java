package Vistas.Peticiones;

import Vistas.Pacientes.PacientesModificacionScreen;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JTextField dniInput;
    private JButton continuarButton;
    private JButton altaButton;
    private PeticionesScreen self;

    private PacienteSucursalDto pacienteData;

    public PeticionesScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos();
    }

    private void asociarEventos () {
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniInput.getText();
                if (Utils.isNumeric(dni)) {
                    try {
                        buscarPaciente(Integer.parseInt(dni));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void buscarPaciente (Integer dni) throws Exception {
            ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
            if (controller.pacienteExistente(dni)) {
                PacienteSucursalDto pacienteDTO = controller.getPacienteDTO(dni);
                this.pacienteData = pacienteDTO;
                AltaPeticionesScreen form = new AltaPeticionesScreen(self, "Pacientes", pacienteData);
                form.setVisible(true);
            } else  {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado");
            }
            dniInput.setText(null);
            dniInput.requestFocus();
    }

}
