package Models;

import java.util.ArrayList;
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
    private List<Peticiones> listPeticiones;

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
        this.id = dni;
        this.dni = dni;
    }

    public void setDomicilio (String domicilio) {
        this.domicilio = domicilio;
    }
    public void setMail (String mail) {
        this.mail = mail;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getDni() {
        return dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void agregarPeticion (Peticiones peticion) {
        listPeticiones.add(peticion);
    }

    public List<Peticiones> getPeticiones () {
        return listPeticiones;
    }

}

