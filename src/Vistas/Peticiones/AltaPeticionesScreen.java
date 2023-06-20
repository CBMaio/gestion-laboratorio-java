package Vistas.Peticiones;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltaPeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton crearPeticiónButton;
    private JPanel pnlForm;
    private JTextField dniInput;
    private JTextField obraSocialInput;
    private JTextField fechaCargaInput;
    private JTextField practicasInput;
    private JTextField fechaEntregaInput;
    private JLabel dniPaciente;
    private JLabel nombreLabel;
    private JLabel dniLabel;
    private JButton agregarButton;
    private JPanel pnlPracticas;
    private JTextField practicaInput;
    private PacienteSucursalDto pacienteData;

    private ArrayList<JTextField> practicas;
    public AltaPeticionesScreen (Window owner, String title, PacienteSucursalDto pacienteData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400, 400 );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.pacienteData = pacienteData;
        this.dniLabel.setText(pacienteData.getIdPaciente().toString());
        this.nombreLabel.setText(pacienteData.getNombrePaciente());
    }

    private void asociarEventos () {
        crearPeticiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkPractica()) {
                    PracticaPeticionDto peticionDto = new PracticaPeticionDto(1, pacienteData);
                    try {
                        ControllerPracticasPeticiones controllerPeticion = ControllerPracticasPeticiones.getInstance();
                        boolean agregadoCorrectamente = controllerPeticion.addPeticionExitosamente(peticionDto);

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nuevaPractica = new JTextField();
                practicas.add(nuevaPractica);
                pnlPracticas.setLayout(new BoxLayout(pnlPracticas, BoxLayout.Y_AXIS));
                pnlPracticas.add(nuevaPractica);
                pnlPracticas.add(nuevaPractica);
                pnlPracticas.validate();
            }
        });

    }

    private boolean checkPractica () {
        String practicaID = practicaInput.getText();
        if (Utils.isNumeric(practicaID)) {
            Integer id = Integer.parseInt(practicaID);
            try {
                ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
                if (controller.practicaExistente(id)) {
                    return true;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return false;
    }
}
