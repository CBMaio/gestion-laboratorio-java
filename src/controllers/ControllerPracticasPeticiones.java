package controllers;

import Models.Paciente;
import Models.Peticiones;
import Models.Practica;
import Models.Sucursal;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
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
        Practica practica = new Practica(dto.getCodigoPractica(), dto.getNombrePractica(), dto.getGrupoPractica(), dto.getHorasDeDemora());
        return practica;
    }

    public static Peticiones dtoToPeticion (PracticaPeticionDto dto) {
        Peticiones peticion = new Peticiones();
        peticion.setNumeroPeticion(dto.getNumeroPeticion());
        peticion.setNombrePeticion(dto.getNombrePeticion());
        return peticion;
    }

    public boolean addPracticaExitosamente (PracticaPeticionDto dto) throws Exception {
        if(practicaExistente(dto.getCodigoPractica()).equals(false)) {
            Practica newPractica = dtoToPractica(dto);
            listPracticas.add(newPractica);
            return true;
        }

        return false;
    }

    public Boolean practicaExistente (Integer codigoPractica) {
        if (listPracticas.size() >= 1) {
            for (Practica model : listPracticas) {
                if (model.getCodigo() != null) {
                    if (model.getCodigo().equals(codigoPractica)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ArrayList<Practica> mostrarPracitcas () {
        if (listPracticas.isEmpty()) {
            return new ArrayList<>();
        }

        return listPracticas;
    }

    public void deleteByCodigoPractica(Integer cod){
        int index = getPracticaIndex(cod);
        if(index != -1){
            listPracticas.remove(index);
        }
    }

    private int getPracticaIndex(Integer cod){
        for (int i=0;i<listPracticas.size();i++){
            if(listPracticas.get(i).getCodigo() != null && listPracticas.get(i).getCodigo().equals(cod)){
                return i;
            }
        }
        return -1;
    }

    public PracticaPeticionDto getPracticaDTO (Integer dni) {
        for (int i=0;i<listPracticas.size();i++){
            if(listPracticas.get(i).getCodigo() != null && listPracticas.get(i).getCodigo().equals(dni)){
                Practica selected = listPracticas.get(i);
                return new PracticaPeticionDto(selected.getCodigo(), selected.getNombre(), selected.getGrupo(), selected.getTiempoDeDemora());
            }
        }

        return null;
    }
}
