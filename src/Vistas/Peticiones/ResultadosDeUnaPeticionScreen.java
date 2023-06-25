package Vistas.Peticiones;

import Models.Practica;
import Models.Resultado;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultadosDeUnaPeticionScreen extends JDialog {
    private JPanel pnlPrincipal;
    private JPanel pnlTitle;
    private JPanel pnlMenu;
    private JButton aceptarButton;
    private JPanel pnlMainScreen;
    private PracticaPeticionDto peticionData;
    private ResultadosDeUnaPeticionScreen self;
    public ResultadosDeUnaPeticionScreen (Window owner, String title, PracticaPeticionDto peticionData) {
        super(owner, title);
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        Integer customHeight = 100 + ((peticionData.getResultados().size()) * 40);
        this.setSize(400,customHeight);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEventos();
        this.peticionData = peticionData;
        this.self = this;
        this.fillScreen();
    }

    private void asociarEventos(){
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.setVisible(false);
            }
        });
    }

    private Boolean tieneValoresReservados () {
        for (Resultado resultado: peticionData.getResultados()) {
            if (resultado.getValorReservado()) {
                return true;
            }
        }

        return false;
    }
    private void fillScreen () {
        ArrayList<Resultado> resultados = peticionData.getResultados();
        Integer rows = resultados.size();
        pnlMainScreen.setLayout(new GridLayout(rows, 2));
        if (this.tieneValoresReservados()) {
            JLabel message = new JLabel();
            message.setText("Debe retirar esta petición por la sucursal");
            pnlMainScreen.add(message);
            return;
        }

        for (Resultado item: resultados) {
            JLabel practicaLabel = new JLabel();
            JLabel resultadoLabel = new JLabel();
            Practica practica = item.getPractica();
            practicaLabel.setText(practica.getNombre());
            if (practica.getTieneResultadosNumericos()) {
                resultadoLabel.setText(item.getResultado().toString());
            } else {
                String value = item.getEsReactivo() ? "Reactivo" : "No reactivo";
                resultadoLabel.setText(value);
            }

            pnlMainScreen.add(practicaLabel);
            pnlMainScreen.add(resultadoLabel);
        }

//        if (resultadosPendientes() > 0) {
//            JLabel message = new JLabel();
//            message.setText("Faltan " + resultadosPendientes().toString() + " resultados");
//            pnlMainScreen.add(message);
//        }
        pnlMainScreen.validate();
    }

    private Integer resultadosPendientes () {
        Integer cantPracticas = peticionData.getPracticas().size();
        Integer cantResultados = peticionData.getResultados().size();
        if (cantResultados == cantPracticas) {
            return 0;
        }

        return cantPracticas - cantResultados;
    }


}
