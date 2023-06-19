package Vistas.Practicas;

import Models.Practica;
import Vistas.Pacientes.PacientesModificacionScreen;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
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

    private ModificaciónPracticaScreen self;

    PracticaPeticionDto initialData;

    public ModificaciónPracticaScreen (Window owner, String title, PracticaPeticionDto practicaData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
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
    }

    private void modificarListener () {
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utils.isNumeric(codigoInput.getText()) && Utils.isFloatNumber(horasDeDemoraInput.getText()) && Utils.isNumeric(grupoInput.getText())) {
                    PracticaPeticionDto practica = new PracticaPeticionDto(
                            Integer.parseInt(codigoInput.getText()),
                            nombreInput.getText(),
                            Integer.parseInt(grupoInput.getText()),
                            Float.parseFloat(horasDeDemoraInput.getText())
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
}
