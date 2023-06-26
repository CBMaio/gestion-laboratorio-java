package Vistas.Peticiones;

import Vistas.utils.Utils;
import controllers.ControllerPacienteSucursal;
import controllers.ControllerPracticasPeticiones;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPeticionScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton modificarButton;
    private JPanel pnlForm;
    private JTextField fechaEntregaInput;
    private JTextField fechaDeCargaInput;
    private JTextField obraSocialInput;
    private JLabel numeroPeticionLabel;
    private JTextField dniInput;
    private JTextField numSucursalInput;
    private PracticaPeticionDto peticionData;
    private ModificarPeticionScreen self;

    public ModificarPeticionScreen (Window owner, String title, PracticaPeticionDto peticionData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.peticionData = peticionData;
        this.self = this;
        this.asociarEventos();
        this.fillForm();
    }

    private void asociarEventos (){
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PracticaPeticionDto peticionDto = new PracticaPeticionDto(
                            peticionData.getNumeroPeticion(),
                            peticionData.getPaciente(),
                            fechaDeCargaInput.getText(),
                            fechaEntregaInput.getText(),
                            peticionData.getSucursal()
                    );
                    ControllerPracticasPeticiones.getInstance().modificarPeticion(peticionDto);
                    JOptionPane.showMessageDialog(null, "Práctica modificada");
                    self.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void fillForm () {
        numeroPeticionLabel.setText(peticionData.getNumeroPeticion().toString());
        fechaDeCargaInput.setText(peticionData.getFechaCarga());
        fechaEntregaInput.setText(peticionData.getFechaEntrega());
    }
}
