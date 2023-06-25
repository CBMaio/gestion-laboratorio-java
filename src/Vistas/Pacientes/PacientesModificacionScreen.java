package Vistas.Pacientes;

import Models.Paciente;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientesModificacionScreen extends JDialog{
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMain;
    private JPanel form;
    private JLabel dniLabel;
    private JLabel nombreLabel;
    private JTextField nombreInput;
    private JLabel apellidoLabel;
    private JTextField apellidoInput;
    private JLabel edadLabel;
    private JTextField edadInput;
    private JLabel mailLabel;
    private JTextField mailInput;
    private JLabel domicilioLabel;
    private JTextField domicilioInput;
    private JButton modificarButton;
    private JTextField dniInput;
    private PacientesModificacionScreen self;

    PacienteSucursalDto initialData;

    public PacientesModificacionScreen (Window owner, String title, PacienteSucursalDto pacienteData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.initialData = pacienteData;
        this.fillForm(initialData);
        this.modificarListener();
        this.self = this;
    }

    private void fillForm (PacienteSucursalDto data) {
        dniInput.setText(data.getIdPaciente().toString());
        nombreInput.setText(data.getNombrePaciente());
        apellidoInput.setText(data.getApellido());
        edadInput.setText(data.getEdad().toString());
        mailInput.setText(data.getMail());
        domicilioInput.setText(data.getDomicilio());
    }

    private void modificarListener () {
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utils.isNumeric(dniInput.getText()) && Utils.isNumeric(edadInput.getText())) {
                    PacienteSucursalDto paciente = new PacienteSucursalDto(
                            Integer.parseInt(dniInput.getText()),
                            nombreInput.getText(),
                            apellidoInput.getText(),
                            Integer.parseInt(edadInput.getText()),
                            domicilioInput.getText(),
                            mailInput.getText()
                    );
                    try {
                        ControllerPacienteSucursal.getInstance().modificarPaciente(paciente);
                        JOptionPane.showMessageDialog(null, "Paciente modificado");
                        self.setVisible(false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
