package dto;

import Models.Practica;

import java.util.ArrayList;

public class PracticaPeticionDto {
    private Integer numeroPeticion;
    private Integer codigoPractica;
    private String nombrePeticion;
    private String nombrePractica;
    private Integer grupoPractica;
    private Float horasDeDemora;
    private Float valorReferenciaMinimo;
    private Float valorReferenciaMaximo;
    private String tipoDePracica;
    private Boolean tieneResultadosNumericos;
    private String fechaCarga;
    private String fechaEntrega;
    private PacienteSucursalDto paciente;
    private ArrayList<Practica> practicas;

    public PracticaPeticionDto(Integer codigoPractica, Integer numeroPeticion, String nombrePractica, String nombrePeticion) {
        this.numeroPeticion = numeroPeticion;
        this.codigoPractica = codigoPractica;
        this.nombrePeticion = nombrePeticion;
        this.nombrePractica = nombrePractica;
    }

    public PracticaPeticionDto (Integer codigoPractica, String nombrePractica, Integer grupo, Float horasDeDemora, String tipoDePracica, Float minimoValor, Float maximoValor, Boolean tieneResultadosNumericos) {
        //solo datos de la práctica
        this.codigoPractica = codigoPractica;
        this.nombrePractica = nombrePractica;
        this.grupoPractica = grupo;
        this.horasDeDemora = horasDeDemora;
        this.valorReferenciaMinimo =  minimoValor;
        this.valorReferenciaMaximo = maximoValor;
        this.tipoDePracica = tipoDePracica;
        this.tieneResultadosNumericos = tieneResultadosNumericos;
    }

    public PracticaPeticionDto (Integer numeroPeticion, PacienteSucursalDto paciente, String fechaCarga, String fechaEntrega) {
        this.numeroPeticion = numeroPeticion;
        this.paciente = paciente;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
    }

    public PracticaPeticionDto (Integer numeroPeticion, PacienteSucursalDto paciente, String fechaCarga, String fechaEntrega, ArrayList<Practica> practicas) {
        this.numeroPeticion = numeroPeticion;
        this.paciente = paciente;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.practicas = practicas;
    }

    public Integer getNumeroPeticion() { return numeroPeticion; }

    public Integer getCodigoPractica() {
        return codigoPractica;
    }

    public void setNumeroPeticion (Integer id) {
        this.numeroPeticion = id;
    }

    public String getNombrePeticion () {
        return nombrePeticion;
    }

    public void setNombrePeticion (String name) {
        this.nombrePeticion = name;
    }
    public void setCodigoPractica (Integer codigo) {
        this.codigoPractica = codigo;
    }

    public String getNombrePractica () {
        return nombrePractica;
    }

    public void setNombrePractica (String name) {
        this.nombrePractica = name;
    }

    public Integer getGrupoPractica() {
        return grupoPractica;
    }

    public void setGrupoPractica(Integer grupoPractica) {
        this.grupoPractica = grupoPractica;
    }

    public Float getHorasDeDemora() {
        return horasDeDemora;
    }

    public void setHorasDeDemora(Float horasDeDemora) {
        this.horasDeDemora = horasDeDemora;
    }

    public PacienteSucursalDto getPaciente () { return this.paciente; }

    public Float getValorReferenciaMinimo() {
        return valorReferenciaMinimo;
    }

    public void setValorReferenciaMinimo(Float valorReferenciaMinimo) {
        this.valorReferenciaMinimo = valorReferenciaMinimo;
    }

    public Float getValorReferenciaMaximo() {
        return valorReferenciaMaximo;
    }

    public void setValorReferenciaMaximo(Float valorReferenciaMaximo) {
        this.valorReferenciaMaximo = valorReferenciaMaximo;
    }

    public String getTipoDePracica() {
        return tipoDePracica;
    }

    public void setTipoDePracica(String tipoDePracica) {
        this.tipoDePracica = tipoDePracica;
    }

    public Boolean getTieneResultadosNumericos() {
        return tieneResultadosNumericos;
    }

    public void setTieneResultadosNumericos(Boolean tieneResultadosNumericos) {
        this.tieneResultadosNumericos = tieneResultadosNumericos;
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

    public ArrayList<Practica> getPracticas () {
        return this.practicas;
    }

}
