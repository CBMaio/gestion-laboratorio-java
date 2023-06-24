package Vistas.Practicas;

import Vistas.utils.Utils;
import controllers.ControllerPracticasPeticiones;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaciónPracticaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton modificarButton;
    private JPanel pnlForm;
    private JLabel Codigo;
    private JTextField codigoInput;
    private JLabel nombre;
    private JTextField nombreInput;
    private JTextField grupoInput;
    private JTextField horasDeDemoraInput;
    private JTextField tipoDePracticaInput;
    private JTextField valorMinimoInput;
    private JTextField valorMaximoInput;
    private JTextField resultadosNumericosInput;

    private ModificaciónPracticaScreen self;

    PracticaPeticionDto initialData;

    public ModificaciónPracticaScreen (Window owner, String title, PracticaPeticionDto practicaData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,560);
        this.setLocationRelativeTo(null);
        this.initialData = practicaData;
        this.fillForm(initialData);
        this.modificarListener();
        this.self = this;
    }

    private void fillForm (PracticaPeticionDto data) {
        codigoInput.setText(data.getCodigoPractica().toString());
        nombreInput.setText(data.getNombrePractica());
        grupoInput.setText(data.getGrupoPractica().toString());
        horasDeDemoraInput.setText(data.getHorasDeDemora().toString());
        tipoDePracticaInput.setText(data.getTipoDePracica().toString());
        valorMinimoInput.setText(data.getValorReferenciaMinimo().toString());
        valorMaximoInput.setText(data.getValorReferenciaMaximo().toString());
        if (data.getTieneResultadosNumericos()) {
            resultadosNumericosInput.setText("s");
        } else {
            resultadosNumericosInput.setText("n");
        }
    }

    private void modificarListener () {
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean validacion = Utils.isNumeric(codigoInput.getText()) && Utils.isFloatNumber(horasDeDemoraInput.getText()) && Utils.isNumeric(grupoInput.getText()) && Utils.isFloatNumber(valorMinimoInput.getText()) && Utils.isFloatNumber(valorMaximoInput.getText());
                Boolean valorValido = resultadosNumericosInput.getText().equals("s") || resultadosNumericosInput.getText().equals("n");
                if (validacion && valorValido) {
                    PracticaPeticionDto practica = new PracticaPeticionDto(
                            Integer.parseInt(codigoInput.getText()),
                            nombreInput.getText(),
                            Integer.parseInt(grupoInput.getText()),
                            Float.parseFloat(horasDeDemoraInput.getText()),
                            tipoDePracticaInput.getText(),
                            Float.parseFloat(valorMinimoInput.getText()),
                            Float.parseFloat(valorMaximoInput.getText()),
                            stringToBoolean(resultadosNumericosInput.getText())
                    );
                    try {
                        ControllerPracticasPeticiones.getInstance().modificarPractica(practica);
                        JOptionPane.showMessageDialog(null, "Práctica modificada");
                        self.setVisible(false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private boolean stringToBoolean (String value) {
        if (value.equals("s")) {
            return true;
        }
        return false;
    }

}
