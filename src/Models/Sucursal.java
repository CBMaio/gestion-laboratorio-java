package Models;

import java.util.ArrayList;

public class Sucursal {
    private Integer numeroSucursal;
    private String nombre;
    private String direccion;
    private String responsableTecnico;
    private ArrayList peticiones;

    public Sucursal(){};
    public Sucursal(Integer number, String name, String direccion) {
        this.numeroSucursal = number;
        this.nombre = name;
        this.direccion = direccion;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPeticiones(ArrayList peticiones) {
        this.peticiones = peticiones;
    }
}
