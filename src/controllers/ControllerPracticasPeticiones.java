package controllers;

import Models.Paciente;
import Models.Peticiones;
import Models.Practica;
import Models.Sucursal;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import java.time.Period;
import java.util.ArrayList;

public class ControllerPracticasPeticiones {
    private static ArrayList<Practica> listPracticas;
    private static ArrayList<Peticiones> listPeticiones;
    private static ControllerPracticasPeticiones CONTROLLER = null;
    private ControllerPracticasPeticiones(){};

    public static synchronized ControllerPracticasPeticiones getInstance() throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new ControllerPracticasPeticiones();
            listPracticas = getPracticasList();
            listPeticiones = getPeticionesList();
        }

        return CONTROLLER;
    }

    public static ArrayList<Peticiones> getPeticionesList () {
        ArrayList peticiones = new ArrayList();
        peticiones.add(new Peticiones());
        return peticiones;
    }

    public static ArrayList<Practica> getPracticasList() {
        ArrayList practicas = new ArrayList();
        practicas.add(new Practica());
        return practicas;
    }

    public static PracticaPeticionDto modelsToDto(Practica model, Peticiones modelPeticion){
        return new PracticaPeticionDto(model.getCodigo(), modelPeticion.getNumeroPeticion(), model.getNombre(), modelPeticion.getNombrePeticion() );
    }

    public PracticaPeticionDto getDtoByIdPracticaIdPeticion (Integer codigoPractica, Integer idPeticion) throws Exception {
        Practica practica = null;
        Peticiones peticion = null;
        for (Practica model: listPracticas) {
            if (model.getCodigo().equals(codigoPractica)){
                practica = model;
            }
        }

        for (Peticiones model: listPeticiones) {
            if (model.getNumeroPeticion().equals(idPeticion)){
                peticion = model;
            }
        }

        if ((practica == null) || (peticion == null)) {
            return  null;
        }

        return modelsToDto(practica, peticion);
    }

    public static Practica dtoToPractica(PracticaPeticionDto dto){
        Practica practica = new Practica();
        practica.setCodigo(dto.getCodigoPractica());
        practica.setNombre(dto.getNombrePractica());
        return practica;
    }

    public static Peticiones dtoToPeticion (PracticaPeticionDto dto) {
        Peticiones peticion = new Peticiones();
        peticion.setNumeroPeticion(dto.getNumeroPeticion());
        peticion.setNombrePeticion(dto.getNombrePeticion());
        return peticion;
    }
}
