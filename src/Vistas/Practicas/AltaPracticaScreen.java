package Vistas.Practicas;

import Vistas.utils.Utils;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import controllers.ControllerPracticasPeticiones;
import dto.PracticaPeticionDto;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AltaPracticaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JPanel pnlForm;
    private JButton crearPrácticaButton;
    private JTextField codigoInput;
    private JTextField nombreInput;
    private JTextField grupoInput;
    private JTextField tiempoDemoraInput;
    private JTextField tipoDePracticaInput;
    private JTextField valorMinimoInput;
    private JTextField valorMaximoInput;
    private JTextField resultadosNumericosInput;

    public AltaPracticaScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450,500);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
    }

    private void asociarEventos () {
        crearPrácticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createPractica(codigoInput.getText(), nombreInput.getText(), grupoInput.getText(), tiempoDemoraInput.getText(), tipoDePracticaInput.getText(), valorMinimoInput.getText(), valorMaximoInput.getText(), resultadosNumericosInput.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createPractica (String codigo, String nombre, String grupo, String demora, String tipoDePractica, String valorMinimo, String valorMaximo, String tieneResultadosNumericos) throws Exception {
        if (Utils.isAnyFieldEmpty(codigo, nombre, grupo, demora, tipoDePractica, valorMinimo, valorMaximo, tieneResultadosNumericos)) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
            return;
        }

        Boolean validacion = Utils.isNumeric(codigo) && Utils.isFloatNumber(demora) && Utils.isNumeric(grupo) && Utils.isFloatNumber(valorMinimo) && Utils.isFloatNumber(valorMaximo);
        if (validacion) {
            int codigoToNumber = Integer.parseInt(codigo);
            float demoraToNumber = Float.parseFloat(demora);
            int grupoToNumber = Integer.parseInt(grupo);
            float valorMinToNumber = Float.parseFloat(valorMinimo);
            float valorMaxToNumber = Float.parseFloat(valorMaximo);
            Boolean tipoDePracticaValida = tipoDePractica.equals("critico") || tipoDePractica.equals("reservado");
            Boolean resultadosNumericosFieldValido = tieneResultadosNumericos.equals("s") || tieneResultadosNumericos.equals("n");
            if (!tipoDePracticaValida || !resultadosNumericosFieldValido) {
                JOptionPane.showMessageDialog(null, "Revise los campos ingresados. 'Tipo de práctica' o 'Tiene resultados ingresados?' son incorrectos");
                return;
            }

            Boolean resultadosNumericosBoolean = false;
            if (tieneResultadosNumericos.equals("s")) {
                resultadosNumericosBoolean = true;
            }

            PracticaPeticionDto practicaDto = new PracticaPeticionDto(codigoToNumber, nombre, grupoToNumber, demoraToNumber, tipoDePractica, valorMinToNumber, valorMaxToNumber, resultadosNumericosBoolean);
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
        tipoDePracticaInput.setText(null);
        valorMinimoInput.setText(null);
        valorMaximoInput.setText(null);
        resultadosNumericosInput.setText(null);
        codigoInput.requestFocus();
    }
}
