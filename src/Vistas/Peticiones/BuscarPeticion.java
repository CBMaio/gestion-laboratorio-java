package Vistas.Peticiones;

import Models.Practica;
import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuscarPeticion extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton buscarPeticiónButton;
    private JTextField peticionIdInput;
    private BuscarPeticion self;
    private PracticaPeticionDto peticionData;

    public BuscarPeticion (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(500,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos();
    }

    private void asociarEventos(){
        buscarPeticiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = peticionIdInput.getText();
                if (Utils.isNumeric(id)) {
                    try {
                        buscarPeticion(Integer.parseInt(id));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void buscarPeticion (Integer id) throws Exception {
        ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
        if (controller.peticionExistente(id)) {
            PracticaPeticionDto peticionDTO = controller.getPeticionDto(id);
            this.peticionData = peticionDTO;
            return;
        } else  {
            JOptionPane.showMessageDialog(null, "Petición no encontrada");
        }
        peticionIdInput.setText(null);
        peticionIdInput.requestFocus();
    }

}
