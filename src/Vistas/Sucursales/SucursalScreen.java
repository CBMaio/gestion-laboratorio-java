package Vistas.Sucursales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SucursalScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel main;
    private JButton altaButton;
    private JButton bajaButton;
    private JButton modificaciónButton;
    private JButton listarSucursalesButton;
    private SucursalScreen self;

    public SucursalScreen (Window owner, String title) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.self = this;
        this.asociarEventos();
    }

    private void asociarEventos () {
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaSucursalScreen altaSucursal = new AltaSucursalScreen(self, "Sucursales");
                altaSucursal.setVisible(true);
            }
        });
    }

}
