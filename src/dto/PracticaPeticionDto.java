package dto;

public class PracticaPeticionDto {
    private Integer numeroPeticion;
    private Integer codigoPractica;
    private String nombrePeticion;
    private String nombrePractica;

    public PracticaPeticionDto(Integer codigoPractica, Integer numeroPeticion, String nombrePractica, String nombrePeticion) {
        this.numeroPeticion = numeroPeticion;
        this.codigoPractica = codigoPractica;
        this.nombrePeticion = nombrePeticion;
        this.nombrePractica = nombrePractica;
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
}
