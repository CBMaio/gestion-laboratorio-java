package controllers;

import Models.Paciente;
import Models.Sucursal;
import dto.PacienteSucursalDto;

import javax.swing.*;
import java.util.ArrayList;

public class ControllerPacienteSucursal {
    private static ControllerPacienteSucursal CONTROLLER = null;
    private static ArrayList<Paciente> pacientesArrayList;

    private static ArrayList<Sucursal> sucursalesArrayList;
    private ControllerPacienteSucursal(){};

    public static synchronized ControllerPacienteSucursal getInstance() throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new ControllerPacienteSucursal();
            sucursalesArrayList = getSucursalesList();
            pacientesArrayList = getPacientesList();
        }

        return CONTROLLER;
    }

    public static ArrayList<Sucursal> getSucursalesList() {
        ArrayList sucursales = new ArrayList();
        sucursales.add(new Sucursal());
        return sucursales;
    }

    public static ArrayList<Paciente> getPacientesList() {
        ArrayList pacientes = new ArrayList();

        pacientes.add(new Paciente());
        return pacientes;
    }

    public ArrayList<Paciente> mostrarPaciente () {
        if (pacientesArrayList.isEmpty()) {
            return new ArrayList<>();
        }

        return pacientesArrayList;
    }


    public static PacienteSucursalDto modelsToDto(Paciente model, Sucursal modelSucursal){
        return new PacienteSucursalDto(model.getId(), modelSucursal.getNumeroSucursal(), model.getNombreCompleto(), modelSucursal.getNombre());
    }

    public PacienteSucursalDto getDtoByIdPacienteIdSucursal (Integer idPaciente, Integer idSucursal) throws Exception {
        Paciente paciente = null;
        Sucursal sucursal = null;
        for (Paciente model: pacientesArrayList) {
            if (model.getId().equals(idPaciente)){
                paciente = model;
            }
        }

        for (Sucursal model: sucursalesArrayList) {
            if (model.getNumeroSucursal().equals(idSucursal)){
                sucursal = model;
            }
        }

        if ((paciente == null) || (sucursal == null)) {
            return  null;
        }

        return modelsToDto(paciente, sucursal);
    }

    public static Paciente dtoToPaciente(PacienteSucursalDto dto){
        Paciente paciente = new Paciente();
        paciente.setDni(dto.getIdPaciente());
        paciente.setNombre(dto.getNombrePaciente());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setMail(dto.getMail());
        paciente.setEdad(dto.getEdad());
        return paciente;
    }

    public static Sucursal dtoToSucursal (PacienteSucursalDto dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNumeroSucursal(dto.getNumeroSucursal());
        sucursal.setNombreSucursal(dto.getNombreSucursal());
        return sucursal;
    }

    public void addPaciente(PacienteSucursalDto dto) throws Exception {
        if(pacienteExistente(dto.getIdPaciente()).equals(false)){
            pacientesArrayList.add(dtoToPaciente(dto));
//            modelDao.save(toModel(dto));
        }
    }

    public void addSucursal (PacienteSucursalDto dto) throws Exception {
        if(sucursalExistente(dto.getNumeroSucursal()) == false) {
            sucursalesArrayList.add(dtoToSucursal(dto));
        }
    }

    public Boolean sucursalExistente (Integer numeroSucursal) {
        for (Sucursal model: sucursalesArrayList) {
            if (model.getNumeroSucursal().equals(numeroSucursal)) {
                return true;
            }
        }

        return false;
    }
    public Boolean pacienteExistente (Integer id) {
        if (pacientesArrayList.size() >= 1) {
            for (Paciente model: pacientesArrayList) {
                if(model.getId() != null) {
                    if (model.getId().equals(id)){
                        return true;
                    }
                }
            }
        }

        return  false;
    }

    public void deleteByPacienteId(Integer id){
        int index = getPacienteIndex(id);
        if(index != -1){
            pacientesArrayList.remove(index);
        }
    }

    private int getPacienteIndex(Integer id){
        for (int i=0;i<pacientesArrayList.size();i++){
            if(pacientesArrayList.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
}
