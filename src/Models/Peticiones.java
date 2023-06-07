package Models;

import java.util.ArrayList;

public class Peticiones {
    private Integer numeroPeticion;
    private Paciente paciente;
    private String obraSocial;
    private String nombrePeticion;

    public Peticiones(){}
    public Peticiones (Integer numeroPeticion, String obraSocial) {
        this.numeroPeticion = numeroPeticion;
        this.obraSocial = obraSocial;
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
}
