package Vistas.Peticiones;

import Vistas.utils.Utils;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
    private JTextField numeroInput;
    private PacienteSucursalDto pacienteData;
    private ArrayList<Integer> practicasIds = new ArrayList<Integer>();

    private AltaPeticionesScreen self;
    public AltaPeticionesScreen (Window owner, String title, PacienteSucursalDto pacienteData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400, 400 );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.self = this;
        this.pacienteData = pacienteData;
        this.dniLabel.setText(pacienteData.getIdPaciente().toString());
        this.nombreLabel.setText(pacienteData.getNombrePaciente());
    }

    private void asociarEventos () {
        crearPeticiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validatePracticesFromPanel() || Utils.isAnyFieldEmpty(numeroInput.getText(), obraSocialInput.getText(), fechaCargaInput.getText(), fechaEntregaInput.getText())) {
                    return;
                }

                if (!Utils.isNumeric(numeroInput.getText())) {
                    return;
                }
                try {
                    ControllerPracticasPeticiones controllerPeticion = ControllerPracticasPeticiones.getInstance();
                    Integer peticionId = Integer.parseInt(numeroInput.getText());
                    if (controllerPeticion.peticionExistente(peticionId)) {
                        JOptionPane.showMessageDialog(null, "El número de petición ya existe");
                        return;
                    }

                    PracticaPeticionDto peticionDto = new PracticaPeticionDto(peticionId, pacienteData, fechaCargaInput.getText(), fechaEntregaInput.getText());
                    boolean peticionAgregadaExitosamente = controllerPeticion.addPeticionExitosamente(peticionDto);

                    if (!peticionAgregadaExitosamente) {
                        JOptionPane.showMessageDialog(null, "No se pudo cargar la petición");
                        return;
                    }
                    for (Integer id: practicasIds) {
                        controllerPeticion.addPracticaToPeticion(peticionDto, id);
                    }

                    boolean peticionToPacienteExitosamente = controllerPeticion.addPeticionToPacienteExitosamente(peticionDto, pacienteData);
                    JOptionPane.showMessageDialog(null, "Petición creada");
                    self.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nuevaPractica = new JTextField();
                pnlPracticas.setLayout(new BoxLayout(pnlPracticas, BoxLayout.Y_AXIS));
                pnlPracticas.add(nuevaPractica);
                pnlPracticas.validate();
            }
        });

    }

    private boolean validatePracticesFromPanel () {
        for (Component component: pnlPracticas.getComponents()) {
            if (component instanceof JTextField) {
                String value = ((JTextField) component).getText();
                if (validPractica(value)) {
                    Integer valueInt = Integer.parseInt(value);
                    practicasIds.add(valueInt);
                } else {
                    JOptionPane.showMessageDialog(null, "Código " + value + " inválido");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validPractica (String value) {
        if (Utils.isNumeric(value)) {
            Integer id = Integer.parseInt(value);
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
