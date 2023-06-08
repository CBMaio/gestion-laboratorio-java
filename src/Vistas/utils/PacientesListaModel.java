package Vistas.utils;

import controllers.ControllerPacienteSucursal;

import javax.swing.*;
import java.util.ArrayList;

public class PacientesListaModel extends AbstractListModel {

    private ArrayList<String> pacientes () throws Exception {
        return ControllerPacienteSucursal.getInstance().getListaPacientes();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getElementAt(int index) {
        return null;
    }
}
