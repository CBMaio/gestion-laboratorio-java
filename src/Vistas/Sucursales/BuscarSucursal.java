package Vistas.Sucursales;

import Vistas.Pacientes.PacientesModificacionScreen;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarSucursal extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JButton buscarButton;
    private JPanel pnlMenu;
    private JTextField numeroInput;
    private PacienteSucursalDto sucursalData;
    private BuscarSucursal self;

    public BuscarSucursal (Window owner, String title, String action) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450,200);
        this.setLocationRelativeTo(null);
        this.self = this;
        events(action);
    }

    private void events (String action) {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!buscarSucursalExitosamente(numeroInput.getText())) {
                        return;
                    }
                    openScreen(action);
                    numeroInput.setText(null);
                    numeroInput.requestFocus();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private Boolean buscarSucursalExitosamente (String id) throws Exception {
        if (Utils.isNumeric(id)) {
            ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
            if (controller.sucursalExistente(Integer.parseInt(id))) {
                PacienteSucursalDto sucursalDto = controller.getSucursalDTO(Integer.parseInt(id));
                this.sucursalData = sucursalDto;
                return true;
            } else  {
                JOptionPane.showMessageDialog(null, "Sucursal no encontrada");
                return false;
            }
        }

        return false;
    }

    private void openScreen (String action) {
        switch (action) {
            case "modificacion":
                openModificarSucursalScreen();
                break;
            case "peticiones":
                openPeticionesDeSucursalScreen();
                break;
        }
    }

    private void openModificarSucursalScreen () {
        ModificaciónSucursalesScreen form = new ModificaciónSucursalesScreen(self, "Sucursales", sucursalData);
        form.setVisible(true);
    }

    private void openPeticionesDeSucursalScreen () {
        PeticionesDeSucursalScreen form = new PeticionesDeSucursalScreen(self, "Sucursales", sucursalData);
        form.setVisible(true);
    }


}

