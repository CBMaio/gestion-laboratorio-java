package Models;

import java.util.Date;
import java.util.Random;

public class Resultado {
    private Integer valor;
    private Boolean esReactivo;
    private Practica practica;
    private Integer id;
    private Boolean valorCritico;
    private Boolean valorReservado;

    public Resultado (Integer valor, Practica practica) {
        this.id = new Random().nextInt();
        this.valor = valor;
        this.practica = practica;
        this.valorFueraDeRango();
    }
    public Resultado (Boolean valor, Practica practica) {
        this.id = new Random().nextInt();
        this.esReactivo = valor;
        this.practica = practica;
        this.valorFueraDeRango();
    }

    public Integer getResultado () {
        return valor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Boolean getEsReactivo() {
        return esReactivo;
    }

    public void setEsReactivo(Boolean esReactivo) {
        this.esReactivo = esReactivo;
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean valorFueraDeRango () {
        if (practica.getTieneResultadosNumericos()) {
            Float minimo = practica.getValorReferenciaMinimo();
            Float maximo = practica.getValorReferenciaMaximo();
            if (this.valor < minimo || this.valor > maximo) {
                if (practica.getTipoDePracica().equals("critico")) {
                    this.valorCritico = true;
                    this.valorReservado = null;
                } else {
                    this.valorCritico = null;
                    this.valorReservado = true;
                }
                return true;
            }

            return false;
        }

        if (esReactivo) {
            if (practica.getTipoDePracica().equals("reservado")) {
                this.valorCritico = null;
                this.valorReservado = true;
            } else {
                this.valorReservado = null;
                this.valorCritico = true;
            }
            return true;
        }

        return false;
    }

    public Boolean getValorCritico() {
        return valorCritico;
    }

    public Boolean getValorReservado() {
        return valorReservado;
    }
}
