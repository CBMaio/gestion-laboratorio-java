package Models;

public class Practica {
    private Integer codigo;
    private String nombre;
    private Integer grupo;
    private Float tiempoDeDemora;
    private Resultado resultado;

    public Practica(){}

    public Practica (Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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



}
