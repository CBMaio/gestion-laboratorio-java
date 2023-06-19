package Vistas.Practicas;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaPracticaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlForm;
    private JButton crearPrácticaButton;
    private JPanel codigo;
    private JTextField codigoInput;
    private JPanel nombre;
    private JTextField nombreInput;
    private JTextField grupoInput;
    private JTextField tiempoDemoraInput;

    public AltaPracticaScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
    }

    private void asociarEventos () {
        crearPrácticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createPractica(codigoInput.getText(), nombreInput.getText(), grupoInput.getText(), tiempoDemoraInput.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createPractica (String codigo, String nombre, String grupo, String demora) throws Exception {
        if (Utils.isAnyFieldEmpty(codigo, nombre, grupo, demora)) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            return;
        }

        if (Utils.isNumeric(codigo) && Utils.isNumeric(demora) && Utils.isNumeric(grupo)) {
            int codigoToNumber = Integer.parseInt(codigo);
            float demoraToNumber = Float.parseFloat(demora);
            int grupoToNumber = Integer.parseInt(grupo);
            PracticaPeticionDto practicaDto = new PracticaPeticionDto(codigoToNumber, nombre, grupoToNumber, demoraToNumber);
            ControllerPracticasPeticiones controllerPractica = ControllerPracticasPeticiones.getInstance();
            boolean agregadoCorrectamente = controllerPractica.addPracticaExitosamente(practicaDto);
            String message = agregadoCorrectamente ? "Alta exitosa!" : "Práctica ya existente";
            JOptionPane.showMessageDialog(null, message);
            resetForm();
        }
    }

    private void resetForm () {
        codigoInput.setText(null);
        nombreInput.setText(null);
        grupoInput.setText(null);
        tiempoDemoraInput.setText(null);
        codigoInput.requestFocus();
    }
}
