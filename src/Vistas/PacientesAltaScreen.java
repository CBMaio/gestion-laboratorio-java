package Vistas;

import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacientesAltaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMainForm;
    private JPanel form;
    private JPanel dni;
    private JTextField dniInput;
    private JPanel nombre;
    private JLabel nombreLabel;
    private JTextField nombreInput;
    private JLabel dniLabel;
    private JPanel apellido;
    private JLabel apellidoLabel;
    private JTextField apellidoInput;
    private JPanel domicilio;
    private JPanel mail;
    private JPanel edad;
    private JLabel edadLabel;
    private JLabel mailLabel;
    private JLabel domicilioLabel;
    private JTextField edadInput;
    private JTextField mailInput;
    private JTextField domicilioInput;
    private JButton altaBtn;

    public PacientesAltaScreen (Window owner, String titulo) {
        super(owner, titulo);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.altaBtnAction();
    }

    private void altaBtnAction () {
        altaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, edadInput.getText() );
            }
        });
    }

    private void createPaciente (String dni, String nombre, String apellido, String edad, String domicilio, String mail) throws Exception {
        if (isNumeric(dni) && isNumeric(edad)) {
            int dniToNumber = Integer.parseInt(dni);
            int edadToNumber = Integer.parseInt(edad);
            PacienteSucursalDto pacienteDto = new PacienteSucursalDto(dniToNumber,nombre,apellido,edadToNumber,domicilio,mail);
            ControllerPacienteSucursal controllerPaciente = ControllerPacienteSucursal.getInstance();
            controllerPaciente.addPaciente(pacienteDto);
        }
    }

    private Boolean isNumeric (String strNumber) {
        try {
            Integer.parseInt(strNumber);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Campo numerico esperado" );
            return false;
        }
    }

}
