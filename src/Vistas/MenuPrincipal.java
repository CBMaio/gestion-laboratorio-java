package Vistas;

import Vistas.Pacientes.PacientesScreen;
import Vistas.Peticiones.PeticionesScreen;
import Vistas.Practicas.PracticasScreen;
import Vistas.Sucursales.SucursalScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JButton pacientesButton;
    private JButton sucursalesButton;
    private JButton prácticasButton;
    private JButton peticionesButton;
    private MenuPrincipal self;

    public MenuPrincipal (String titulo) {
        super(titulo);
        setContentPane(pnlPrincipal);
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.self = this;
    }

    public void asociarEventos () {
        pacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacientesScreen pacientesScreen = new PacientesScreen(self, "Menu Pacientes");
                pacientesScreen.setVisible(true);
            }
        });

        sucursalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SucursalScreen sucularScreen = new SucursalScreen(self, "Menu Sucursales");
                sucularScreen.setVisible(true);
            }
        });

        peticionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeticionesScreen peticionesScreen = new PeticionesScreen(self, "Menu peticiones");
                peticionesScreen.setVisible(true);
            }
        });

        prácticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PracticasScreen practicasScreen = new PracticasScreen(self, "Menu de prácticas");
                practicasScreen.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal("Menu principal");
        menu.setVisible(true);
    }
}
