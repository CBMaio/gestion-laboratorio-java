package Vistas.Pacientes;

import Models.Paciente;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarPaciente extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlMenu;
    private JButton buscar;
    private JPanel pnlTitle;
    private JLabel titleLabel;
    private JLabel dniLabel;
    private JTextField dniInput;
    private PacienteSucursalDto pacienteData;
    private BuscarPaciente self;

    public BuscarPaciente (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.self = this;
        events();
    }

    private void events () {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarPaciente(dniInput.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void buscarPaciente (String dni) throws Exception {
        if (isNumeric(dni)) {
            ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
            if (controller.pacienteExistente(Integer.parseInt(dni))) {
                PacienteSucursalDto pacienteDTO = controller.getPacienteDTO(Integer.parseInt(dni));
                this.pacienteData = pacienteDTO;
                PacientesModificacionScreen form = new PacientesModificacionScreen(self, "Pacientes", pacienteData);
                form.setVisible(true);
            } else  {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado");
            }
        }

        dniInput.setText(null);
        dniInput.requestFocus();
    }

    private Boolean isNumeric (String strNumber) {
        try {
            Integer.parseInt(strNumber);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Campo numérico esperado" );
            return false;
        }
    }

}
