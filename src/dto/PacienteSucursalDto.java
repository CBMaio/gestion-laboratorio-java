package dto;

public class PacienteSucursalDto {
    private Integer idPaciente;
    private Integer numeroSucursal;
    private String nombrePaciente;
    private String nombreSucursal;
    private Integer edad;
    private String apellido;
    private String domicilio;
    private String mail;
    public PacienteSucursalDto(Integer idPaciente, String nombrePaciente, String apellido, Integer edad, String domicilio, String mail ) {
        this.idPaciente = idPaciente;
        this.nombrePaciente = nombrePaciente;
        this.edad = edad;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.mail = mail;
    }

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
