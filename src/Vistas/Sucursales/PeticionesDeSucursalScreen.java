package Vistas.Sucursales;

import Models.Peticiones;
import Models.Practica;
import Models.Resultado;
import controllers.ControllerPacienteSucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PeticionesDeSucursalScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JLabel numeroSucursal;
    private JPanel pnlMenu;
    private JButton aceptarButton;
    private JPanel pnlMainScreen;
    private PacienteSucursalDto sucursalData;
    private PeticionesDeSucursalScreen self;

    public PeticionesDeSucursalScreen (Window owner, String title, PacienteSucursalDto sucursalData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.sucursalData = sucursalData;
        this.self = this;
        this.asociarEventos();
        this.fillForm();
    }

    private void asociarEventos () {}

    private void fillForm() {
        ArrayList<Peticiones> peticiones = getPeticiones();
        Integer rows = peticiones.size();
        pnlMainScreen.setLayout(new GridLayout(rows, 1));

        for (Peticiones item: peticiones) {
            JLabel practicaLabel = new JLabel();
            String value = item.getNumeroPeticion().toString() + " - " + item.getFechaCarga();
            practicaLabel.setText(value);
            pnlMainScreen.add(practicaLabel);
        }

        pnlMainScreen.validate();
    }
    private ArrayList getPeticiones () {
        ControllerPacienteSucursal controller = null;
        try {
            controller = ControllerPacienteSucursal.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller.getPeticionesPorSucursaL(sucursalData);
    }

}
