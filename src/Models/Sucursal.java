package Models;

import java.util.ArrayList;

public class Sucursal {
    private Integer numeroSucursal;
    private String nombre;
    private String direccion;
    private String responsableTecnico;
    private ArrayList<Peticiones> peticionesList;

    public Sucursal(){};
    public Sucursal(Integer number, String name, String direccion) {
        this.numeroSucursal = number;
        this.nombre = name;
        this.direccion = direccion;
        this.peticionesList = new ArrayList<>();
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
        this.peticionesList = peticiones;
    }

    public void agregarPeticion (Peticiones peticion) {
        peticionesList.add(peticion);
    }

    public ArrayList<Peticiones> getPeticionesList () {
        return this.peticionesList;
    }

    public void eliminarPeticion (Peticiones peticion) {
        Integer peticionIndex = getPeticionIndex(peticion);
        if (peticionIndex.equals(-1)) {
            return;
        }

        peticionesList.remove(peticionIndex);
    }

    public void eliminarPeticion (Integer codigoPeticion) {
        for (int i = 0; i < getPeticionesList().size(); i++) {
            if (getPeticionesList().get(i).getNumeroPeticion().equals(codigoPeticion)) {
                peticionesList.remove(i);
                return;
            }
        }
    }

    private Integer getPeticionIndex (Peticiones peticion) {
        Integer peticionID = peticion.getNumeroPeticion();
        for (int i = 0; i < getPeticionesList().size(); i++) {
            if(peticionesList.get(i).getNumeroPeticion().equals(peticionID))  {
                return i;
            }
        }

        return -1;
    }
}
