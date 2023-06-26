package Vistas.Sucursales;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DerivarPeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton aceptarButton;
    private JPanel pnlForm;
    private JTextField numeroDestinoInput;

    private DerivarPeticionesScreen self;
    private PacienteSucursalDto sucursalActualData;

    public DerivarPeticionesScreen (Window owner, String title, PacienteSucursalDto sucursalActualData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.sucursalActualData = sucursalActualData;
        events();
    }

    private void events () {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sucursalValida()) {
                    return;
                }
                JOptionPane.showMessageDialog(null, "Peticiones derivadas! Su sucursal ya no tiene peticiones asociadas. Vuelva a hacer el proceso para eliminar la sucursal definitivamente ");
                self.setVisible(false);
            }
        });
    }

    private Boolean sucursalValida () {
        if (!Utils.isNumeric(numeroDestinoInput.getText())) {
            return false;
        }

        Integer sucursalActualID =  sucursalActualData.getNumeroSucursal();
        Integer sucursalDestinoID = Integer.parseInt(numeroDestinoInput.getText());

        if (sucursalActualID.equals(sucursalDestinoID)) {
            return false;
        }

        try {
            ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
            if (!controller.sucursalExistente(sucursalDestinoID)) {
                JOptionPane.showMessageDialog(null, "La sucursal de destino no existe");
                return false;
            }
            PacienteSucursalDto sucursalDestinoDTO = controller.getSucursalDTO(sucursalDestinoID);
            controller.derivarPeticionesActivas(sucursalActualData, sucursalDestinoDTO);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
