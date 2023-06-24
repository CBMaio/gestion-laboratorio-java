package Models;

import java.util.ArrayList;

public class Peticiones {
    private Integer numeroPeticion;
    private Paciente paciente;
    private String obraSocial;
    private String nombrePeticion;
    private ArrayList<Practica> practicas = new ArrayList<Practica>();
    private String fechaCarga;
    private String fechaEntrega;

    public Peticiones(){}
    public Peticiones (Integer numeroPeticion, String obraSocial, String fechaCarga, String fechaEntrega) {
        this.numeroPeticion = numeroPeticion;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getNumeroPeticion () { return numeroPeticion; }

    public String getNombrePeticion () { return  nombrePeticion; }
    public void setNumeroPeticion (Integer id) {
        this.numeroPeticion = id;
    }
    public void setNombrePeticion (String name) {
        this.nombrePeticion = name;
    }
//    public String getEstadoFinal(){}
//    public ArrayList getPracticasCriticas() {}
//    public ArrayList getPracticasReservadas() {}
//    public void mostrarResultados(){}

    public void addPractica (Practica practica) {
        practicas.add(practica);
    }

    public ArrayList<Practica> getPracticas () {
        return this.practicas;
    }

    public void setPaciente (Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
