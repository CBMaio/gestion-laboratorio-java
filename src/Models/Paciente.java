package Models;

import java.util.List;

public class Paciente {
    private Integer id;
    private Integer dni;
    private String sexo;
    private Integer edad;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String mail;
//    private List<Peticiones> peticiones;

    public Paciente(){}

    public Paciente (Integer dni, String sexo, Integer edad, String nombre, String apellido, String domicilio, String mail) {
        this.id = dni;
        this.dni = dni;
        this.sexo = sexo;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public Integer getId () {
        return id;
    }

    public Integer getDNI () {
        return dni;
    }
    public String getNombreCompleto () {
        return nombre + ' ' + apellido;
    }

    public String getMail () {
        return mail;
    }

    public String getDomicilio () {
        return domicilio;
    }

    public void setNombre (String name) {
        this.nombre = name;
    }
    public void setDni (Integer dni) {
        this.dni = dni;
        this.dni = dni;
    }

    public void setDomicilio (String domicilio) {
        this.domicilio = domicilio;
    }
    public void setMail (String mail) {
        this.mail = mail;
    }
}

