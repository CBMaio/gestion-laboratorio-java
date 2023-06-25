package Vistas.Sucursales;

import Vistas.Practicas.ModificaciónPracticaScreen;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaciónSucursalesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton modificarButton;
    private JPanel pnlForm;
    private JTextField direccionInput;
    private JTextField nombreInput;
    private JLabel numeroLabel;
    private PacienteSucursalDto sucursalData;
    private ModificaciónSucursalesScreen self;

    public ModificaciónSucursalesScreen (Window owner, String title, PacienteSucursalDto sucursalData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,350);
        this.setLocationRelativeTo(null);
        this.sucursalData = sucursalData;
        this.fillForm();
        this.modificarListener();
        this.self = this;
    }

    private void modificarListener () {
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacienteSucursalDto sucursal = new PacienteSucursalDto(
                        sucursalData.getNumeroSucursal(),
                        direccionInput.getText(),
                        nombreInput.getText()
                );
                try {
                    ControllerPacienteSucursal.getInstance().modificarSucursal(sucursal);
                    JOptionPane.showMessageDialog(null, "Sucursal modificada");
                    self.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void fillForm () {
        PacienteSucursalDto data = this.sucursalData;
        numeroLabel.setText(data.getNumeroSucursal().toString());
        nombreInput.setText(data.getNombreSucursal());
        direccionInput.setText(data.getDireccionSucursal());
    }
}
