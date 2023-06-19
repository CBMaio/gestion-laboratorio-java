package Vistas.Practicas;

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

public class BuscarPractica extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JTextField codigoInput;
    private JButton buscarPrácticaButton;

    private PracticaPeticionDto practicaData;
    private BuscarPractica self;

    public BuscarPractica (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.self = this;
        events();
    }

    private void events () {
        buscarPrácticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarPractica(codigoInput.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void buscarPractica (String codigo) throws Exception {
        if (Utils.isNumeric(codigo)) {
            ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
            if (controller.practicaExistente(Integer.parseInt(codigo))) {
                this.practicaData = controller.getPracticaDTO(Integer.parseInt(codigo));
                ModificaciónPracticaScreen form = new ModificaciónPracticaScreen(self, "Prácticas", practicaData);
                form.setVisible(true);
            } else  {
                JOptionPane.showMessageDialog(null, "Práctica no encontrada");
            }
        }

        codigoInput.setText(null);
        codigoInput.requestFocus();
    }
}
