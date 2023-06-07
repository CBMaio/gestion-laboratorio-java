package Models;

import java.util.Date;

public class Resultado {
    private Integer id;
    private Date fecha;
    private Integer valor;

    public Resultado (Integer valor) {
        this.valor = valor;
    }

    public Integer getResultado () {
        return valor;
    }
}
