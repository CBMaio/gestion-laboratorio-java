package Models;

public class Practica {
    private Integer codigo;
    private String nombre;
    private Integer grupo;
    private Float tiempoDeDemora;
    private Resultado resultado;

    public Practica(){}

    public Practica (Integer codigo, String nombre, Integer grupo, Float tiempoDeDemora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.tiempoDeDemora = tiempoDeDemora;
    }

    public Integer getCodigo () {
        return codigo;
    }
    public String getNombre () { return nombre; }

    public void setCodigo (Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre (String name) {
        this.nombre = name;
    }

//    public void inhabilitarPractica (){}
//    public void habilitarPractica (){}
//    public Boolean esReservado (){};
//    public Boolean esCritico (){};

    public Resultado obtenerResultado () {
        return resultado;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Float getTiempoDeDemora() {
        return tiempoDeDemora;
    }

    public void setTiempoDeDemora(Float tiempoDeDemora) {
        this.tiempoDeDemora = tiempoDeDemora;
    }
}
