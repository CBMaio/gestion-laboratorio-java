package dto;

public class PacienteSucursalDto {
    private Integer idPaciente;
    private Integer numeroSucursal;
    private String nombrePaciente;
    private String nombreSucursal;

    public PacienteSucursalDto(Integer idPaciente, Integer numeroSucursal, String nombrePaciente, String nombreSucursal) {
        this.idPaciente = idPaciente;
        this.numeroSucursal = numeroSucursal;
        this.nombrePaciente = nombrePaciente;
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getIdPaciente() { return idPaciente; }

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setIdPaciente (Integer id) {
        this.idPaciente = id;
    }

    public String getNombrePaciente () {
        return nombrePaciente;
    }

    public void setNombrePaciente (String name) {
        this.nombrePaciente = name;
    }
    public void setNumeroSucursal (Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public String getNombreSucursal () {
        return nombreSucursal;
    }

    public void setNombreSucursal (String name) {
        this.nombreSucursal = name;
    }
}
