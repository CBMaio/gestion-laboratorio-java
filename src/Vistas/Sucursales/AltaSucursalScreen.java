package Vistas.Sucursales;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaSucursalScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JPanel form;
    private JLabel numeroLabel;
    private JTextField numeroInput;
    private JLabel direccionLabel;
    private JTextField direccionInput;
    private JButton altaBtn;
    private JLabel nombreLabel;
    private JTextField nombreInput;

    public AltaSucursalScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
    }

    private void asociarEventos () {
        altaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createSucursal(numeroInput.getText(), direccionInput.getText(), nombreInput.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createSucursal(String numero, String direccion, String nombre) throws Exception {
        if (Utils.isAnyFieldEmpty(numero, direccion, nombre)) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            return;
        }

        if (Utils.isNumeric(numero)) {
            int numeroToNumber = Integer.parseInt(numero);
            PacienteSucursalDto sucursalDto = new PacienteSucursalDto(numeroToNumber, direccion, nombre);
            ControllerPacienteSucursal controllerSucursal = ControllerPacienteSucursal.getInstance();
            boolean agregadoCorrectamente = controllerSucursal.addSucursalExitosamente(sucursalDto);
            String message = agregadoCorrectamente ? "Alta exitosa!" : "Sucursal ya existente";
            JOptionPane.showMessageDialog(null, message);
            resetForm();
        }
    }

    private void resetForm () {
        numeroInput.setText(null);
        direccionInput.setText(null);
        nombreInput.setText(null);
        numeroInput.requestFocus();
    }
}
