package Vistas.Peticiones;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarPaciente extends JDialog{
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton continuarButton;
    private JPanel pnlform;
    private JTextField dniInput;
    private BuscarPaciente self;
    private PacienteSucursalDto pacienteData;

    public BuscarPaciente (Window owner, String title, String action) {
        super(owner, title);
        if (action.isEmpty()) {
            action = "";
        }

        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos(action);
    }
    private void asociarEventos (String action) {
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniInput.getText();
                if (Utils.isNumeric(dni)) {
                    try {
                        buscarPaciente(Integer.parseInt(dni));
                        openScreen(action);
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
            return;
        } else  {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
        dniInput.setText(null);
        dniInput.requestFocus();
    }

    private void openScreen (String action) {
        switch (action) {
            case "alta":
                openAltaScreen();
                break;
        }
    }

    private void openAltaScreen () {
        AltaPeticionesScreen form = new AltaPeticionesScreen(self, "Pacientes", pacienteData);
        form.setVisible(true);
    }

}
