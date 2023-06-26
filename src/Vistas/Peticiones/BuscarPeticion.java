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

    public BuscarPeticion (Window owner, String title, String action) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(500,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos(action);
    }

    private void asociarEventos(String action){
        buscarPeticiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = peticionIdInput.getText();
                if (Utils.isNumeric(id)) {
                    try {
                        if (!buscarPeticionExitosamente(Integer.parseInt(id))) {
                            return;
                        }
                        openScreen(action);
                        peticionIdInput.setText(null);
                        peticionIdInput.requestFocus();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private Boolean buscarPeticionExitosamente (Integer id) throws Exception {
        ControllerPracticasPeticiones controller = ControllerPracticasPeticiones.getInstance();
        if (controller.peticionExistente(id)) {
            PracticaPeticionDto peticionDTO = controller.getPeticionDto(id);
            this.peticionData = peticionDTO;
            return true;
        } else  {
            JOptionPane.showMessageDialog(null, "Petición no encontrada");
        }
        peticionIdInput.setText(null);
        peticionIdInput.requestFocus();
        return false;
    }


    private void openScreen (String action) {
        switch (action) {
            case "cargaResultados":
                openCargaDeResultadosScreen();
                break;
            case "verResultados":
                openVerResultadosScreen();
                break;
            case "modificarPeticion":
                openModificarPeticion();
                break;
            case "eliminarPeticion":
                openEliminarPeticion();
                break;
        }
    }

    private void openCargaDeResultadosScreen () {
        CargarResultadosScreen form = new CargarResultadosScreen(self, "Peticiones", peticionData);
        form.setVisible(true);
    }

    private void openVerResultadosScreen () {
        if (!peticionData.tieneResultados()) {
            JOptionPane.showMessageDialog(null, "La petición seleccionada no tiene resultados cargados");
            return;
        }
        ResultadosDeUnaPeticionScreen screen = new ResultadosDeUnaPeticionScreen(self, "Peticiones", peticionData);
        screen.setVisible(true);
    }

    private void openModificarPeticion () {
        ModificarPeticionScreen form = new ModificarPeticionScreen(self, "Peticiones", peticionData);
        form.setVisible(true);
    }

    private void openEliminarPeticion () {
        ModificarPeticionScreen form = new ModificarPeticionScreen(self, "Peticiones", peticionData);
        form.setVisible(true);
    }

}
