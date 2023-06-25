package Vistas.Sucursales;

import Vistas.Pacientes.PacientesBajaScreen;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BajaSucursalScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JButton darDeBajaButton;
    private JPanel pnlForm;
    private JTextField numeroInput;
    private BajaSucursalScreen self;

    public BajaSucursalScreen (Window owner, String title) {
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
        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = numeroInput.getText();
                try {
                    eliminarSucursal(id);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void eliminarSucursal (String id) throws Exception {
        if (Utils.isNumeric(id)) {
            Integer validId = Integer.parseInt(id);
            ControllerPacienteSucursal controllerInstance = ControllerPacienteSucursal.getInstance();
            Boolean existeSucursal = controllerInstance.sucursalExistente(validId);
            if (existeSucursal) {
                PacienteSucursalDto sucursalDto = controllerInstance.getSucursalDTO(validId);
                if (!puedeEliminarse(sucursalDto)) {
                    JOptionPane.showMessageDialog(null, "Esta sucursal tiene peticiones con resultados finalizados, por lo tanto no puede ser eliminada.");
                    return;
                }

                controllerInstance.deleteBySucursalId(validId);
                JOptionPane.showMessageDialog(null, "Sucursal dada de baja exitosamente");
                self.setVisible(false);
                return;
            }

            JOptionPane.showMessageDialog(null, "Sucursal no encontrada");
            numeroInput.setText(null);
            numeroInput.requestFocus();
        }
    }

    private Boolean puedeEliminarse (PacienteSucursalDto sucursalDto) {
        try {
            ControllerPacienteSucursal controllerInstance = ControllerPacienteSucursal.getInstance();
            return !controllerInstance.sucursalTienePeticionesFinalizadas(sucursalDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
