package Models;

import javax.swing.*;
import java.util.ArrayList;

public class Peticiones {
    private Integer numeroPeticion;
    private Paciente paciente;
    private String obraSocial;
    private String nombrePeticion;
    private ArrayList<Practica> practicas = new ArrayList<Practica>();
    private String fechaCarga;
    private String fechaEntrega;
    private ArrayList<Resultado> resultados = new ArrayList<Resultado>();

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

    public void setResultadoToPractica (Integer codigoPractica, Integer resultado) {
        Integer practicaIndex = buscarPracticaIndex(codigoPractica);
        if (practicaIndex.equals(-1)) {
            JOptionPane.showMessageDialog(null, "No se entontró la práctica");
            return;
        }
        Practica practicaItem = practicas.get(practicaIndex);
        Integer indexResultado = practicaTieneResultadoIndex(codigoPractica);
        if (!indexResultado.equals(-1)) {
            modificarResultadoExistente(indexResultado, resultado);
            return;
        }
        Resultado resultadoModel = new Resultado(resultado, practicaItem);
        resultados.add(resultadoModel);
    }

    private Integer practicaTieneResultadoIndex (Integer codPractica) {
        for (int i = 0; i < resultados.size(); i++) {
            if (resultados.get(i).getPractica().getCodigo().equals(codPractica)) {
                return i;
            }
        }

        return -1;
    }

    private void modificarResultadoExistente (Integer indexResultado, Integer nuevoResultado) {
        resultados.get(indexResultado).setValor(nuevoResultado);
    }
    public void setResultadoToPractica (Integer codigoPractica, Boolean resultado) {
        Integer practicaIndex = buscarPracticaIndex(codigoPractica);
        if (practicaIndex.equals(-1)) {
            JOptionPane.showMessageDialog(null, "No se entontró la práctica");
            return;
        }
        Practica practicaItem = practicas.get(practicaIndex);
        Resultado resultadoModel = new Resultado(resultado, practicaItem);
        resultados.add(resultadoModel);
    }

    private Integer buscarPracticaIndex (Integer codigoPractica) {
        for (int i = 0; i < practicas.size(); i++) {
            if (practicas.get(i).getCodigo().equals(codigoPractica)) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<Resultado> getResultados () {
        return this.resultados;
    }

    public Boolean resultadosCompletos () {
        if (practicas.size() == resultados.size()) {
            return true;
        }

        return false;
    }

}
