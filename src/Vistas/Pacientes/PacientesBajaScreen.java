package Vistas.Pacientes;

import Vistas.utils.Utils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import controllers.ControllerPacienteSucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientesBajaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton bajaBtn;
    private JTextField dniInput;
    private PacientesBajaScreen self;

    public PacientesBajaScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.listener();
        this.self = this;
    }

    private void listener () {
        bajaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniInput.getText();
                try {
                    eliminarPaciente(dni);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void eliminarPaciente (String dni) throws Exception {
        if (Utils.isNumeric(dni)) {
            Integer validDni = Integer.parseInt(dni);
            ControllerPacienteSucursal controllerInstance = ControllerPacienteSucursal.getInstance();
            Boolean existePaciente = controllerInstance.pacienteExistente(validDni);
            if (existePaciente) {
                controllerInstance.deleteByPacienteId(validDni);
                JOptionPane.showMessageDialog(null, "Paciente dado de baja exitosamente");
                self.setVisible(false);
                return;
            }

            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
            dniInput.setText(null);
            dniInput.requestFocus();
        }
    }
}
