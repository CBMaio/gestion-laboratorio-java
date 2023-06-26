package Vistas.Peticiones;

import Vistas.utils.Utils;
import controllers.ControllerPracticasPeticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BajaPeticionScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton darDeBajaButton;
    private JPanel pnlForm;
    private JTextField numeroInput;
    private BajaPeticionScreen self;

    public BajaPeticionScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.listener();
        this.self = this;
    }

    private void listener(){
        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = numeroInput.getText();
                try {
                    eliminarPeticion(numero);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void eliminarPeticion (String numero) throws Exception {
        if (!Utils.isNumeric(numero)) {
            return;
        }

        Integer validNum = Integer.parseInt(numero);
        ControllerPracticasPeticiones controllerInstance = ControllerPracticasPeticiones.getInstance();
        Boolean existePeticion = controllerInstance.peticionExistente(validNum);

        if(!existePeticion) {
            JOptionPane.showMessageDialog(null, "Petición no encontrada");
            numeroInput.setText(null);
            numeroInput.requestFocus();
            return;
        }


        controllerInstance.deleteByCodigoPeticion(validNum);
        JOptionPane.showMessageDialog(null, "Petición eliminada exitosamente");
        self.setVisible(false);

    }

}
