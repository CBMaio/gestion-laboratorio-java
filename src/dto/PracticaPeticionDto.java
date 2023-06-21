package dto;

import Models.Paciente;

public class PracticaPeticionDto {
    private Integer numeroPeticion;
    private Integer codigoPractica;
    private String nombrePeticion;
    private String nombrePractica;

    private Integer grupoPractica;
    private Float horasDeDemora;

    private PacienteSucursalDto paciente;

    public PracticaPeticionDto(Integer codigoPractica, Integer numeroPeticion, String nombrePractica, String nombrePeticion) {
        this.numeroPeticion = numeroPeticion;
        this.codigoPractica = codigoPractica;
        this.nombrePeticion = nombrePeticion;
        this.nombrePractica = nombrePractica;
    }

    public PracticaPeticionDto (Integer codigoPractica, String nombrePractica, Integer grupo, Float horasDeDemora) {
        //solo datos de la práctica
        this.codigoPractica = codigoPractica;
        this.nombrePractica = nombrePractica;
        this.grupoPractica = grupo;
        this.horasDeDemora = horasDeDemora;
    }

    public PracticaPeticionDto (Integer numeroPeticion, PacienteSucursalDto paciente) {
        this.numeroPeticion = numeroPeticion;
        this.paciente = paciente;
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
}
