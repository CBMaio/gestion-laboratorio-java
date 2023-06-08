package Vistas.utils;

import Models.Paciente;

import javax.swing.*;
import java.util.ArrayList;

public class ListaModel extends AbstractListModel {
    private ArrayList<String> list = new ArrayList<String>();
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    public void add(String item) {
        list.add(item);
        fireContentsChanged(this, 0, list.size());
    }
}
