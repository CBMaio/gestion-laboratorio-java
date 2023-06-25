package Models;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Practica {
    private Integer codigo;
    private String nombre;
    private Integer grupo;
    private Float tiempoDeDemora;
    private Float valorReferenciaMinimo;
    private Float valorReferenciaMaximo;
    private String tipoDePracica;
    // tipo de practica podrá ser 'critico' o 'reservado'. Por ejemplo, un análisis de HIV es 'reservado',
    // un resultado de colesterol o glucemia, es crítico.
    private Boolean tieneResultadosNumericos;

    public Practica(){}

    public Practica (Integer codigo, String nombre, Integer grupo, Float tiempoDeDemora, String tipoDePracica, Float valorReferenciaMinimo, Float valorReferenciaMaximo, Boolean resultadosNumericos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.tiempoDeDemora = tiempoDeDemora;
        this.tipoDePracica = tipoDePracica;
        this.tieneResultadosNumericos = resultadosNumericos;
        setValoresDeReferencia(valorReferenciaMinimo, valorReferenciaMaximo);
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

    public void setValoresDeReferencia (Float minimo, Float maximo) {
        this.valorReferenciaMinimo = minimo;
        this.valorReferenciaMaximo = maximo;
    }


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

}
