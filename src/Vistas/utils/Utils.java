package Vistas.utils;

import javax.swing.*;

public class Utils {
    public static boolean isAnyFieldEmpty(String... values) {
        for (String value : values) {
            if (value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric (String strNumber) {
        try {
            Integer.parseInt(strNumber);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Campo numérico esperado" );
            return false;
        }
    }

    public static boolean isFloatNumber (String strNumber) {
        try {
            Float.parseFloat(strNumber);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Campo numérico esperado" );
            return false;
        }
    }
}
