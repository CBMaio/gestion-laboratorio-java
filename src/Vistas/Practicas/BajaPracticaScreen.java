package Vistas.Practicas;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BajaPracticaScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlForm;
    private JButton button1;
    private JTextField codigoInput;

    private BajaPracticaScreen self;

    public BajaPracticaScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.listener();
        this.self = this;
    }

    private void listener () {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoInput.getText();
                try {
                    eliminarPractica(codigo);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void eliminarPractica (String codigo) throws Exception {
        if (Utils.isNumeric(codigo)) {
            Integer validCod = Integer.parseInt(codigo);
            ControllerPracticasPeticiones controllerInstance = ControllerPracticasPeticiones.getInstance();
            Boolean existePractica = controllerInstance.practicaExistente(validCod);
            if (existePractica) {
                controllerInstance.deleteByCodigoPractica(validCod);
                JOptionPane.showMessageDialog(null, "Práctica eliminada exitosamente");
                self.setVisible(false);
                return;
            }

            JOptionPane.showMessageDialog(null, "Práctica no encontrada");
            codigoInput.setText(null);
            codigoInput.requestFocus();
        }
    }
}
