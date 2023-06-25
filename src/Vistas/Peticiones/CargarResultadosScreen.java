package Vistas.Peticiones;

import Models.Practica;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CargarResultadosScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JPanel pnlPracticas;
    private JButton aceptarButton;
    private PracticaPeticionDto peticionData;
    private CargarResultadosScreen self;

    public CargarResultadosScreen (Window owner, String title, PracticaPeticionDto peticionData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.peticionData = peticionData;
        this.createForm();
        this.self = this;
    }

    private void asociarEventos (){
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarResultados();
                JOptionPane.showMessageDialog(null, "Los resultados fueron cargados correctamente");
                self.setVisible(false);
            }
        });
    }

    private void createForm () {
        ArrayList<Practica> practicas = peticionData.getPracticas();
        Integer rows = practicas.size();
        pnlPracticas.setLayout(new GridLayout(rows, 2));
        for (Practica practica: practicas) {
            JTextField resultadoInput = new JTextField();
            JLabel practicaLabel = new JLabel();
            practicaLabel.setText(practica.getNombre());
            resultadoInput.setName(practica.getCodigo().toString());
            pnlPracticas.add(practicaLabel);
            pnlPracticas.add(resultadoInput);
        }

        pnlPracticas.validate();
    }

    private void validarResultados () {
        for (Component component: pnlPracticas.getComponents()) {
            if (component instanceof JTextField) {
                String value = ((JTextField) component).getText();
                if (value.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los resultados deben estar completos");
                    return;
                }

                String resultadoIngresado = ((JTextField) component).getText();
                agregarResultadoAPractica(Integer.parseInt(component.getName()), resultadoIngresado);
            }
        }
    }



    private void agregarResultadoAPractica (Integer codigo, String resultado) {
        try {
            ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
            PracticaPeticionDto practica = controller.getPracticaDTO(codigo);
            if (practica.getTieneResultadosNumericos()) {
                if (Utils.isNumeric(resultado)) {
                    controller.setResultadoToPractica(practica, peticionData, Integer.parseInt(resultado));
                }
                return;
            }

            if (!resultado.equals("reactivo") && !resultado.equals("no reactivo")) {
                JOptionPane.showMessageDialog(null, "El resultado cargado es inválido. Código de practica " + codigo.toString());
                return;
            }

            Boolean esReactivo = resultado.equals("reactivo");
            controller.setResultadoToPractica(practica, peticionData, esReactivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
