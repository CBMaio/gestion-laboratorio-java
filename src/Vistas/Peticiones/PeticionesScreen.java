package Vistas.Peticiones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeticionesScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JButton altaButton;
    private JButton bajaButton;
    private JButton modificacionButton;
    private PeticionesScreen self;

    public PeticionesScreen (Window owner, String title) {
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
                AltaPeticionesScreen altaScreen = new AltaPeticionesScreen(self, "Peticiones");
                altaScreen.setVisible(true);
            }
        });
    }

}
