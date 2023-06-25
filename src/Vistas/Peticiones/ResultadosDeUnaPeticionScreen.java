package Vistas.Peticiones;

import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;

public class ResultadosDeUnaPeticionScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton aceptarButton;
    private PracticaPeticionDto peticionData;
    public ResultadosDeUnaPeticionScreen (Window owner, String title, PracticaPeticionDto peticionData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.peticionData = peticionData;
    }

    private void asociarEventos(){}
}
