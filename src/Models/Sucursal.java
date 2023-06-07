package Models;

import java.util.ArrayList;

public class Sucursal {
    private Integer numeroSucursal;
    private String nombre;
    private String responsableTecnico;
    private ArrayList peticiones;

    public Sucursal(){};
    public Sucursal(Integer number, String name, String technic) {
        this.numeroSucursal = number;
        this.nombre = name;
        this.responsableTecnico = technic;
    }

    public ArrayList getPeticiones () {
        return  peticiones;
    }

    public Integer getNumeroSucursal () {
        return numeroSucursal;
    }
    public String getNombre () {
        return nombre;
    }

    public void setNumeroSucursal (Integer id) { this.numeroSucursal = id; }
    public void setNombreSucursal (String name) { this.nombre = name; }
}
